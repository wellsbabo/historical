package kr.co.gravity.sample.document;

import wellsbabo.common.request.ApiPageRequest;
import kr.co.gravity.sample.controller.dto.Species;
import kr.co.gravity.sample.service.dto.Rocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.List;

import static kr.co.gravity.sample.document.utils.ApiDocumentUtils.getDocumentRequest;
import static kr.co.gravity.sample.document.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RocketDocumentTests extends BaseDocumentTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void findAllTest() throws Exception {

        // given
        ApiPageRequest search = ApiPageRequest.builder()
                .page(1)
                .limit(10)
                .build();
        List<Rocket> responseList = Arrays.asList(
                Rocket.builder()
                        .idx(1)
                        .id("rosa")
                        .name("로사")
                        .species(Species.HUMAN)
                        .build(),
                Rocket.builder()
                        .idx(2)
                        .id("roy")
                        .name("로이")
                        .species(Species.HUMAN)
                        .build(),
                Rocket.builder()
                        .idx(3)
                        .id("naong")
                        .name("나옹")
                        .species(Species.POKEMON)
                        .build()
        );
        given(rocketMapper.findAll(search)).willReturn(responseList);
        given(rocketMapper.countAll()).willReturn(responseList.size());

        // when
        ResultActions result = this.mockMvc.perform(
                get("/rocket?page=1&limit=10")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        // then
        result.andExpect(status().isOk())
                .andDo(document("rocket-find-all",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(beneathPath("data[]").withSubsectionId("data"),
                                fieldWithPath("idx").type(JsonFieldType.NUMBER).description("고유번호"),
                                fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("species").type(JsonFieldType.STRING).description("종족")
                        )
                ));
    }

}

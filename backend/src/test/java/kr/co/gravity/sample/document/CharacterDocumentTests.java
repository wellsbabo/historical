package kr.co.gravity.sample.document;

import kr.co.gravity.sample.controller.dto.CharacterSearch;
import kr.co.gravity.sample.service.dto.Character;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static kr.co.gravity.sample.document.utils.ApiDocumentUtils.getDocumentRequest;
import static kr.co.gravity.sample.document.utils.ApiDocumentUtils.getDocumentResponse;
import static kr.co.gravity.sample.document.utils.DocumentFormatGenerator.getDateTimeFormat;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CharacterDocumentTests extends BaseDocumentTest {

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
        CharacterSearch search = CharacterSearch.builder()
                .page(1)
                .limit(10)
                .build();
        List<Character> responseList = Arrays.asList(
                Character.builder()
                        .idx(1)
                        .id("jiwoo")
                        .name("한지우")
                        .registerDate(LocalDateTime.of(2022, 1, 1, 1, 1))
                        .build(),
                Character.builder()
                        .idx(2)
                        .id("yiseul")
                        .name("최이슬")
                        .registerDate(LocalDateTime.of(2022, 2, 2, 2, 2))
                        .build()
        );
        given(characterMapper.findAll(search)).willReturn(responseList);
        given(characterMapper.countAll()).willReturn(responseList.size());

        // when
        ResultActions result = this.mockMvc.perform(
                get("/character?page=1&limit=10")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        // then
        result.andExpect(status().isOk())
                .andDo(document("character-find-all",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(beneathPath("data[]").withSubsectionId("data"),
                                fieldWithPath("idx").type(JsonFieldType.NUMBER).description("고유번호"),
                                fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("registerDate").type(JsonFieldType.STRING).attributes(getDateTimeFormat()).description("가입일시")
                        )
                ));
    }

    @Test
    public void findByIdTest() throws Exception {

        // given
        Character response = Character.builder()
                        .idx(1)
                        .id("jiwoo")
                        .name("한지우")
                        .registerDate(LocalDateTime.of(2022, 1, 1, 1, 1))
                        .build();
        given(characterMapper.findById("jiwoo")).willReturn(response);

        // when
        ResultActions result = this.mockMvc.perform(get("/character/{id}", "jiwoo").accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
                .andDo(document("character-find-by-id",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("id").description("아이디")
                        ),
                        responseFields(beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("idx").type(JsonFieldType.NUMBER).description("고유번호"),
                                fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("registerDate").type(JsonFieldType.STRING).attributes(getDateTimeFormat()).description("가입일시")
                        )
                ));
    }

}

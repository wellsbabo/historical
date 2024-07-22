package kr.co.gravity.sample.document;

import kr.co.gravity.sample.controller.dto.PokemonCreate;
import kr.co.gravity.sample.controller.dto.PokemonSearch;
import kr.co.gravity.sample.controller.dto.PokemonUpdate;
import kr.co.gravity.sample.service.dto.Pokemon;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PokemonDocumentTests extends BaseDocumentTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    /* Controller 통합테스트 */
    @Test
    public void findAllTest() throws Exception {

        // given
        PokemonSearch request = PokemonSearch.builder()
                .code("code...")
                .name("name...")
                .limit(10)
                .page(1)
                .build();
        List<Pokemon> responseList = Arrays.asList(
                Pokemon.builder()
                        .idx(1)
                        .code("pikachu")
                        .name("피카츄")
                        .build(),
                Pokemon.builder()
                        .idx(2)
                        .code("bulbasaur")
                        .name("이상해씨")
                        .build(),
                Pokemon.builder()
                        .idx(3)
                        .code("jigglypuff")
                        .name("푸린")
                        .build()
        );
        given(pokemonMapper.findAll(any(PokemonSearch.class))).willReturn(responseList);
        given(pokemonMapper.countAll()).willReturn(responseList.size());

        // when
        ResultActions result = this.mockMvc.perform(
                get("/pokemon?page=1&limit=10")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        // then
        result.andExpect(status().isOk())
                .andDo(document("pokemon-find-all",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(beneathPath("data[]").withSubsectionId("data"),
                                fieldWithPath("idx").type(JsonFieldType.NUMBER).description("고유번호"),
                                fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                        )
                ));
    }

    @Test
    public void createTest() throws Exception {

        // given
        PokemonCreate request = PokemonCreate.builder()
                .code("code...")
                .name("name...")
                .build();
        given(pokemonMapper.create(any(PokemonCreate.class))).willReturn(1);

        // when
        ResultActions result = this.mockMvc.perform(
                post("/pokemon")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                //.andDo(print())
                .andDo(document("pokemon-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                        )
                ));
    }


    @Test
    public void updateTest() throws Exception {

        // given
        int idx = 1;
        PokemonUpdate request = PokemonUpdate.builder()
                .code("code...")
                .name("name...")
                .build();
        given(pokemonMapper.update(eq(idx), any(PokemonUpdate.class))).willReturn(1);

        // when
        ResultActions result = this.mockMvc.perform(
                put("/pokemon/{idx}", idx)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                //.andDo(print())
                .andDo(document("pokemon-update",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("idx").description("고유번호")
                        ),
                        requestFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                        )
                ));
    }

    @Test
    public void deleteTest() throws Exception {

        //given
        int idx = 1;
        given(pokemonMapper.delete(eq(idx))).willReturn(1);

        //when
        ResultActions result = this.mockMvc.perform(
                delete("/pokemon/{idx}", idx)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("pokemon-delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("idx").description("고유번호")
                        )
                ));
    }

    /* Service 단위테스트 */
    @Test
    public void randomTest() throws Exception {

        // given
        PokemonSearch request = PokemonSearch.builder()
                .code("code...")
                .name("name...")
                .limit(10)
                .page(1)
                .build();

        List<Pokemon> responseList = Arrays.asList(
                Pokemon.builder()
                        .idx(1)
                        .code("pikachu")
                        .name("피카츄")
                        .build(),
                Pokemon.builder()
                        .idx(2)
                        .code("bulbasaur")
                        .name("이상해씨")
                        .build(),
                Pokemon.builder()
                        .idx(3)
                        .code("jigglypuff")
                        .name("푸린")
                        .build()
        );
        given(pokemonMapper.findAll(any(PokemonSearch.class))).willReturn(responseList);

        // when
        Pokemon pokemon = pokemonService.findRandom(request);

        // then
        assertEquals(responseList.contains(pokemon), true);
    }

}

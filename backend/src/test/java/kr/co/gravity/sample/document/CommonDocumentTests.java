package kr.co.gravity.sample.document;

import wellsbabo.common.response.ApiResponseCode;
import wellsbabo.common.utils.EnumType;
import kr.co.gravity.sample.controller.dto.Species;
import kr.co.gravity.sample.document.utils.CustomResponseFieldsSnippet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.Map;

import static kr.co.gravity.sample.document.utils.ApiDocumentUtils.getDocumentRequest;
import static kr.co.gravity.sample.document.utils.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommonDocumentTests extends BaseDocumentTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void commonsTest() throws Exception {

        // when
        ResultActions result = this.mockMvc.perform(
                get("/docs?page=1&limit=10")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        // then
        result.andExpect(status().isOk())
                .andDo(document("common",
                getDocumentRequest(),
                getDocumentResponse(),
                queryParameters(
                        parameterWithName("page").description("현재 페이지"),
                        parameterWithName("limit").description("한페이지당 조회 수")
                ),
                responseFields(
                        fieldWithPath("resultCode").type(JsonFieldType.NUMBER).description("결과코드"),
                        fieldWithPath("resultMessage").type(JsonFieldType.STRING).description("결과메시지"),
                        subsectionWithPath("data").description("데이터")
                ),
                customResponseFields("custom-result-type", beneathPath("data.apiResponseCodes").withSubsectionId("codes"),
                        attributes(key("title").value("resultCode / resultMessage")),
                        resultTypeConvertFieldDescriptor(ApiResponseCode.values())
                ),
                customResponseFields("custom-enum-type", beneathPath("data.species").withSubsectionId("species"),
                        attributes(key("title").value("종족")),
                        enumTypeConvertFieldDescriptor(Species.values())
                )
        ));
    }

    private static FieldDescriptor[] resultTypeConvertFieldDescriptor(ApiResponseCode[] codes) {
        return Arrays.stream(codes)
                .map(code -> fieldWithPath(code.name()).type(code.getCode()).description(code.getMessage()))
                .toArray(FieldDescriptor[]::new);
    }

    private static FieldDescriptor[] enumTypeConvertFieldDescriptor(EnumType[] codes) {
        return Arrays.stream(codes)
                .map(code -> fieldWithPath(code.getId()).description(code.getText()))
                .toArray(FieldDescriptor[]::new);
    }

/*
    private static FieldDescriptor[] enumTypeConvertFieldDescriptor(Map<String, String> enumValues) {
        return enumValues.entrySet().stream()
                .map(x -> fieldWithPath(x.getKey()).description(x.getValue()))
                .toArray(FieldDescriptor[]::new);
    }
*/

    public static CustomResponseFieldsSnippet customResponseFields(String type,
                                                                   PayloadSubsectionExtractor<?> subsectionExtractor,
                                                                   Map<String, Object> attributes,
                                                                   FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes, true);
    }

}

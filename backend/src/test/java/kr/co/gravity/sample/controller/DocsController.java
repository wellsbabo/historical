package kr.co.gravity.sample.controller;

import wellsbabo.common.response.ApiResponse;
import wellsbabo.common.response.ApiResponseCode;
import wellsbabo.common.utils.EnumType;
import kr.co.gravity.sample.controller.dto.Species;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DocsController {

    @GetMapping("/docs")
    public ApiResponse<Docs> findAll() {

        Map<String, String> apiResponseCodes = Arrays.stream(ApiResponseCode.values())
                .collect(Collectors.toMap(ApiResponseCode::name, ApiResponseCode::getMessage));
        Map<String, String> species = getDocs(Species.values());

        return ApiResponse.success(Docs.testBuilder()
                .apiResponseCodes(apiResponseCodes)
                .species(species)
                .build());
    }

    private Map<String, String> getDocs(EnumType[] codes) {
        return Arrays.stream(codes)
                .collect(Collectors.toMap(EnumType::getId, EnumType::getText));
    }

}

package kr.co.gravity.sample.controller;

import wellsbabo.common.response.ApiResponse;
import wellsbabo.common.response.ApiResponseCode;
import kr.co.gravity.sample.service.dto.Character;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sample")
public class SampleController {

    // 처리성공
    @GetMapping(value = "/success")
    public ApiResponse<String> success() {
        log.info("log success");
        return ApiResponse.success();
    }

    // 처리실패
    @GetMapping(value = "/error")
    public ApiResponse<Character> error() {
        try {
            throw new RuntimeException("force error");
        }
        catch (Exception ex) {
            log.error("", ex);
            return ApiResponse.create(ApiResponseCode.ERROR);
        }
    }

}

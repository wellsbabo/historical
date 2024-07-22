package kr.co.gravity.sample.controller;

import wellsbabo.common.request.ApiPageRequest;
import wellsbabo.common.response.ApiPageResponse;
import kr.co.gravity.sample.mapper.database3.RocketMapper;
import kr.co.gravity.sample.service.dto.Rocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rocket")
public class RocketController {

    private final RocketMapper rocketMapper;

    @GetMapping(value = "")
    public ApiPageResponse<Rocket> findAll(ApiPageRequest search) {
        List<Rocket> rockets = rocketMapper.findAll(search);
        int totalCount = rocketMapper.countAll();
        return ApiPageResponse.success(rockets, totalCount);
    }

}

package kr.co.gravity.sample.controller;

import wellsbabo.common.response.ApiPageResponse;
import wellsbabo.common.response.ApiResponse;
import kr.co.gravity.sample.controller.dto.CharacterSearch;
import kr.co.gravity.sample.mapper.database1.CharacterMapper;
import kr.co.gravity.sample.service.dto.Character;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {

    private final CharacterMapper characterMapper;

    // GET(조회)
    @GetMapping(value = "")
    public ApiPageResponse<Character> find(CharacterSearch search) {
        List<Character> characters;
        int totalCount = 0;
        if (search.getName() != null) {
            characters = characterMapper.findByName(search);
            totalCount = characterMapper.countByName(search);
        }
        else if (search.getNames() != null && search.getNames().length > 0) {
            characters = characterMapper.findByNames(search);
            totalCount = characterMapper.countByNames(search.getNames());
        }
        else {
            characters = characterMapper.findAll(search);
            totalCount = characterMapper.countAll();
        }
        return ApiPageResponse.success(characters, totalCount);
    }

    @GetMapping(value = "/{id}")
    public ApiResponse<Character> findById(@PathVariable final String id) {
        Character character = characterMapper.findById(id);
        return ApiResponse.success(character);
    }

}

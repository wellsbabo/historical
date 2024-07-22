package kr.co.gravity.sample.controller;

import jakarta.validation.Valid;
import wellsbabo.common.response.ApiPageResponse;
import wellsbabo.common.response.ApiResponse;
import wellsbabo.common.response.ApiResponseCode;
import kr.co.gravity.sample.controller.dto.PokemonCreate;
import kr.co.gravity.sample.controller.dto.PokemonSearch;
import kr.co.gravity.sample.controller.dto.PokemonUpdate;
import kr.co.gravity.sample.mapper.database2.PokemonMapper;
import kr.co.gravity.sample.service.PokemonService;
import kr.co.gravity.sample.service.dto.Pokemon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;
    private final PokemonMapper pokemonMapper;

    @GetMapping(value = "")
    public ApiPageResponse<Pokemon> findAll(PokemonSearch search) {
        List<Pokemon> pokemons = pokemonMapper.findAll(search);
        int totalCount = pokemonMapper.countAll();
        return ApiPageResponse.success(pokemons, totalCount);
    }

    // POST(입력)
    @PostMapping("")
    public ApiResponse<String> create(@Valid @RequestBody PokemonCreate create) {
        int resultCount = pokemonMapper.create(create);
        if (resultCount > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.create(ApiResponseCode.FAIL);
    }

    // PUT(수정)
    @PutMapping("/{idx}")
    public ApiResponse<String> update(@PathVariable("idx") int idx, @Valid @RequestBody PokemonUpdate update) {
        int resultCount = pokemonMapper.update(idx, update);
        if (resultCount > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.create(ApiResponseCode.FAIL);
    }

    // DELETE(삭제)
    @DeleteMapping("/{idx}")
    public ApiResponse<String> delete(@PathVariable("idx") int idx) {
        int resultCount = pokemonMapper.delete(idx);
        if (resultCount > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.create(ApiResponseCode.FAIL);
    }

}

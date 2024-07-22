package kr.co.gravity.sample.service;

import kr.co.gravity.sample.controller.dto.PokemonSearch;
import kr.co.gravity.sample.mapper.database2.PokemonMapper;
import kr.co.gravity.sample.service.dto.Pokemon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class PokemonService {

    private final PokemonMapper pokemonMapper;

    public Pokemon findRandom(PokemonSearch page) {

        List<Pokemon> pokemons = pokemonMapper.findAll(page);
        Random random = new Random();
        int index = random.nextInt(pokemons.size());

        log.info("Random pokemon : " + pokemons.get(index));

        return pokemons.get(index);

    }

}

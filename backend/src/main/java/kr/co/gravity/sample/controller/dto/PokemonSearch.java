package kr.co.gravity.sample.controller.dto;

import wellsbabo.common.request.ApiPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PokemonSearch extends ApiPageRequest {

    private String code;
    private String name;

}

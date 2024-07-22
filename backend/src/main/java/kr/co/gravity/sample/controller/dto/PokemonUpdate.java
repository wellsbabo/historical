package kr.co.gravity.sample.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PokemonUpdate {

    @NotBlank(message = "코드는 필수입니다.")
    private String code;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Builder
    private PokemonUpdate(String code, String name) {
        this.code = code;
        this.name = name;
    }

}

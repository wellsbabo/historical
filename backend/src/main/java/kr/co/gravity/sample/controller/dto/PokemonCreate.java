package kr.co.gravity.sample.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PokemonCreate {

    @NotBlank(message = "코드는 필수입니다.")
    private String code;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

/*
    // Date
    @NotNull(message = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sampleDate;

    // Datetime
    @NotNull(message = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sampleDatetime;
*/

    @Builder
    private PokemonCreate(String code, String name) {
        this.code = code;
        this.name = name;
    }

}

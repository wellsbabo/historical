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
public class CharacterSearch extends ApiPageRequest {

    private String name;
    private String[] names;

}

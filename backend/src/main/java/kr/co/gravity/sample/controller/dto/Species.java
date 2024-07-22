package kr.co.gravity.sample.controller.dto;

import wellsbabo.common.utils.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Species implements EnumType {

    HUMAN("인간"),
    POKEMON("포켓몬");

    private final String text;

    @Override
    public String getId() {
        return this.name();
    }

}

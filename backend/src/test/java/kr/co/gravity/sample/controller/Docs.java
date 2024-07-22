package kr.co.gravity.sample.controller;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class Docs {

    Map<String, String> apiResponseCodes;
    Map<String, String> species;

    @Builder(builderClassName = "TestBuilder", builderMethodName = "testBuilder")
    private Docs(Map<String, String> apiResponseCodes, Map<String, String> species) {
        this.apiResponseCodes = apiResponseCodes;
        this.species = species;
    }

}

package kr.co.gravity.sample.service.dto;

import kr.co.gravity.sample.controller.dto.Species;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class Rocket implements Serializable {

	private int idx;
	private String id;
	private String name;
	private Species species;

	@Builder
	private Rocket(int idx, String id, String name, Species species) {
		this.idx = idx;
		this.id = id;
		this.name = name;
		this.species = species;
	}

}

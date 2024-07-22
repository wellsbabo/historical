package kr.co.gravity.sample.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class Pokemon implements Serializable {

	private int idx;
	private String code;
	private String name;

	@Builder
	private Pokemon(int idx, String code, String name) {
		this.idx = idx;
		this.code = code;
		this.name = name;
	}

}

package kr.co.gravity.sample.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Character implements Serializable {

	private int idx;
	private String id;
	private String name;
	private LocalDateTime registerDate;

	@Builder
	private Character(int idx, String id, String name, LocalDateTime registerDate) {
		this.idx = idx;
		this.id = id;
		this.name = name;
		this.registerDate = registerDate;
	}

}

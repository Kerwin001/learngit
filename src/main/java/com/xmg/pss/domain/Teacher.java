package com.xmg.pss.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher {
	private Long id;
	private String name;
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}
	
}

package com.xmg.pss.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Permission extends BasicDomain {

	private static final long serialVersionUID = 1L;

	private String name;
	private String expression;

	@Override
	public String toString() {
		return "Permission [name=" + name + ", expression=" + expression + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Permission) {
			return this.expression.equals(((Permission) obj).getExpression());
		}
		return false;
	}
}

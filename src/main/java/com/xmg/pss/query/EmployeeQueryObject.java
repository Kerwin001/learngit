package com.xmg.pss.query;

import lombok.Getter;
import lombok.Setter;

import com.xmg.pss.query.QueryObject;

@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
	private String keyword;
	private Long deptId;
}

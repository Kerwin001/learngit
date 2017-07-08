package com.xmg.pss.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Employee extends BasicDomain{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private Boolean admin;
	private Department dept;
	private List<Role> roles = new ArrayList<>();

	public String getRoleNames(){
		if(admin){
			return "[超级管理员]";
		}
		if(getRoles().size()==0){
			return "暂未分配角色";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Role role : roles) {
			sb.append(role.getName()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.append("]").toString();
	}
}

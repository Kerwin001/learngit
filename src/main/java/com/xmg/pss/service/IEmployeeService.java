package com.xmg.pss.service;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;

import java.util.List;

public interface IEmployeeService extends IBasicService<Employee>{

	PageResult pageQuery(QueryObject qo);
	/**
	 * 登录校验
	 * @param username
	 * @param password
	 */
	Employee checkLogin(String username, String password);

	void batchDelete(List<Long> ids);
}

package com.xmg.pss.service.impl;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.domain.Permission;
import com.xmg.pss.domain.Role;
import com.xmg.pss.mapper.EmployeeMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IEmployeeService;
import com.xmg.pss.util.MD5;
import com.xmg.pss.util.UserContext;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements IEmployeeService {
	@Setter
	private EmployeeMapper empMapper;

	@Override
	public void save(Employee e) {
		e.setPassword(MD5.encode(e.getPassword()));
		empMapper.save(e);
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			empMapper.updateRelation(e.getId(),role.getId());
		}
	}

	@Override
	public void update(Employee e) {
		empMapper.update(e);
		empMapper.deleteRelationByEmployee(e.getId());
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			empMapper.updateRelation(e.getId(),role.getId());
		}
	}

	@Override
	public void delete(Long id) {
		empMapper.deleteRelationByEmployee(id);
		empMapper.delete(id);

	}

	@Override
	public Employee get(Long id) {
		return empMapper.get(id);
	}

	@Override
	public List<Employee> list() {
		return empMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = empMapper.getTotalCount(qo);
		if (totalCount == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0, 1,
					qo.getPageSize());
		}
		List<Employee> listData = empMapper.getListData(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public Employee checkLogin(String username, String password) {
		Employee e = empMapper.checkLogin(username, MD5.encode(password));
		if(e==null){
			throw new RuntimeException("用户名或密码不正确,请重新输入!!");
		}
		Set<String> expressions = new HashSet<>();
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			List<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				expressions.add(permission.getExpression());
			}
		}
		UserContext.putPermissionSession(expressions);
		return e;
	}

	@Override
	public void batchDelete(List<Long> ids) {
		empMapper.batchDeleteRelation(ids);
		empMapper.batchDelete(ids);
	}

}

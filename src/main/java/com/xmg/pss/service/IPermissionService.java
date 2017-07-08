package com.xmg.pss.service;

import java.util.List;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;

public interface IPermissionService {
	void save(Permission p);

	void delete(Long id);

	List<Permission> list();

	PageResult pageQuery(QueryObject qo);
	
	void reload();
	
}

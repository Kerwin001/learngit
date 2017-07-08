package com.xmg.pss.mapper;

import java.util.List;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.query.QueryObject;

public interface PermissionMapper {
	void save(Permission p);

	void delete(Long id);

	List<Permission> list();

	List<Permission> getListData(QueryObject qo);
	
	Long getTotalCount(QueryObject qo);
	
	List<Permission> getPermissionsByRloe(Long roleId);
	/**
	 * 根据权限删除角色和权限的关系
	 * @param id 权限id
	 */
	void deleteRelationByPermission(Long id);
}

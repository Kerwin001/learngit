package com.xmg.pss.service.impl;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.domain.Role;
import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.mapper.RoleMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IRoleService;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class RoleServiceImpl implements IRoleService {

	@Setter
	private RoleMapper roleMapper;

	@Override
	public void save(Role r) {
		roleMapper.save(r);
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.updatePermissionRelation(r.getId(),permission.getId());
		}
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			roleMapper.updateMenuRelation(r.getId(),menu.getId());
		}
	}

	@Override
	public void update(Role r) {
		roleMapper.update(r);
		roleMapper.deletePermissionRelationByRole(r.getId());
		roleMapper.deleteMenuRelationByRole(r.getId());
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.updatePermissionRelation(r.getId(),permission.getId());
		}
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			roleMapper.updateMenuRelation(r.getId(),menu.getId());
		}

	}

	@Override
	public void delete(Long id) {
		roleMapper.deleteRelationFromEmpByRole(id);
		roleMapper.deletePermissionRelationByRole(id);
		roleMapper.deleteMenuRelationByRole(id);
		roleMapper.delete(id);
	}

	@Override
	public Role get(Long id) {
		return roleMapper.get(id);
	}

	@Override
	public List<Role> list() {
		return roleMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = roleMapper.getTotalCount(qo);
		if (totalCount == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0, 1,
					qo.getPageSize());
		}
		List<Role> listData = roleMapper.getListData(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public void batchDelete(List<Long> ids) {
		roleMapper.batchDeleteRelationByRole(ids);
		roleMapper.batchDeleteRelationFromEmpByRole(ids);
		roleMapper.batchdelete(ids);
	}
}

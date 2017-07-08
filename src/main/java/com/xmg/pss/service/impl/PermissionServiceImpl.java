package com.xmg.pss.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.mapper.PermissionMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IPermissionService;
import com.xmg.pss.util.RequiredPermission;
import com.xmg.pss.web.action.BasicAction;

public class PermissionServiceImpl implements IPermissionService,
		ApplicationContextAware {

	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(
			org.springframework.context.ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

	@Setter
	private PermissionMapper permissionMapper;

	@Override
	public void save(Permission p) {
		permissionMapper.save(p);
	}

	@Override
	public void delete(Long id) {
		permissionMapper.deleteRelationByPermission(id);
		permissionMapper.delete(id);
	}

	@Override
	public List<Permission> list() {
		return permissionMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = permissionMapper.getTotalCount(qo);
		if (totalCount == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0, 1,
					qo.getPageSize());
		}
		List<Permission> listData = permissionMapper.getListData(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public void reload() {
		List<Permission> permissions = permissionMapper.list();
		Map<String, BasicAction> actionMap = ctx
				.getBeansOfType(BasicAction.class);
		Collection<BasicAction> actions = actionMap.values();
		System.out.println(actions);
		for (BasicAction action : actions) {
			// 获取类名
			String actionName = action.getClass().getName();
			// 获取方法
			Method[] methods = action.getClass().getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(RequiredPermission.class)) {
					// 获取权限描述
					String name = method
							.getAnnotation(RequiredPermission.class).value();
					// 获取方法名
					String methodName = method.getName();
					// 拼接权限表达式
					String expression = actionName + ":" + methodName;
					Permission permission = new Permission();
					permission.setName(name);
					permission.setExpression(expression);
					if (!permissions.contains(permission)) {
						permissionMapper.save(permission);
					}
				}
			}
		}
	}

}

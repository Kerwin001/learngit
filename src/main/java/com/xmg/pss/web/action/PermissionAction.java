package com.xmg.pss.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.pss.domain.Permission;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IPermissionService;
import com.xmg.pss.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class PermissionAction extends BasicAction {

	private static final long serialVersionUID = 1L;
	@Setter
	private IPermissionService permissionService;
	@Getter
	private QueryObject qo = new QueryObject();
	@Getter
	private Permission permission = new Permission();
	@Override
	@RequiredPermission("权限列表")
	public String execute() throws Exception {
		PageResult page = permissionService.pageQuery(qo);
		ActionContext.getContext().put("page", page);
		return LIST;
	}
	@RequiredPermission("删除权限")
	public String delete() throws Exception {
		try {
			if(permission.getId()!=null){
                permissionService.delete(permission.getId());
            }
			putMsg("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败!");
		}
		return NONE;
	}
	@RequiredPermission("重新加载权限")
	public String reload() throws Exception {
		try {
			permissionService.reload();
			putMsg("权限加载成功!");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("权限加载失败!");
		}
		return NONE;
	}

}

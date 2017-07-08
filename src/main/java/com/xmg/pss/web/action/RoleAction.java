package com.xmg.pss.web.action;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.domain.Role;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IPermissionService;
import com.xmg.pss.service.IRoleService;
import com.xmg.pss.service.ISystemMenuService;
import com.xmg.pss.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoleAction extends BasicAction {

    private static final long serialVersionUID = 1L;
    @Setter
    private IRoleService roleService;
    @Setter
    private ISystemMenuService systemMenuService;
    @Setter
    private IPermissionService permissionService;
    @Getter
    private QueryObject qo = new QueryObject();
    @Getter
    private Role role = new Role();
    @Getter
    @Setter
    private List<Long> ids = new ArrayList<>();

    @Override
    @RequiredPermission("角色列表")
    public String execute() throws Exception {
        PageResult page = roleService.pageQuery(qo);
        putContext("page", page);
        return LIST;
    }

    @RequiredPermission("角色编辑")
    public String input() {
        List<Permission> permissions = permissionService.list();
        putContext("menus",systemMenuService.list());
        putContext("permissions", permissions);
        if (role.getId() != null) {
            role = roleService.get(role.getId());
        }
        return INPUT;
    }

    @RequiredPermission("角色删除")
    public String delete() throws Exception {
        try {
            if (role.getId() != null) {
                roleService.delete(role.getId());
                putMsg("删除成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败!");
        }
        return NONE;
    }

    @RequiredPermission("角色批量删除")
    public String batchDelete() throws Exception {
        try {
            if (ids.size() > 0) {
                roleService.batchDelete(ids);
                putMsg("批量删除成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("批量删除失败!");
        }
        return NONE;
    }

    @RequiredPermission("角色修改")
    public String saveOrUpdate() {
        try {
            if (role.getId() != null) {
                roleService.update(role);
                putMsg("修改成功!");
            } else {
                roleService.save(role);
                putMsg("保存成功!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}

package com.xmg.pss.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.pss.domain.Department;
import com.xmg.pss.domain.Employee;
import com.xmg.pss.domain.Role;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.EmployeeQueryObject;
import com.xmg.pss.service.IDepartmentService;
import com.xmg.pss.service.IEmployeeService;
import com.xmg.pss.service.IRoleService;
import com.xmg.pss.util.RequiredPermission;
import com.xmg.pss.util.UserContext;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAction extends BasicAction {

    private static final long serialVersionUID = 1L;
    @Setter
    private IRoleService roleService;
    @Setter
    private IEmployeeService empService;
    @Setter
    private IDepartmentService deptService;
    @Getter
    private Employee e = new Employee();
    @Getter
    private EmployeeQueryObject qo = new EmployeeQueryObject();
    @Setter
    private String username;
    @Setter
    private String password;
    @Getter
    @Setter
    private List<Long> ids = new ArrayList<>();
    @Setter
    private String repassword;

    @Override
    @RequiredPermission("员工列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            System.out.println(this.getClass());
            PageResult page = empService.pageQuery(qo);
            List<Department> depts = deptService.list();
            ActionContext.getContext().put("page", page);
            ActionContext.getContext().put("depts", depts);
//			int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("发生异常,请联系管理员");
        }
        return LIST;
    }

    @RequiredPermission("员工删除")
    public String delete() throws Exception {
        try {
            if (e.getId() != null) {
                int i=1/0;
                empService.delete(e.getId());
                putMsg("删除成功!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
                putMsg("删除失败!");
        }
        return NONE;
    }

    @RequiredPermission("员工编辑")
    public String input() {

        List<Role> roles = roleService.list();
        List<Department> depts = deptService.list();
        ActionContext.getContext().put("depts", depts);
        ActionContext.getContext().put("roles", roles);
        if (e.getId() != null) {
            e = empService.get(e.getId());
        }

        return INPUT;
    }

    @RequiredPermission("员工修改")
    public String saveOrUpdate() {
        try {
//            int i = 1 / 0;
            if (e.getId() != null) {
                empService.update(e);
                addActionMessage("修改成功!");
            } else {
                empService.save(e);
                addActionMessage("保存成功!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            addActionError("发生异常,请联系管理员");
        }
        return SUCCESS;
    }

    @RequiredPermission("批量删除员工")
    public String batchDelete() throws Exception {
        try {
            if (ids.size() > 0) {
                System.out.println(ids);
                empService.batchDelete(ids);
                putMsg("批量删除成功!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            putMsg("批量删除失败!");
        }
        return NONE;
    }


    public String chekLogin() {
        try {
            Employee emp = empService.checkLogin(username, password);
            UserContext.putUserSession(emp);
            return SUCCESS;
        } catch (Exception e) {
            addActionError(e.getMessage());
        }
        return LOGIN;
    }

    public String logout() {
        ActionContext.getContext().getSession().remove("User_IN_SESSION");
        return LOGIN;
    }
}

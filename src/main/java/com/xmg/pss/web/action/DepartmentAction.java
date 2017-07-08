package com.xmg.pss.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import com.opensymphony.xwork2.ActionContext;
import com.xmg.pss.domain.Department;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IDepartmentService;
import com.xmg.pss.util.RequiredPermission;

public class DepartmentAction extends BasicAction {

    private static final long serialVersionUID = 1L;
    @Getter
    private Department dept = new Department();

    @Setter
    private IDepartmentService deptService;
    @Getter
    private QueryObject qo = new QueryObject();

    @Override
    @RequiredPermission("部门列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        PageResult page = deptService.pageQuery(qo);
        ActionContext.getContext().put("page", page);
        return LIST;
    }

    @RequiredPermission("部门删除")
    public String delete() throws Exception {
        try {
//            int i=1/0;
            if (dept.getId() != null) {
                deptService.delete(dept.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败");
        }
        return NONE;
    }

    @RequiredPermission("部门编辑")
    public String input() throws Exception {
        if (dept.getId() != null) {
            dept = deptService.get(dept.getId());
        }
        return INPUT;
    }

    @RequiredPermission("部门更新")
    public String saveOrUpdate() throws Exception {
            if (dept.getId() != null) {
                deptService.update(dept);
                addActionMessage("修改成功!");
            } else {
                deptService.save(dept);
                addActionMessage("保存成功!");
            }
        return SUCCESS;
    }

}

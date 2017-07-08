package com.xmg.pss.util;

import com.xmg.pss.domain.Employee;
import org.apache.struts2.ServletActionContext;

import java.util.Set;

/**
 * Created by Administrator on 2017/6/22 0022.
 */
public class UserContext {
    private UserContext() {
    }

    public static void putPermissionSession(Set<String> expressions) {
        ServletActionContext.getContext().getSession().put("PERMISSON_IN_SESSION", expressions);
    }

    public static void putUserSession(Employee emp) {

        ServletActionContext.getContext().getSession().put("User_IN_SESSION", emp);
    }

    public static Set<String> getPermissionSession() {
        return (Set<String>) ServletActionContext.getContext().getSession().get("PERMISSON_IN_SESSION");
    }

    public static Employee getUserSession() {
        return (Employee) ServletActionContext.getContext().getSession().get("User_IN_SESSION");
    }

}

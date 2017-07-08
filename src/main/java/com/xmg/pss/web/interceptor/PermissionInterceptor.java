package com.xmg.pss.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xmg.pss.domain.Employee;
import com.xmg.pss.util.RequiredPermission;
import com.xmg.pss.util.UserContext;

import java.lang.reflect.Method;
import java.util.Set;

public class PermissionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee emp = UserContext.getUserSession();

		if(emp.getAdmin()){
			return invocation.invoke();
		}
		
		Class<?> actionClz = invocation.getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		Method method = actionClz.getMethod(methodName);
		if(!method.isAnnotationPresent(RequiredPermission.class)){
			return invocation.invoke();
		}
		
		String expression = actionClz.getName()+":"+methodName;
		Set<String> expressions = UserContext.getPermissionSession();
		if(expressions.contains(expression)){
			return invocation.invoke();
		}
		return "nopermission";
	}

}

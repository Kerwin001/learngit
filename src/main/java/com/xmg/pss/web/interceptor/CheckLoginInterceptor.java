package com.xmg.pss.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xmg.pss.domain.Employee;
import com.xmg.pss.util.UserContext;

public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee emp = UserContext.getUserSession();
		if(emp==null){
			return ActionSupport.LOGIN;
		}
		return invocation.invoke();
	}

}

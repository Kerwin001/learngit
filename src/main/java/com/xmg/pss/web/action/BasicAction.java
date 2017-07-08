package com.xmg.pss.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

public class BasicAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    public static final String LIST = "list";

    public void putMsg(String msg) throws IOException {
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(msg);
    }

    public void putContext(String str, Object obj) {
        ActionContext.getContext().put(str, obj);
    }

    public void putJson(Object obj) throws Exception {
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(obj));
    }
}

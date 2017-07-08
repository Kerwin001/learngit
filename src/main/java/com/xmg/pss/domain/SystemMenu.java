package com.xmg.pss.domain;

import com.xmg.pss.generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ObjectProp("系统菜单")
public class SystemMenu extends BasicDomain {
    @ObjectProp("菜单名称")
    private String name;
    @ObjectProp("URL")
    private String url;
    @ObjectProp("菜单编码")
    private String sn;
    @ObjectProp("上级菜单")
    private SystemMenu parent;
    public Map<String,Object> toJson(){
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("id",getId());
        jsonMap.put("pId",getParent().getId());
        jsonMap.put("name",name);
        jsonMap.put("action",url);
        return jsonMap;
    }
}

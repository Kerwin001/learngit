package com.xmg.pss.domain;

import com.xmg.pss.generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@ObjectProp("仓库管理")
public class Depot  extends BasicDomain{
    @ObjectProp("仓库名称")
    private String name;
    @ObjectProp("仓库地址")
    private String location;
}

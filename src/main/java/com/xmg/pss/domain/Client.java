package com.xmg.pss.domain;

import com.xmg.pss.generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ObjectProp("客户")
public class Client extends BasicDomain {
    @ObjectProp("客户名称")
    private String name;
    @ObjectProp("客户编码")
    private String sn;
    @ObjectProp("联系电话")
    private String phone;
}

package com.xmg.pss.domain;

import com.xmg.pss.generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ObjectProp("供应商管理")
public class Supplier extends BasicDomain {
    @ObjectProp("供应商名称")
    private String name;
    @ObjectProp("联系电话")
    private String phone;
    @ObjectProp("地址")
    private String address;
}

package com.xmg.pss.domain;

import com.xmg.pss.generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ObjectProp("品牌管理")
public class Brand extends BasicDomain {
    @ObjectProp("品牌名称")
    private String name;
    @ObjectProp("品牌编码")
    private String sn;
}

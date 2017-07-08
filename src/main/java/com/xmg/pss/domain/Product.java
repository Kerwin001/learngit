package com.xmg.pss.domain;

import com.alibaba.fastjson.JSON;
import com.xmg.pss.generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ObjectProp("商品管理")
public class Product extends BasicDomain {
    @ObjectProp("货品名称")
    private String name;
    @ObjectProp("货品编码")
    private String sn;
    @ObjectProp("成本价格")
    private BigDecimal costPrice;
    @ObjectProp("销售价格")
    private BigDecimal salePrice;
    @ObjectProp("货品图片")
    private String imagePath;
    @ObjectProp("备注")
    private String intro;
    @ObjectProp("货品品牌")
    private Brand brand;
    public String getSmallImgPath(){
        if(StringUtils.isNotEmpty(imagePath)){
            int index = imagePath.lastIndexOf(".");
            return imagePath.substring(0,index)+"_small"+imagePath.substring(index);
        }
        return "";
    }
    public String getProductJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("name",name);
        map.put("brand",getBrand().getName());
        map.put("costPrice",costPrice==null?"":costPrice);
        map.put("salePrice",salePrice==null?"":salePrice);
        return JSON.toJSONString(map);
    }
}

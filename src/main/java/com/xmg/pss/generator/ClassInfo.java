package com.xmg.pss.generator;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import com.xmg.pss.domain.BasicDomain;

@Setter
@Getter
@ToString
public class ClassInfo {
    private String packageName;//包名
    private String className;//类名(简单名称)
    private String objectName;//类名的小写
    private String objectCNName;//类的中文名称
    //对于类中的字段列表信息 key 英文名称 --> value  中文名称
    private Map<String,String> fieldMap=new HashMap<>();

    public ClassInfo(Class clazz) throws Exception{
        packageName=clazz.getPackage().getName();
        //com.xmg.wms.domain ==>com.xmg.wms
        packageName= packageName.substring(0,packageName.lastIndexOf("."));
        className=clazz.getSimpleName();
        objectName=className.substring(0,1).toLowerCase()+className.substring(1);
        ObjectProp objectProp=(ObjectProp)clazz.getAnnotation(ObjectProp.class);
        if(objectProp==null){//没有注解
            objectCNName=objectName;
        }else{
            objectCNName=objectProp.value();
        }
        //通过内省机制获取字段信息
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, BasicDomain.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getName());
            //获取字段上的注解
            objectProp=(ObjectProp)clazz.getDeclaredField(pd.getName()).getAnnotation(ObjectProp.class);
            if(objectProp==null){//没有注解
                fieldMap.put(pd.getName(),pd.getName());
            }else{
                fieldMap.put(pd.getName(),objectProp.value());
            }
        }
    }

}

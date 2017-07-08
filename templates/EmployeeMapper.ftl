<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-Mapper.dtd">
<mapper namespace="${pkgName}.mapper.${objName}">

	<resultMap type="${tableName}" id="base_mapping">
		<id column="id" property="id"/>
		<#assign feilds=feildMap?keys>
		<#list feilds as name>
		<result column="${name}" property="${name}"/>
		</#list>
	</resultMap>
	<insert id="save" useGeneratedKeys="true" keyColumn="id" keyProperty="id">
		INSERT INTO ${tableName}(<#list feilds as name>${name}<#if name_has_next>,</#if></#list>)
		VALUES(<#list feilds as name>${r"#"}{${name}}<#if name_has_next>,</#if></#list>)
	</insert>
	<update id="update">
		UPDATE ${tableName} 
		<set>
			<#list feilds as name>
			<if test="${name}!=null">${name}=${r"#"}{${name}},</if>
			</#list>
		</set>
		WHERE id=${r"#"}{id}
	</update>
</mapper>
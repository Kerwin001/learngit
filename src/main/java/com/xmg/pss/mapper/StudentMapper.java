package com.xmg.pss.mapper;

import org.apache.ibatis.annotations.Param;

import com.xmg.pss.domain.Student;

public interface StudentMapper {
	
	void save(Student s);
	
	Student get(Long id);
	
	/**
	 * 维护老师和学生的关系,将数据添加到中间表
	 * @param teacherId 老师id
	 * @param studentId 学生id
	 */
	void updateRelation(@Param("teacherId")Long teacherId,@Param("studentId")Long studentId);
}

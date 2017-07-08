package com.xmg.pss.mapper;

import com.xmg.pss.domain.Teacher;

public interface TeacherMapper {
	
	void save(Teacher t);
	
	Teacher get(Long id);
}

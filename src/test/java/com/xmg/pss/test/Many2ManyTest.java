package com.xmg.pss.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Student;
import com.xmg.pss.domain.Teacher;
import com.xmg.pss.mapper.StudentMapper;
import com.xmg.pss.mapper.TeacherMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Many2ManyTest {
	@Autowired
	private StudentMapper stuMapper;
	@Autowired
	private TeacherMapper teaMapper;
	@Test
	public void testSave() throws Exception {
		Student s1 = new Student();
		Student s2 = new Student();
		Teacher t1 = new Teacher();
		Teacher t2 = new Teacher();
		
		s1.setName("花满楼");
		s2.setName("司空摘星");
		t1.setName("陆小凤");
		t2.setName("西门吹雪");
		
		stuMapper.save(s1);
		stuMapper.save(s2);
		teaMapper.save(t1);
		teaMapper.save(t2);
		
		List<Teacher> list1 = s1.getTeachers();
		List<Teacher> list2 = s2.getTeachers();
		list1.add(t1);
		list1.add(t2);
		list2.add(t1);
		list2.add(t2);
		
		for (Teacher t : list1) {
			stuMapper.updateRelation(t.getId(), s1.getId());
		}
		for (Teacher t : list2) {
			stuMapper.updateRelation(t.getId(), s2.getId());
		}
	}
	@Test
	public void testGet() throws Exception {
		Student s = stuMapper.get(1L);
		System.out.println(s);
		System.out.println(s.getTeachers());
	}
}

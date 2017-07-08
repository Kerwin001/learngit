package com.xmg.pss.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Department;
import com.xmg.pss.service.IDepartmentService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {
	@Autowired
	private IDepartmentService deptService;
	
	
	@Test
	public void testDepartmentService() throws Exception {
		Department dept = new Department();
		dept.setName("市场部");
		deptService.save(dept);
	}
	@Test
	public void testUpdate() throws Exception {
		Department dept = new Department();
		dept.setName("市场部");
		dept.setId(5L);
		dept.setSn("13");
		deptService.update(dept);
	}
	@Test
	public void testDelete() throws Exception {
		deptService.delete(2L);
	}
	@Test
	public void testGet() throws Exception {
		Department dept = deptService.get(5L);
		System.out.println(dept);
	}
	@Test
	public void testList() throws Exception {
		List<Department> list = deptService.list();		
		for (Department dept : list) {
			System.out.println(dept);
		}
	}
}

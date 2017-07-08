package com.xmg.pss.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.EmployeeQueryObject;
import com.xmg.pss.service.IEmployeeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {
	@Autowired
	private IEmployeeService empService;
	
	
	@Test
	public void testEmployeeService() throws Exception {
		Employee e = new Employee();
		e.setName("小杰");
		e.setAge(12);
		e.setPassword("12345");
		e.setAdmin(true);
		e.setEmail("nigo@qq.com");
		empService.save(e);
	}
	@Test
	public void testUpdate() throws Exception {
		Employee e = new Employee();
		e.setName("奇牙");
		e.setAge(13);
		e.setPassword("23456");
		e.setAdmin(true);
		e.setEmail("kilo@qq.com");
		e.setId(2L);
		empService.update(e);
	}
	@Test
	public void testDelete() throws Exception {
		empService.delete(2L);
	}
	@Test
	public void testGet() throws Exception {
		Employee e = empService.get(1L);
		System.out.println(e);
	}
	@Test
	public void testList() throws Exception {
		List<Employee> list = empService.list();		
		for (Employee e : list) {
			System.out.println(e);
		}
	}
	@Test
	public void testQuery() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setKeyword("杰");
		PageResult result = empService.pageQuery(qo);
		System.out.println(result);
	}
}

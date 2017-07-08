package com.xmg.pss.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Role;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoleTest {
	@Autowired
	private IRoleService roleService;

	@Test
	public void testSave() throws Exception {
		Role r = new Role();
		r.setName("部门管理员");
		r.setSn("DEPT_MGR");
		roleService.save(r );
	}

	@Test
	public void testUpdate() throws Exception {
		Role r = new Role();
		r.setName("员工管理员");
		r.setSn("EMP_MGR");
		r.setId(1L);
		roleService.update(r);
	}
	@Test
	public void testDelete() throws Exception {
		roleService.delete(1L);
	}
	@Test
	public void testGet() throws Exception {
		Role role = roleService.get(1L);
		System.out.println(role);
	}
	@Test
	public void testList() throws Exception {
		List<Role> list = roleService.list();
		for (Role role : list) {
			System.out.println(role);
		}
	}
	@Test
	public void testPageQuery() throws Exception {
		QueryObject qo = new QueryObject();
		PageResult page = roleService.pageQuery(qo );
		System.out.println(page);
	}
}

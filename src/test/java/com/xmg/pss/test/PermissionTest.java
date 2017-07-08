package com.xmg.pss.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Permission;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;
import com.xmg.pss.service.IPermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PermissionTest {
	
	@Autowired
	private IPermissionService permissionService;

	@Test
	public void testSave() throws Exception {
		Permission p = new Permission();
		p.setName("部门编辑");
		permissionService.save(p);
	}
	@Test
	public void testdelete() throws Exception {
		permissionService.delete(1L);
	}
	@Test
	public void testPageQuery() throws Exception {
		QueryObject qo = new QueryObject();
		PageResult page= permissionService.pageQuery(qo);
		System.out.println(page);
	}
}

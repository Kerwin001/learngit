package com.xmg.pss.service;

import com.xmg.pss.domain.Department;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;

public interface IDepartmentService extends IBasicService<Department>{
	PageResult pageQuery(QueryObject qo);
}

package com.xmg.pss.service;

import com.xmg.pss.domain.Role;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.QueryObject;

import java.util.List;

public interface IRoleService extends IBasicService<Role> {
	PageResult pageQuery(QueryObject qo);

    void batchDelete(List<Long> ids);
}

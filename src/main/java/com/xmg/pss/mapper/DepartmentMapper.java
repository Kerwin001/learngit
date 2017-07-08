package com.xmg.pss.mapper;

import java.util.List;

import com.xmg.pss.domain.Department;
import com.xmg.pss.query.QueryObject;

public interface DepartmentMapper extends BaseMapper<Department>{

	Long getTotalCount(QueryObject qo);

	List<Department> getListData(QueryObject qo);
	/**
	 * 根据部门移除部门和员工的关系,将该部门的员工的部门id设置为null
	 * @param id 部门id
	 */
	void deleteRelationByDept(Long id);
	
}

package com.xmg.pss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.query.QueryObject;

public interface EmployeeMapper extends BaseMapper<Employee>{


	/**
	 * 根据高级查询条件查询的总记录数
	 * 
	 * @return 查询到的总记录数
	 */
	Long getTotalCount(QueryObject qo);

	/**
	 * 高级查询
	 * 
	 * @return 查找到的Employee对象集合
	 */
	List<Employee> getListData(QueryObject qo);
	/**
	 * 维护角色和员工之间的关系
	 * @param eid	员工id
	 * @param rid	角色id
	 */
	void updateRelation(@Param("eId")Long eid,@Param("rId")Long rid);

	/**
	 * 根据id批量删除员工和角色的关系
	 * @param ids 员工id的集合
	 */
	void batchDeleteRelation(List<Long> ids);
	/**
	 * 根据员工id删除和角色的关系
	 * @param id
	 */
	void deleteRelationByEmployee(Long eid);
	/**
	 * 登录检验,用户名和密码匹配则返回员工对象,否则返回null
	 * @param username 用户名
	 * @param password 密码
	 * @return 员工对象
	 */
	Employee checkLogin(@Param("username")String username,@Param("password")String password);

}

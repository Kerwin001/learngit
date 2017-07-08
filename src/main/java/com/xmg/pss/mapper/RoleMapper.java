package com.xmg.pss.mapper;

import com.xmg.pss.domain.Role;
import com.xmg.pss.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
	void save(Role r);

	void delete(Long id);

	void update(Role r);
	
	Role get(Long id);
	
	List<Role> list();

	List<Role> getListData(QueryObject qo);

	Long getTotalCount(QueryObject qo);

	void updatePermissionRelation(@Param("rId")Long rId,@Param("pId")Long pId);
	/**
	 * 根据角色删除角色和权限的关系
	 * @param rid 角色id
	 */
	void deletePermissionRelationByRole(Long rid);
	/**
	 * 根据角色删除角色和员工的关系
	 * @param id
	 */
	void deleteRelationFromEmpByRole(Long id);

	/**
	 * 根据角色id批量删除角色和权限的关系
	 * @param ids
	 */
    void batchDeleteRelationByRole(List<Long> ids);

	/**
	 * 根据角色id批量删除和员工的关系
	 * @param ids
	 */
	void batchDeleteRelationFromEmpByRole(List<Long> ids);

	/**
	 * 根据id批量删除角色
	 * @param ids
	 */
	void batchdelete(List<Long> ids);

	/**
	 * 维护角色和菜单的关系
	 * @param rid
	 * @param mid
	 */
    void updateMenuRelation(@Param("rId")Long rid,@Param("mId") Long mid);

	/**
	 * 根据角色id删除和菜单的关系
	 * @param id
	 */
	void deleteMenuRelationByRole(Long id);
}

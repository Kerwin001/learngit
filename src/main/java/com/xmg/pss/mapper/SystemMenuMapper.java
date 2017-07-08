package com.xmg.pss.mapper;

import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.query.SystemMenuQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMenuMapper {
	void save(SystemMenu entity);
	void update(SystemMenu entity);
	void delete(Long id);
    SystemMenu get(Long id);
	List<SystemMenu> list();
    Long getTotalCount(SystemMenuQueryObject qo);
    List<SystemMenu> pageQuery(SystemMenuQueryObject qo);

	/**
	 * 根据一级菜单编码查询子菜单
	 * @param sn 一级菜单的编码
	 * @return 子菜单对象集合
	 */
	List<SystemMenu> getMenusBySn(String sn);

	/**
	 * 根据当前用户和一级菜单编码查询菜单子菜单
	 * @param sn 一级菜单的编码
	 * @param id 当前用户id
	 * @return 子菜单对象集合
	 */
	List<SystemMenu> getMenusByUser(@Param("sn") String sn,@Param("eId") Long id);
}
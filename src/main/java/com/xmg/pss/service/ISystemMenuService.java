package com.xmg.pss.service;
import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.vo.SystemMenuVO;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.SystemMenuQueryObject;

import java.util.List;
import java.util.Map;

public interface ISystemMenuService {
	void delete(Long id);
	void save(SystemMenu entity);
    SystemMenu get(Long id);
    List<SystemMenu> list();
	void update(SystemMenu entity);
	PageResult pageQuery(SystemMenuQueryObject qo);

	/**
	 * 根据id获取所有上级目录
	 * @param id 当前目录id
	 * @return 所有上级目录的VO对象集合
	 */
    List<SystemMenuVO> getParents(Long id);

	/**
	 * 根据用户和一级菜单的编码获取子菜单
	 * @param sn 一级菜单的编码
	 * @return 子菜单的ztree的json映射集合
	 */
    List<Map<String,Object>> getMenusBySn(String sn);
}

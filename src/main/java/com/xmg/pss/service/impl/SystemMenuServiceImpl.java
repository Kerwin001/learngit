package com.xmg.pss.service.impl;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.vo.SystemMenuVO;
import com.xmg.pss.mapper.SystemMenuMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.SystemMenuQueryObject;
import com.xmg.pss.service.ISystemMenuService;
import com.xmg.pss.util.UserContext;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SystemMenuServiceImpl implements ISystemMenuService {
	@Setter
	private SystemMenuMapper systemMenuMapper;
	
	public void  delete(Long id) {
		  systemMenuMapper.delete(id);
	}

	public void save(SystemMenu entity) {
		  systemMenuMapper.save(entity);
	}

	public SystemMenu get(Long id) {
		return systemMenuMapper.get(id);
	}

	public List<SystemMenu> list() {
		return systemMenuMapper.list();
	}

	public void update(SystemMenu entity) {
		  systemMenuMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SystemMenuQueryObject qo) {
		Long count = systemMenuMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<SystemMenu> result = systemMenuMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}

	@Override
	public List<SystemMenuVO> getParents(Long id) {
		SystemMenu current = systemMenuMapper.get(id);
		List<SystemMenuVO> parents = new ArrayList<>();
		while(current!=null){
			SystemMenuVO vo = new SystemMenuVO();
			vo.setId(current.getId());
			vo.setName(current.getName());
			current = current.getParent();
			parents.add(vo);
		}
		Collections.reverse(parents);
		return parents;
	}

	@Override
	public List<Map<String, Object>> getMenusBySn(String sn) {
		Employee user = UserContext.getUserSession();
		List<Map<String, Object>> jsonList = new ArrayList<>();
		List<SystemMenu> menus=null;
		if(user.getAdmin()){
			menus = systemMenuMapper.getMenusBySn(sn);

		}else{
			menus = systemMenuMapper.getMenusByUser(sn,user.getId());
		}
		if(menus!=null) {
			for (SystemMenu menu : menus) {
				jsonList.add(menu.toJson());
			}
		}
		return jsonList;
	}
}

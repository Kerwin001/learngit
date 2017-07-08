package com.xmg.pss.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.Client;
import com.xmg.pss.mapper.ClientMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ClientQueryObject;
import com.xmg.pss.service.IClientService;
import lombok.Setter;
public class ClientServiceImpl implements IClientService {
	@Setter
	private ClientMapper clientMapper;
	
	public void  delete(Long id) {
		  clientMapper.delete(id);
	}

	public void save(Client entity) {
		  clientMapper.save(entity);
	}

	public Client get(Long id) {
		return clientMapper.get(id);
	}

	public List<Client> list() {
		return clientMapper.list();
	}

	public void update(Client entity) {
		  clientMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ClientQueryObject qo) {
		Long count = clientMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Client> result = clientMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}

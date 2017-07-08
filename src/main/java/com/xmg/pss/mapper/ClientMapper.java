package com.xmg.pss.mapper;

import com.xmg.pss.domain.Client;
import com.xmg.pss.query.ClientQueryObject;
import java.util.List;

public interface ClientMapper {
	void save(Client entity);
	void update(Client entity);
	void delete(Long id);
    Client get(Long id);
	List<Client> list();
    Long getTotalCount(ClientQueryObject qo);
    List<Client> pageQuery(ClientQueryObject qo);
}
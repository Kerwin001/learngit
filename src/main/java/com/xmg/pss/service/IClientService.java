package com.xmg.pss.service;
import java.util.List;
import com.xmg.pss.domain.Client;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.ClientQueryObject;

public interface IClientService {
	void delete(Long id);
	void save(Client entity);
    Client get(Long id);
    List<Client> list();
	void update(Client entity);
	PageResult pageQuery(ClientQueryObject qo);
}

package com.xmg.pss.web.action;

import com.xmg.pss.domain.Client;
import com.xmg.pss.query.ClientQueryObject;
import com.xmg.pss.service.IClientService;
import com.xmg.pss.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class ClientAction extends BasicAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IClientService clientService;

	@Getter
	private ClientQueryObject qo=new ClientQueryObject();

	@Getter
	private Client client = new Client();
	
	@RequiredPermission("客户列表")
	public String execute(){
		try {
			putContext("page", clientService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("客户编辑")
	public String input() {
		try {
			if (client.getId() != null) {
                client = clientService.get(client.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("客户保存/更新")
	public String saveOrUpdate() {
		try {
			if (client.getId() == null) {
                clientService.save(client);
				addActionMessage("增加成功");
            } else {
                clientService.update(client);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("客户删除")
	public String delete()  throws  Exception {
		try {
			if (client.getId() != null) {
                clientService.delete(client.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}

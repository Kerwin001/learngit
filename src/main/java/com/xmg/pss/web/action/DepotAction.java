package com.xmg.pss.web.action;

import com.xmg.pss.domain.Depot;
import com.xmg.pss.query.DepotQueryObject;
import com.xmg.pss.service.IDepotService;
import com.xmg.pss.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class DepotAction extends BasicAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IDepotService depotService;

	@Getter
	private DepotQueryObject qo=new DepotQueryObject();

	@Getter
	private Depot depot = new Depot();
	
	@RequiredPermission("仓库列表")
	public String execute(){
		try {
			putContext("page", depotService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("仓库编辑")
	public String input() {
		try {
			if (depot.getId() != null) {
                depot = depotService.get(depot.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("仓库保存/更新")
	public String saveOrUpdate() {
		try {
			if (depot.getId() == null) {
                depotService.save(depot);
				addActionMessage("增加成功");
            } else {
                depotService.update(depot);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("仓库删除")
	public String delete()  throws  Exception {
		try {
			if (depot.getId() != null) {
                depotService.delete(depot.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}

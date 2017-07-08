package com.xmg.pss.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.pss.domain.StockOutcomeBill;
import com.xmg.pss.query.StockOutcomeBillQueryObject;
import com.xmg.pss.service.IClientService;
import com.xmg.pss.service.IDepotService;
import com.xmg.pss.service.IStockOutcomeBillService;
import com.xmg.pss.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class StockOutcomeBillAction extends BasicAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockOutcomeBillService stockOutcomeBillService;
	@Setter
	private IClientService clientService;
	@Setter
	private IDepotService depotService;
	@Getter
	private StockOutcomeBillQueryObject qo=new StockOutcomeBillQueryObject();

	@Getter
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
	
	@RequiredPermission("销售出库单列表")
    @InputConfig(methodName = "input")
	public String execute(){
		try {
			putContext("clients",clientService.list());
			putContext("depots",depotService.list());
			putContext("page", stockOutcomeBillService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("销售出库单编辑")
	public String input() {
		try {
			putContext("clients",clientService.list());
			putContext("depots",depotService.list());
			if (stockOutcomeBill.getId() != null) {
                stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("销售出库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockOutcomeBill.getId() == null) {
                stockOutcomeBillService.save(stockOutcomeBill);
				addActionMessage("增加成功");
            } else {
                stockOutcomeBillService.update(stockOutcomeBill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("销售出库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
                stockOutcomeBillService.delete(stockOutcomeBill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("销售出库单查看")
	public String show() {
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}
	@RequiredPermission("销售出库单审核")
	public String audit()  throws  Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBillService.audit(stockOutcomeBill);
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}

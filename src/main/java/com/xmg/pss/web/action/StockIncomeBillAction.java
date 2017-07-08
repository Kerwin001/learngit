package com.xmg.pss.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.pss.domain.StockIncomeBill;
import com.xmg.pss.query.StockIncomeBillQueryObject;
import com.xmg.pss.service.IDepotService;
import com.xmg.pss.service.IStockIncomeBillService;
import com.xmg.pss.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class StockIncomeBillAction extends BasicAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockIncomeBillService stockIncomeBillService;
	@Setter
	private IDepotService depotService;
	@Getter
	private StockIncomeBillQueryObject qo=new StockIncomeBillQueryObject();

	@Getter
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();
	
	@RequiredPermission("采购入库单列表")
    @InputConfig(methodName = "input")
	public String execute(){
		try {
			putContext("depots",depotService.list());
			putContext("page", stockIncomeBillService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("采购入库单编辑")
	public String input() {
		try {
			putContext("depots",depotService.list());
			if (stockIncomeBill.getId() != null) {
                stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("采购入库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockIncomeBill.getId() == null) {
                stockIncomeBillService.save(stockIncomeBill);
				addActionMessage("增加成功");
            } else {
                stockIncomeBillService.update(stockIncomeBill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("采购入库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockIncomeBill.getId() != null) {
                stockIncomeBillService.delete(stockIncomeBill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购入库单查看")
	public String show() {
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}
	@RequiredPermission("采购入库单审核")
	public String audit()  throws  Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				stockIncomeBillService.audit(stockIncomeBill);
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}

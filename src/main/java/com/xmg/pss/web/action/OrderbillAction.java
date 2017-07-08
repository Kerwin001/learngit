package com.xmg.pss.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.pss.domain.Orderbill;
import com.xmg.pss.query.OrderbillQueryObject;
import com.xmg.pss.service.IOrderbillService;
import com.xmg.pss.service.ISupplierService;
import com.xmg.pss.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class OrderbillAction extends BasicAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IOrderbillService orderbillService;

	@Setter
	private ISupplierService supplierService;
	@Getter
	private OrderbillQueryObject qo=new OrderbillQueryObject();

	@Getter
	private Orderbill orderbill = new Orderbill();

	@RequiredPermission("采购订单列表")
    @InputConfig(methodName = "input")
	public String execute(){
		try {
			putContext("suppliers",supplierService.list());
			putContext("page", orderbillService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("采购订单编辑")
	public String input() {
		putContext("suppliers",supplierService.list());
		try {
			if (orderbill.getId() != null) {
                orderbill = orderbillService.get(orderbill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("采购订单保存/更新")
	public String saveOrUpdate() {
		try {
			if (orderbill.getId() == null) {
                orderbillService.save(orderbill);
				addActionMessage("增加成功");
            } else {
                orderbillService.update(orderbill);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("采购订单删除")
	public String delete()  throws  Exception {
		try {
			if (orderbill.getId() != null) {
                orderbillService.delete(orderbill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购订单审核")
	public String audit()  throws  Exception {
		try {
			if (orderbill.getId() != null) {
                orderbillService.audit(orderbill);
				putMsg("审核成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购订单查看")
	public String show() {
		putContext("suppliers",supplierService.list());
		try {
			if (orderbill.getId() != null) {
				orderbill = orderbillService.get(orderbill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}

}

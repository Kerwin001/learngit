package com.xmg.pss.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.pss.domain.Product;
import com.xmg.pss.query.ProductQueryObject;
import com.xmg.pss.service.IBrandService;
import com.xmg.pss.service.IProductService;
import com.xmg.pss.util.FileUploadUtil;
import com.xmg.pss.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class ProductAction extends BasicAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IProductService productService;
    @Setter
    private IBrandService brandService;
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();

    @Getter
    private Product product = new Product();
    @Setter
    private File pic;
    @Setter
    private String picFileName;
    @RequiredPermission("商品列表")
//    @InputConfig(methodName = "execute")
    public String execute() {
        try {
        putContext("brands",brandService.list());
            putContext("page", productService.pageQuery(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("商品编辑")
    public String input() {
        try {
            putContext("brands",brandService.list());
            if (product.getId() != null) {
                product = productService.get(product.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("商品保存/更新")
    @InputConfig(methodName = "execute")
    public String saveOrUpdate() {
        try {
            if(pic!=null){
                String path = FileUploadUtil.uploadFile(pic, picFileName);
                product.setImagePath(path);
            }
            if (product.getId() == null) {
                productService.save(product);
                addActionMessage("增加成功");
            } else {
                if(pic!=null){
                    Product temp = productService.get(product.getId());
                    FileUploadUtil.deleteFile(temp.getImagePath());
                    String path = FileUploadUtil.uploadFile(pic, picFileName);
                    product.setImagePath(path);
                }
                productService.update(product);
                addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("商品删除")
    public String delete() throws Exception {
        try {
            if (product.getId() != null) {
                product=productService.get(product.getId());
                if(StringUtils.isNotEmpty(product.getImagePath())){
                    FileUploadUtil.deleteFile(product.getImagePath());
                }
                productService.delete(product.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg(e.getMessage());
        }
        return NONE;
    }
    public String selectProduct(){
        try {
            putContext("brands",brandService.list());
            putContext("page", productService.pageQuery(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "selectProduct";
    }
}

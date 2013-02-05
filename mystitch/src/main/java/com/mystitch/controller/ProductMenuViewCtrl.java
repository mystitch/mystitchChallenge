/**
 * 
 */
package com.mystitch.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Window;

import com.mystitch.dao.ProductItemDaoImpl;
import com.mystitch.exception.ProductServiceException;
import com.mystitch.model.ProductItem;
import com.mystitch.service.ProductService;

/**
 * @author augusto.marte
 *
 */

@Controller
@Scope("prototype")
public class ProductMenuViewCtrl extends GenericForwardComposer {

	private static Logger logger = Logger.getLogger(ProductMenuViewCtrl.class);
	
	private Window productsMenu;	
	private Menuitem belts;
	

	
	AnnotateDataBinder databinder;
	
	
	ProductService productService;

	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setAttribute(comp.getId() + "Ctrl", this, true);		
		databinder = new AnnotateDataBinder(comp);

	}
	
	public void onClick$belts(){
		
//		try {
			//List<ProductItem> productItems =  productService.getProductItemList("BLT", "");
			
//			for(ProductItem item:productItems){
//				logger.debug("item name: " + item.getName());
//			}
			
			Executions.sendRedirect("/products/productView.zul");
			
//		} 
//		catch (ProductServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	public ProductService getProductService() {
		return productService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	

}

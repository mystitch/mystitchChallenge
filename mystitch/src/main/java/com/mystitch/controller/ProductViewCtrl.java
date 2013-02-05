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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import com.mystitch.exception.ProductServiceException;
import com.mystitch.model.ProductItem;
import com.mystitch.service.ProductService;

/**
 * @author augusto.marte
 *
 */
@Controller
@Scope("prototype")
public class ProductViewCtrl extends GenericForwardComposer {

	private static Logger logger = Logger.getLogger(ProductViewCtrl.class);
	
	private Window productView;
	private Rows productRows;
	private Grid searchGrid;
	private Checkbox Quicksilver;
	private Checkbox Vans;
	private Checkbox Stussy;
	private Checkbox RCVA;
	private Checkbox Rusty;
	private Checkbox Volcom;
	private Checkbox Nixon;
	private Checkbox Havaianas;
	private Checkbox Levis;
	private Checkbox Lee;
	private Checkbox Insight;
	private Checkbox Hurley;
	private Checkbox Billabong;
	private Checkbox colorWhite;
	private Checkbox colorBrown;
	private Checkbox colorChoc;
	private Checkbox colorBlack;
	private Checkbox size38;
	private Checkbox size36;
	private Checkbox size34;
	private Checkbox size32;

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
		
		try {
			List<ProductItem> productItems =  productService.getProductItemList("BLT", "");
			
			displayProductList(productItems);
		} 
		catch (ProductServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void displayProductList(List<ProductItem> items){
		int dimension=4;
		Row row = new Row();
		Cell cell;
		Image image;
		Image imageBrand;
		Groupbox groupBox;
		Label label;
		int counter=0;
		for(ProductItem item:items){
			row.setHeight("100px");
			final long itemId=item.getItemId();
			cell = new Cell();
			image = new Image();
			//logger.debug("item name: " + item.getName());
			image.setSrc("/images/products/mens/belts/"+ item.getName()+".JPG");			
			cell.appendChild(image);
			imageBrand = new Image();
			imageBrand.setSrc("/images/products/mens/belts/brands/brand"+ (counter%2)+".jpg");			
			cell.appendChild(imageBrand);
			label= new Label();
			label.setValue("[NAME HERE] - $" + item.getPrice() + " AUD");
			groupBox = new Groupbox();
			groupBox.setTitle("RRP");
			groupBox.appendChild(label);			
			cell.appendChild(groupBox);
			cell.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
				public void onEvent(Event event){
					Executions.sendRedirect("/products/productItemView.zul?productItemId="+itemId);			
				}
			});
			row.appendChild(cell);			
			counter++;
			if((counter % dimension) == 0){
				productRows.appendChild(row);
				row = new Row();
				row.setHeight("100px");
			}
			if(counter == items.size()){
				productRows.appendChild(row);
			}
		}
	}
	
	
	
	public ProductService getProductService() {
		return productService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}

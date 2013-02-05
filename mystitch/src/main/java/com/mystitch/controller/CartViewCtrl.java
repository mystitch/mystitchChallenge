/**
 * 
 */
package com.mystitch.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.mystitch.model.OrderItem;
import com.mystitch.model.ProductItem;
import com.mystitch.model.ShoppingCart;
import com.mystitch.service.ProductService;

/**
 * @author augusto.marte
 *
 */
@Controller
@Scope("prototype")
public class CartViewCtrl extends GenericForwardComposer {

	private Window cartViewWindow;
	private Button checkout;
	private Button checkoutDown;
	private Button continueShop;
	
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
		listCartItems();

	}
	
	
	public void listCartItems(){
		ShoppingCart shoppingCart;
		Object cart = Executions.getCurrent().getSession().getAttribute("shoppingCart");
		if(null!=cart){
			shoppingCart = (ShoppingCart)cart;
			Rows cartItemRows = (Rows)cartViewWindow.getFellowIfAny("cartItemRows");
			Row promoRow = (Row)cartViewWindow.getFellowIfAny("promoRow");
			
			List<OrderItem> orderItems =  shoppingCart.getOrderItems();			
			for(OrderItem orderItem:orderItems){
				ProductItem item = orderItem.getItem();
				
				Row row = new Row();
				Cell cell;
				cell = new Cell();
				Image image = new Image();
				image.setSrc("/images/products/mens/itemview/small1.jpg");
				cell.appendChild(image);
				row.appendChild(cell);
				
				cell = new Cell();
				cell.setColspan(2);
				Div div;
				Label label;
				
				div = new Div();
				label = new Label();
				label.setValue("Item:" + item.getName());
				div.appendChild(label);
				cell.appendChild(div);
				
				div = new Div();
				label = new Label();
				label.setValue("Product Code:" +item.getProductCode());
				div.appendChild(label);
				cell.appendChild(div);
				
				div = new Div();
				label = new Label();
				label.setValue("Size:" +item.getSize());
				div.appendChild(label);
				cell.appendChild(div);
				
				div = new Div();
				label = new Label();
				label.setValue("Colour:" +item.getProductCode());
				div.appendChild(label);
				cell.appendChild(div);
				
				row.appendChild(cell);
				
				
				cell = new Cell();
				cell.setAlign("right");
				div = new Div();
				Textbox qty = new Textbox();
				qty.setWidth("35%");
				qty.setValue(String.valueOf(orderItem.getQuantity()));
				div.appendChild(qty);
				cell.appendChild(div);
								
				div = new Div();
				Button button;
				button = new Button();
				button.setLabel("UPDATE ");
				button.setImage("/images/cart/edit.png");
				div.appendChild(button);
				cell.appendChild(div);
				
				div = new Div();
				button = new Button();
				button.setLabel("REMOVE ");
				button.setImage("/images/cart/remove.png");
				div.appendChild(button);
				cell.appendChild(div);
				row.appendChild(cell);
				
				cell = new Cell();
				cell.setAlign("right");
				div = new Div();
				label = new Label();
				label.setValue(String.valueOf(item.getPrice()));
				div.appendChild(label);
				cell.appendChild(div);
				row.appendChild(cell);
				
				cell = new Cell();
				cell.setAlign("right");
				div = new Div();
				label = new Label();
				label.setValue("$" + (orderItem.getQuantity()*item.getPrice()) + " AUD");
				div.appendChild(label);
				cell.appendChild(div);
				row.appendChild(cell);
				
				cartItemRows.insertBefore(row, promoRow);
			}
						
			Cell priceCell = (Cell)cartViewWindow.getFellowIfAny("priceCell");
			Div div;
			Label label;
			
			div = new Div();
			label = new Label();
			label.setValue("$" + shoppingCart.getSubtotal() + " AUD");
			div.appendChild(label);
			priceCell.appendChild(div);
			
			div = new Div();
			label = new Label();
			label.setValue("TBD");
			div.appendChild(label);
			priceCell.appendChild(div);
			
			div = new Div();
			label = new Label();
			label.setValue("$" + shoppingCart.getSubtotal() + " AUD");
			div.appendChild(label);
			priceCell.appendChild(div);
			
			
			
		}
		
		
	}
	
	public void onClick$checkout(){
		Executions.getCurrent().sendRedirect("/pages/carts/cartlogin.zul");
	}
	public void onClick$checkoutDown(){
		Executions.getCurrent().sendRedirect("/pages/carts/cartlogin.zul");
	}
	
	public ProductService getProductService() {
		return productService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}

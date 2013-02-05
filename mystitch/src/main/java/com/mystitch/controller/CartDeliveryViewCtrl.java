/**
 * 
 */
package com.mystitch.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import com.mystitch.model.Address;
import com.mystitch.model.Customer;
import com.mystitch.model.OrderItem;
import com.mystitch.model.ProductItem;
import com.mystitch.model.ShoppingCart;

/**
 * @author augusto.marte
 *
 */
@Controller
@Scope("prototype")
public class CartDeliveryViewCtrl extends GenericForwardComposer {

	private Window cartDeliveryWindow;
	private Button checkoutDown;
	private Cell priceCell;
	private Checkbox addGiftNote;
	private Checkbox addGiftWrap;
	private Row cartItemRow;
	private Combobox shippingMethodCombo;
	private Cell nameDetails;
	private Rows cartItemRows;
	private Button checkout;

	AnnotateDataBinder databinder;
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
			Rows cartItemRows = (Rows)cartDeliveryWindow.getFellowIfAny("cartItemRows");
			Row summaryRow = (Row)cartDeliveryWindow.getFellowIfAny("summaryRow");
			
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
				label = new Label();
				label.setValue(String.valueOf(orderItem.getQuantity()));
				div.appendChild(label);
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
				
				cartItemRows.insertBefore(row, summaryRow);
			}
			
			Address address = (Address) Executions.getCurrent().getSession().getAttribute("shippingAddress");
			if(null!=address){
				
				Customer customer = address.getCustomer();
				StringBuilder detail = new StringBuilder(customer.getFirstName());
				detail.append(" ").append(customer.getLastName()).append(", ");
				detail.append(address.getAddressLine1()).append(", ");
				detail.append(address.getAddressLine2()).append(", ");
				detail.append(address.getCity()).append(", ");
				detail.append(address.getState()).append(", ");
				detail.append(address.getZipCode()).append(", ");
				detail.append(address.getCountry()).append(", ");
				
				Div div;
				Label label;
				
				div = new Div();
				label = new Label();
				label.setValue(detail.toString());
				div.appendChild(label);
				nameDetails.appendChild(div);
			}
			
			
						
			Cell priceCell = (Cell)cartDeliveryWindow.getFellowIfAny("priceCell");
			Div div;
			Label label;
			
			div = new Div();
			label = new Label();
			label.setValue("$" + shoppingCart.getSubtotal() + " AUD");
			div.appendChild(label);
			priceCell.appendChild(div);
			
			div = new Div();
			label = new Label();
			label.setValue("$0.00 AUD");
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
		
		Executions.getCurrent().sendRedirect("/pages/carts/cartPayment.zul");
	}
	public void onClick$checkoutDown(){
		
		Executions.getCurrent().sendRedirect("/pages/carts/cartPayment.zul");
	}
	
	public void onClick$back(){
		Executions.getCurrent().sendRedirect("/pages/carts/cartCheckout.zul");
	}
	public void onClick$backDown(){
		Executions.getCurrent().sendRedirect("/pages/carts/cartCheckout.zul");
	}
	
}

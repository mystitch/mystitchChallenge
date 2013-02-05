/**
 * 
 */
package com.mystitch.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Vbox;
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
public class ProductItemViewCtrl extends GenericForwardComposer {

	private Image itemImage;
	private Label itemPrice;	
	private Intbox itemQuantity;
	private Button addToCart;
	private Checkbox size32;
	private Checkbox size34;
	private Checkbox size36;
	private Checkbox size38;
	
	private int itemSize;
	private ProductItem selectedItem;
	
	private Button currencyConverter;
	private Button proceedToCart;
	
	private Window cartWindow;	
	private Vbox proceedToCartBox;
		
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
		
		String id = Executions.getCurrent().getParameter("productItemId");
		System.out.println("item ID: " + id);
		
		ProductItem item = productService.getProductItem(Long.parseLong(id));
		setSelectedItem(item);
		itemPrice.setValue("RRP " + item.getPrice() + " AUD");
		
		addToCart.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
			public void onEvent(Event event){
				System.out.println("ADD TO CART - clicked: ");	
				
				if(itemQuantity.getValue() == null){
					Messagebox.show("Please enter quantity for this operation.", "",
							Messagebox.OK, Messagebox.ERROR);
					return;
				}
				
				if(itemQuantity.getValue().intValue()==0){
					itemQuantity.setValue(new Integer(1));
					
				}
				
				if(getItemSize() == 0){
					Messagebox.show("Please select item size for this operation.", "",
							Messagebox.OK, Messagebox.ERROR);
					return;
				}
				
				ProductItem shoppedItem = new ProductItem();
				shoppedItem.setItemId(getSelectedItem().getItemId());
				shoppedItem.setBrand(getSelectedItem().getBrand());
				shoppedItem.setCategory(getSelectedItem().getCategory());
				shoppedItem.setColor(getSelectedItem().getColor());				
				shoppedItem.setProductCode(getSelectedItem().getProductCode());
				shoppedItem.setPrice(getSelectedItem().getPrice());
				shoppedItem.setName(getSelectedItem().getName());
				shoppedItem.setSize(getItemSize());
				
				ShoppingCart cart = shop(shoppedItem);
				showShoppingCart(cart);

			}
		});
		

	}
	
	
	public void showShoppingCart(ShoppingCart cart){		
		
		Rows cartItemRows = (Rows)cartWindow.getFellowIfAny("cartItemRows");
		Row rowSummary = (Row)cartWindow.getFellowIfAny("cartItemSummary");
		Components.removeAllChildren(cartItemRows);
		
		//cartItemRows.insertBefore(row, rowSummary);
		
		List<OrderItem> orderItems =  cart.getOrderItems();
		
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
			Div div;
			Label label;
			div = new Div();
			label = new Label();
			label.setValue("Item: " + item.getName());
			div.appendChild(label);
			cell.appendChild(div);
									
			div = new Div();
			label = new Label();
			label.setValue("Size: " + String.valueOf(getItemSize()));
			div.appendChild(label);
			cell.appendChild(div);		
			
				
			div = new Div();
			label = new Label();
			label.setValue("Colour: " + item.getColor());
			div.appendChild(label);
			cell.appendChild(div);
				
			div = new Div();
			label = new Label();
			label.setValue("Qty: " + orderItem.getQuantity());
			div.appendChild(label);
			cell.appendChild(div);
			row.appendChild(cell);
					
			cell = new Cell();		
			cell.setAttribute("valign","top");
			cell.setAttribute("align","center");
			div = new Div();
			label = new Label();
			label.setValue(String.valueOf("$" + item.getPrice() + " AUD"));
			div.appendChild(label);
			cell.appendChild(div);
			row.appendChild(cell);
			
			cartItemRows.appendChild(row);		
			
		}
		
		cartItemRows.appendChild(rowSummary);
		
		
		
		System.out.println("order items size: " + cart.getOrderItems().size());
		System.out.println("Shopping Cart prize:" + cart.getSubtotal());
		
		
		((Label)cartWindow.getFellowIfAny("ORDER_SUBTOTAL")).setValue("$" + cart.getSubtotal() + " AUD");
		((Label)cartWindow.getFellowIfAny("ORDER_SHIPPING")).setValue("TBD");
		((Label)cartWindow.getFellowIfAny("ORDER_TOTAL")).setValue("$" + cart.getSubtotal() + " AUD");
			
		cartWindow.setClosable(true);
		
		cartWindow.doPopup();	
		
		final Timer cartTimer = (Timer)cartWindow.getFellowIfAny("cartTimer");
		cartTimer.addEventListener(Events.ON_TIMER,
				new EventListener<Event>() {
					@Override
					public void onEvent(Event arg0) throws Exception {						
						cartWindow.setVisible(false);
						proceedToCartBox.setVisible(true);
					}
				});
		cartTimer.start();
		
		cartWindow.addEventListener(Events.ON_MOUSE_OVER,
				new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {						
				cartTimer.stop();
			}
		});
		
		cartWindow.addEventListener(Events.ON_MOUSE_OUT,
				new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {						
				cartTimer.start();
			}
		});
		
		/*proceedToCartBox.addEventListener(Events.ON_MOUSE_OVER,
				new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				cartWindow.setVisible(true);
				proceedToCartBox.setVisible(false);
			}
		});*/
		
	}
	
	public ShoppingCart shop(ProductItem shoppedItem){
		ShoppingCart shoppingCart;
		Object cart = Executions.getCurrent().getSession().getAttribute("shoppingCart");
		if(null!=cart){
			boolean onCart = false;			
			shoppingCart = (ShoppingCart)cart;		
			List<OrderItem> orderItems = shoppingCart.getOrderItems();
			for(OrderItem orderItem:orderItems){
				if(orderItem.getItem().getProductCode().equals(shoppedItem.getProductCode())){	
					if(orderItem.getItem().getSize() == shoppedItem.getSize()){
						onCart = true;
						orderItem.setQuantity(orderItem.getQuantity() + itemQuantity.getValue().intValue());
						double orderPrice = shoppedItem.getPrice() * itemQuantity.getValue().intValue();
						shoppingCart.setSubtotal(shoppingCart.getSubtotal() + orderPrice);
					}
				}
			}
			if(!onCart){				
				orderItems.add(createNewOrderItem(shoppedItem));
				double orderPrice = shoppedItem.getPrice() * itemQuantity.getValue().intValue();
				shoppingCart.setSubtotal(shoppingCart.getSubtotal() + orderPrice);
			}
			
			//Executions.getCurrent().getSession().setAttribute("shoppingCart", shoppingCart);
			
		}else{
			shoppingCart = new ShoppingCart();
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			OrderItem orderItem = createNewOrderItem(shoppedItem);
			shoppingCart.setSubtotal(orderItem.getQuantity() * orderItem.getItem().getPrice());
			orderItems.add(orderItem);
			shoppingCart.setOrderItems(orderItems);			
			Executions.getCurrent().getSession().setAttribute("shoppingCart", shoppingCart);
		}
		
		return shoppingCart;
	}
	
	public OrderItem createNewOrderItem(ProductItem item){
						
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setQuantity(itemQuantity.getValue().intValue());
		
		return orderItem;
		
	}
	
	
	public void onClick$proceedToCart(){
		Executions.getCurrent().sendRedirect("/pages/carts/cartview.zul");
	}
	
	public ProductService getProductService() {
		return productService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public int getItemSize() {
		return itemSize;
	}

	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
	}

	public void onCheck$size32(){
		if(size32.isChecked()){
			uncheck(new Checkbox[]{size34,size36,size38});			
			setItemSize(32);
		}else{
			setItemSize(0);
		}
	}
	public void onCheck$size34(){
		if(size34.isChecked()){
			uncheck(new Checkbox[]{size32,size36,size38});
			setItemSize(34);
		}else{
			setItemSize(0);
		}
	}
	public void onCheck$size36(){
		if(size36.isChecked()){
			uncheck(new Checkbox[]{size32,size34,size38});
			setItemSize(36);
		}else{
			setItemSize(0);
		}
	}
	public void onCheck$size38(){
		if(size38.isChecked()){
			uncheck(new Checkbox[]{size32,size34,size36});
			setItemSize(38);
		}else{
			setItemSize(0);
		}
	}
	
	public void uncheck(Checkbox[] boxes){
		for(int i=0;i<boxes.length;i++){
			((Checkbox)boxes[i]).setChecked(false);
		}
	}


	public ProductItem getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(ProductItem selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	
	
}

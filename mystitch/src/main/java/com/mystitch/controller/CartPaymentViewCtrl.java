/**
 * 
 */
package com.mystitch.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * @author augusto.marte
 *
 */
@Controller
@Scope("prototype")
public class CartPaymentViewCtrl extends GenericForwardComposer {

	private Window cartPaymentWindow;
	private Button checkoutDown;
	private Radio residentialAddress;
	private Radio businessAddress;
	private Textbox fax;
	private Textbox address2;
	private Textbox phone;
	private Textbox address1;
	private Textbox state;
	private Textbox company;
	private Combobox countryCombo;
	private Textbox lastname;
	private Textbox zip;
	private Textbox firstname;
	private Textbox city;
	private Radio noneRadio;
	private Radio drRadio;
	private Radio msRadio;
	private Radio mrsRadio;
	private Radio mrRadio;
	private Radiogroup titleRadio;
	private Checkbox checkBillingAddress;
	private Rows cartItemRows;
	private Button checkout;
	private Button back;
	private Button backDown;
	
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

	}
	
	public void onClick$back(){
		Executions.getCurrent().sendRedirect("/pages/carts/cartDelivery.zul");
	}
	public void onClick$backDown(){
		Executions.getCurrent().sendRedirect("/pages/carts/cartDelivery.zul");
	}
	

}

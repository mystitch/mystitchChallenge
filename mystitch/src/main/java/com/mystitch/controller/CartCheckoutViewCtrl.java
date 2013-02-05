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

import com.mystitch.model.Address;
import com.mystitch.model.Customer;

/**
 * @author augusto.marte
 *
 */

@Controller
@Scope("prototype")
public class CartCheckoutViewCtrl extends GenericForwardComposer {

	private Window cartCheckoutWindow;
	private Button checkout;
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
	
	public void onSelect$countryCombo(){
		
	}
	
	public void onClick$checkout(){
		
		Customer customer = new Customer();
		Address address = new Address();
		address.setAddressLine1(address1.getValue());
		address.setAddressLine2(address2.getValue());
		address.setAddressType(businessAddress.isChecked()?"Business":"Residential");
		address.setCity(city.getValue());
		address.setState(state.getValue());
		address.setZipCode(zip.getValue());
		address.setTelNumber(phone.getValue());
		//address.setCountry(countryCombo.getSelectedItem().getContent());
		address.setCountry("Australia");
		
		customer.setTitle(titleRadio.getSelectedItem().getLabel());
		customer.setFirstName(firstname.getValue());
		customer.setLastName(lastname.getValue());
		address.setCustomer(customer);
		
		Executions.getCurrent().getSession().setAttribute("shippingAddress", address);
		Executions.getCurrent().sendRedirect("/pages/carts/cartDelivery.zul");
		
		
	}
	

}

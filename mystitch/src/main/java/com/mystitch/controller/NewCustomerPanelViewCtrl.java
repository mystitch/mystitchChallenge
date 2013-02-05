/**
 * 
 */
package com.mystitch.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;

/**
 * @author augusto.marte
 *
 */
public class NewCustomerPanelViewCtrl extends GenericForwardComposer {

	private Textbox newCustomerEmail;
	private Radio newCustomerRadio;

	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute(comp.getId() + "Ctrl", this, true);
		newCustomerRadio.addEventListener(Events.ON_SELECT,
				new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {						
				newCustomerRadio.setSelected(false);
			}
		});
	}
	

}

/**
 * 
 */
package com.mystitch.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Window;

/**
 * @author augusto.marte
 *
 */
public class NoAccountWindowCtrl extends GenericForwardComposer {

	private Window noAccountWindow;
	private Radio noAccountRadio;

	AnnotateDataBinder databinder;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute(comp.getId() + "Ctrl", this, true);		
		databinder = new AnnotateDataBinder(comp);
		noAccountRadio.setSelected(true);

	}

}

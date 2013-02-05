package com.mystitch.logger;

import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

@WebServlet(name="myStitchLoggerLoader",loadOnStartup=10,urlPatterns={"/myStitchLogger"})
public class MyStitchLoggerLoader extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754049091977570733L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();		
		System.out.println("*** " + "["+ MyStitchLoggerLoader.class.getName() +
				"] " + "loading the Log4j log loader...");
		String pack = this.getClass().getPackage().getName();			
		URL url = Loader.getResource(pack.replace('.', '/')+"/log4j.properties");
		System.out.println("URL:"+url.getPath());
		PropertyConfigurator.configure(url);
		
		System.out.println("*** " + "["+ MyStitchLoggerLoader.class.getName() +
				"] " + "loading the Log4j log loader...finished!");		
		
	}

	
	
}

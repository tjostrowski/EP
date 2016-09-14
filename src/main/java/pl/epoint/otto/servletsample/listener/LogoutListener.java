package pl.epoint.otto.servletsample.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LogoutListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {				
		System.out.println("User was logged out!");				
	}
}

package pl.epoint.otto.servletsample;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import pl.epoint.otto.servletsample.helper.Link;

public class SiteAppenders {

	public static String renderLogout() {
		return "<br>" + Link.of("?action=logout", "Logout");
	}
	
	public static final String COUNTER_ATTR = "counter";
	
	public static String renderCounters(int servletCounter /*updated by servlet*/, ServletRequest req) {
		HttpServletRequest httpReq = (HttpServletRequest)req;
		final int sessionCounter = incrementSessionCounter(httpReq);
		final int servletContextCounter = incrementServletContextCounter(httpReq);
		
		return new StringBuffer("<br><h5>servletCounter=").append(servletCounter).append("</h5><br>")
				.append("<h5>sessionCounter=").append(sessionCounter).append("</h5><br>")
				.append("<h5>servletContextCounter=").append(servletContextCounter).append("</h5><br>")
				.toString();
	}	
	
	private static int incrementSessionCounter(HttpServletRequest httpReq) {
		Object sessionCounterObj = httpReq.getSession().getAttribute(COUNTER_ATTR);
		int sessionCounter;
		if (sessionCounterObj == null) {
			httpReq.getSession().setAttribute(COUNTER_ATTR, 1);
			sessionCounter = 1;
		} else {
			sessionCounter = ((Integer)sessionCounterObj).intValue();
			sessionCounter++;
			httpReq.getSession().setAttribute(COUNTER_ATTR, sessionCounter);
		}
		return sessionCounter;		
	}
	
	private static int incrementServletContextCounter(HttpServletRequest httpReq) {
		Object servletContextCounterObj = httpReq.getSession().getServletContext().getAttribute(COUNTER_ATTR);
		int servletContextCounter;
		if (servletContextCounterObj == null) {
			httpReq.getSession().getServletContext().setAttribute(COUNTER_ATTR, 1);
			servletContextCounter = 1;
		} else {
			servletContextCounter = ((Integer)servletContextCounterObj).intValue();
			servletContextCounter++;
			httpReq.getSession().getServletContext().setAttribute(COUNTER_ATTR, servletContextCounter);
		}
		return servletContextCounter;
	}			
	
}

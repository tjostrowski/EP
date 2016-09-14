package pl.epoint.otto.servletsample.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.epoint.otto.servletsample.helper.FormBuilder;

public class LoginFilter implements Filter {
	
	private static final String USER_ID = "userId";
	private static final String USER_NAME = "userName";
	private static final String USER_PASSWORD = "userPassword";
	private static final String ACTION = "action";
	
	private static final String ACTION_LOGIN = "login";
	private static final String ACTION_LOGOUT = "logout";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		
		final String userId = (String) httpReq.getSession().getAttribute(USER_ID);
		final String action = httpReq.getParameter(ACTION);
		
		setEncodings(httpReq, httpResp);
		
		if (ACTION_LOGOUT.equals(action)) {
			httpReq.getSession().invalidate();
			resp.getWriter().append(renderLoginForm());
			return;
		}
		
		if (userId == null) {
			final String userName = httpReq.getParameter(USER_NAME);
			final String userPass = httpReq.getParameter(USER_PASSWORD);
			
			if ("user".equals(userName) && "123456".equals(userPass)) {
				httpReq.getSession().setAttribute(USER_ID, "user1");
				chain.doFilter(req, resp);
				return;
			}			
			
			Writer writer = resp.getWriter();
			writer.append(renderLoginForm());			
		} else {									
			chain.doFilter(req, resp);
		}				
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}
	
	private String renderLoginForm() {
		FormBuilder formBuilder = new FormBuilder("?action=" + ACTION_LOGIN, "get");
		return formBuilder
			.withTextInput("User name", USER_NAME, "")
			.withTextInput("Password", USER_PASSWORD, "")
			.withSubmit("Login")
			.finish()
			.build();				
	}
	
	private void setEncodings(HttpServletRequest httpReq, HttpServletResponse httpResp) {
		try {
			httpResp.setContentType("text/html;charset=UTF-8");
			httpReq.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}
}

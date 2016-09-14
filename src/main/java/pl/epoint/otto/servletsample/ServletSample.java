package pl.epoint.otto.servletsample;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletSample extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer writer = resp.getWriter();
		writer.append("<html>");
		writer.append("<body>");
		writer.append("<h1>Hello world from otto</h1>");
		writer.append("</body>");
		writer.append("</html>");
	}
}
	
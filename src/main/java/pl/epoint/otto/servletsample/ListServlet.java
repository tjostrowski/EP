package pl.epoint.otto.servletsample;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.epoint.otto.servletsample.database.Product;
import pl.epoint.otto.servletsample.database.Products;
import pl.epoint.otto.servletsample.helper.HtmlBuilder;
import pl.epoint.otto.servletsample.helper.Link;
import pl.epoint.otto.servletsample.helper.TableBuilder;

public class ListServlet extends HttpServlet {
	
	private int visitCounter;
	
	@Override
	public void init() throws ServletException {
		this.visitCounter = 0;
		super.init();
	}
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		
		TableBuilder tableBuilder = new TableBuilder();
		tableBuilder.addHeader("Product Id", "Name", "Price");
		List<Product> products = Products.getProductsList();
		for (Product product : products) {
			tableBuilder.addRow(String.valueOf(product.getId()), 
					Link.of("edit/" + product.getId(), product.getName()), 
					String.valueOf(product.getPrice()));
		}		
		tableBuilder.finish();
		
		htmlBuilder.withBody(tableBuilder.build())
			.finish();
		
		final String html = htmlBuilder.build();
		final Writer writer = resp.getWriter(); 
		writer.append(html);		
		writer.append(SiteAppenders.renderLogout());
		
		visitCounter++;
		writer.append(SiteAppenders.renderCounters(visitCounter, req));
	}	
	
	private void testMe(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Writer writer = resp.getWriter();
		writer.append("<html>");
		writer.append("<body>");
		writer.append("<h1>Hello from list</h1>");
		writer.append("</body>");
		writer.append("</html>");		
	}	
}

package pl.epoint.otto.servletsample;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.epoint.otto.servletsample.database.Product;
import pl.epoint.otto.servletsample.database.Products;
import pl.epoint.otto.servletsample.helper.FormBuilder;
import pl.epoint.otto.servletsample.helper.HtmlBuilder;
import pl.epoint.otto.servletsample.helper.Link;
import pl.epoint.otto.servletsample.validator.ProductValidator;

public class EditServlet extends HttpServlet {
	
	private static final String ACTION = "action";
	private static final String PRODUCT_NAME = "productName";
	private static final String PRODUCT_PRICE = "productValue";
	
	private static final String ACTION_SAVE_PRODUCT = "save_product";
	
	private ProductValidator validator = new ProductValidator();
	
	private int visitCounter;
	
	@Override
	public void init() throws ServletException {
		this.visitCounter = 0;
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Integer productId = getProductId(req);
		final Product product = Products.getProductById(productId);
		final Writer writer = resp.getWriter();
		
		final String action = req.getParameter(ACTION);
		if (action != null && action.equals(ACTION_SAVE_PRODUCT)) {
//			testMe(req, resp);
			final String productName = req.getParameter(PRODUCT_NAME);
			final String productPrice = req.getParameter(PRODUCT_PRICE);			
			
			final String errMsg = validator.validate(productName, productPrice);
			if (!errMsg.equals("")) {
				writer.append("<h4>" + errMsg + "</h4>");
				writer.append(renderForm(product));
				writer.append(Link.of("../list", "Cancel"));
				return;
			}						
			
			product.setName(productName);
			product.setPrice(Long.valueOf(productPrice));			
			resp.sendRedirect("../list");
			return;
		}
						
		if (product == null) {
			resp.getWriter().append( new HtmlBuilder().withBody("No matching product").build() );
			return;
		}
												
		writer.append(renderForm(product));
		writer.append(Link.of("../list", "Cancel"));
		writer.append(SiteAppenders.renderLogout());
		
		visitCounter++;
		writer.append(SiteAppenders.renderCounters(visitCounter, req));
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		doGet(req, resp);
	}
	
	private String renderForm(Product product) {
		FormBuilder formBuilder = new FormBuilder("?action=" + ACTION_SAVE_PRODUCT, "post");
		return formBuilder
			.withTextInput("Product name", PRODUCT_NAME, product.getName())
			.withTextInput("Product price", PRODUCT_PRICE, String.valueOf(product.getPrice()))
			.withSubmit("Save")
//			.withCancel("/list", "Cancel")
			.finish()
			.build();		
	}
	
	private void testMe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer names = new StringBuffer();
		Enumeration paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			names.append(paramNames.nextElement()).append(" ");
		}				
		Writer writer = resp.getWriter();
		writer.append("<html>");
		writer.append("<body>");
		writer.append("<h1>Hello from edit " + names.toString() + "</h1>");
		writer.append("</body>");
		writer.append("</html>");
	}
	
	private Integer getProductId(HttpServletRequest req) {
		final String uri = req.getRequestURI();
		return Integer.valueOf(uri.substring(uri.lastIndexOf("/") + 1));		
	}

}

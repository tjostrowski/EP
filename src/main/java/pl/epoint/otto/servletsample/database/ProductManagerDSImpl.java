package pl.epoint.otto.servletsample.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pl.epoint.otto.servletsample.helper.DBHelper;

public class ProductManagerDSImpl implements ProductManager {
	public static ProductManager INSTANCE = new ProductManagerDSImpl();
	
	private DataSource dataSource;	
	private List<Product> products = null;
	
	public ProductManagerDSImpl() {
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:jdbc/FirstappDS");			
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}

	@Override
	public List<Product> getProductsList() {
		if (products == null) {
			products = new ArrayList<>();
			try {
				Connection conn = dataSource.getConnection();
				DBHelper.fetchProductsFromDB(conn, products);
				conn.close();
				return products;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return products;
	}

	@Override
	public Product getProductById(int productId) {
		// naive
		for (Product p : products) {
			if (p.getId().equals(productId)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void doCleanup() {
		products.clear();		
	}	
}

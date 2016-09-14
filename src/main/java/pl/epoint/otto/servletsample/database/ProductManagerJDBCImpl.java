package pl.epoint.otto.servletsample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pl.epoint.otto.servletsample.helper.DBHelper;

public class ProductManagerJDBCImpl implements ProductManager {
	public static ProductManager INSTANCE = new ProductManagerJDBCImpl();

	private Connection conn;
	private List<Product> products = null;

	public ProductManagerJDBCImpl() {
		conn = openConnection();
	}

	@Override
	public List<Product> getProductsList() {
		if (products == null) {
			products = new ArrayList<>();
			DBHelper.fetchProductsFromDB(conn, products);
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
		closeConnection(conn);
	}

	private Connection openConnection() {
		try {			
			Properties properties = new Properties();
			properties.put("user", "firstapp");
			properties.put("password", "firstapp");
			properties.put("characterEncoding", "ISO-8859-2");
			properties.put("useUnicode", "true");
			String url = "jdbc:postgresql://localhost:5432/firstapp";

			Class.forName("org.postgresql.Driver").newInstance();
			Connection c = DriverManager.getConnection(url, properties);
			return c;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

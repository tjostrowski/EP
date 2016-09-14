package pl.epoint.otto.servletsample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductManagerJDBCImpl implements ProductManager {
	public static ProductManager INSTANCE = new ProductManagerJDBCImpl();
	
	private Connection conn;
	private List<Product> products = new ArrayList<>();
	
	public ProductManagerJDBCImpl() {
		conn = openConnection();
	}

	@Override
	public List<Product> getProductsList() {
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int productId = rs.getInt("id");
				String name = rs.getString("name");
				long price = rs.getLong("price");
				
				products.add(new Product(productId, name, price));
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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
		  properties.put("characterEncoding", "ISO-8859-1");
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

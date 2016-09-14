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

public class ProductManagerDSImpl implements ProductManager {
	public static ProductManager INSTANCE = new ProductManagerDSImpl();
	
	private DataSource dataSource;	
	private List<Product> products = new ArrayList<>();
	
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
		try {
			Connection conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int productId = rs.getInt("id");
				String name = rs.getString("name");
				long price = rs.getLong("price");

				products.add(new Product(productId, name, price));
			}
			conn.close();
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
	}
}

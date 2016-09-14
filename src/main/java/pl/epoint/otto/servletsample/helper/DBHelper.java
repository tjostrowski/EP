package pl.epoint.otto.servletsample.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import pl.epoint.otto.servletsample.database.Product;

public class DBHelper {
	
	public static List<Product> fetchProductsFromDB(Connection conn, List<Product> products) {
		products.clear();
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
}

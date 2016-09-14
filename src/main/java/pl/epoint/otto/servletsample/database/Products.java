package pl.epoint.otto.servletsample.database;

import java.util.ArrayList;
import java.util.List;

public class Products {
	
	private static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product(1, "cake", 100L));
		products.add(new Product(2, "butter", 200L));
		products.add(new Product(3, "bread", 300L));
	}
	
	public static List<Product> getProductsList() {
		return products;
	}	
	
	public static Product getProductById(Integer productId) {
		// naive
		for (Product p : products) {
			if (p.getId().equals(productId)) {
				return p;
			}			
		}
		return null;
	}	
}

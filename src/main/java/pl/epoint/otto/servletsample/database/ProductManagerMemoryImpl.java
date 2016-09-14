package pl.epoint.otto.servletsample.database;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerMemoryImpl implements ProductManager {
	public static ProductManager INSTANCE = new ProductManagerMemoryImpl();
	
	List<Product> products = new ArrayList<>();
	
	{
		products.add(new Product(1, "cake", 100L));
		products.add(new Product(2, "butter", 200L));
		products.add(new Product(3, "bread", 300L));
	}

	@Override
	public List<Product> getProductsList() {
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

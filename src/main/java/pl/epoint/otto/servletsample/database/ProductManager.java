package pl.epoint.otto.servletsample.database;

import java.util.List;

public interface ProductManager {

	List<Product> getProductsList();
	Product getProductById(int productId);
	void doCleanup();
}

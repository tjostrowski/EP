package pl.epoint.otto.servletsample.validator;

import pl.epoint.otto.servletsample.database.Product;

public class ProductValidator {
	
	public String validate(String productName, String productPrice) {
		StringBuffer errorStr = new StringBuffer();
		if (productName == null || productName.equals("")) {
			errorStr.append("Product name could not be empty.");
		}
		if (!productPrice.matches("\\d+")) {
			errorStr.append(" Product price should be a number.");
		}
		return errorStr.toString();
	}

}

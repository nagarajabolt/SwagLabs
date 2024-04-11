package testData;

import java.util.ArrayList;

import objects.ProductPojo;

public class ProductsData {

	public ArrayList<ProductPojo> initializeProduct() {
		ArrayList<ProductPojo> productsExpected = new ArrayList<ProductPojo>();
		ProductPojo product = new ProductPojo("Sauce Labs Backpack", 29.99);
		productsExpected.add(product);		
		product = new ProductPojo("Sauce Labs Bike Light", 9.99);
		productsExpected.add(product);	
		product = new ProductPojo("Sauce Labs Bolt T-Shirt", 15.99);
		productsExpected.add(product);
		product = new ProductPojo("Sauce Labs Fleece Jacket", 49.99);
		productsExpected.add(product);		
		product = new ProductPojo("Sauce Labs Onesie", 7.99);
		productsExpected.add(product);				
		product = new ProductPojo("Test.allTheThings() T-Shirt (Red)", 15.99);
		productsExpected.add(product);
		return productsExpected;
	}
	
	
	
}

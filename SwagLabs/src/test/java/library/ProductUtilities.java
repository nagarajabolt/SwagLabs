package library;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import objects.ProductPojo;
import testcases.BaseClass;
/**
 * Product related operations like logging product list items, sorting product lists and verifying product list and
 * prices are matching are placed here
 */
public class ProductUtilities  extends BaseClass{
	public void displayListItems(List<ProductPojo> list) {
		Log.info("Method - displayListItems");
		for(int i=0; i<list.size(); i++) {
			Log.info(list.get(i).getProductName()+"  "+list.get(i).getPrice());
			
		}
		Log.info("Method - displayListItems - end");
	}
	
	public void sortByProductName(ArrayList<ProductPojo> productlist){
	       Collections.sort(productlist, new Comparator<ProductPojo>() {
				@Override
				public int compare(ProductPojo o1, ProductPojo o2) {
					return (o1.getProductName().compareTo(o2.getProductName()));
				}
	        });
	       
	       
	}
	
	public void reverseSortByProductName(ArrayList<ProductPojo> productlist){
	       Collections.sort(productlist, new Comparator<ProductPojo>() {
				@Override
				public int compare(ProductPojo o1, ProductPojo o2) {
					// TODO Auto-generated method stub
					return (o2.getProductName().compareTo(o1.getProductName()));
				}
	        });
	       
	       
	}
	
	public void sortByPrice(ArrayList<ProductPojo> productlist){
	       Collections.sort(productlist, new Comparator<ProductPojo>() {
				@Override
				public int compare(ProductPojo o1, ProductPojo o2) {
					return Double.compare(o1.getPrice(), o2.getPrice());
				}
	        });
	}
	
	public void reversesortByPrice(ArrayList<ProductPojo> productlist){
	       Collections.sort(productlist, new Comparator<ProductPojo>() {
				@Override
				public int compare(ProductPojo o1, ProductPojo o2) {
					return Double.compare(o2.getPrice(), o1.getPrice());
				}
	        });
	}	
	
	public Boolean verifyProductListsAreMatching(ArrayList<ProductPojo> productsActual, ArrayList<ProductPojo> productsExpected) {
		Boolean productsMatching = true;
		try {
			for(int i=0; i<productsActual.size(); i++) {
				String actualProductName = productsActual.get(i).getProductName();
				Double actualPrice = productsActual.get(i).getPrice();
				Log.info("Actual  : "+actualProductName+ " "+actualPrice);

				String expectedProductName = productsExpected.get(i).getProductName();
				Double expectedPrice = productsExpected.get(i).getPrice();	
				Log.info("Expected: "+expectedProductName+ " "+expectedPrice);

				if(!(actualProductName.equals(expectedProductName)||(actualPrice==expectedPrice))) {
					productsMatching = false;
					break;
				}
			} 
		}catch (Exception e) {
			Log.error(e.toString());
			productsMatching = false;
		}
		
		
		return productsMatching;
	}
	
	public Boolean verifyProductPricesAreMatching(ArrayList<ProductPojo> productsActual, ArrayList<ProductPojo> productsExpected) {
		Boolean productsMatching = true;
		try {
			for(int i=0; i<productsActual.size(); i++) {
				String actualProductName = productsActual.get(i).getProductName();
				Double actualPrice = productsActual.get(i).getPrice();
				Log.info("Actual  : "+actualProductName+ " "+actualPrice);

				String expectedProductName = productsExpected.get(i).getProductName();
				Double expectedPrice = productsExpected.get(i).getPrice();	
				Log.info("Expected: "+expectedProductName+ " "+expectedPrice);

				if(Double.compare(expectedPrice, actualPrice)!=0) {
					productsMatching = false;
					break;
				}
			} 
		}catch (Exception e) {
			Log.error(e.toString());
			productsMatching = false;
		}		
		return productsMatching;
	}
}

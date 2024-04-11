package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import objects.ProductPojo;
import testcases.BaseClass;

public class FileOperations extends BaseClass{

	public Properties readPropertiesFile(String fileName)
	{
		FileInputStream fileInputStream = null;
	      Properties properties = null;
	      try {
	    	  fileInputStream = new FileInputStream(fileName);
	    	  properties = new Properties();
	    	  properties.load(fileInputStream);
	      } catch(FileNotFoundException fileNotFoundException) {
	    	  fileNotFoundException.printStackTrace();
	      } catch(IOException ioException) {
	    	  ioException.printStackTrace();
	      } finally {
	    	  try {
				fileInputStream.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
	      }
	      return properties;
	   }
	
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
	
	
	public File createSceenshotFile() {
		File destFile = null;
		try {

			File screenshotFolder = new File(screenShotsFolderName);
			if (!screenshotFolder.exists()) {
				screenshotFolder.mkdir();
				Log.info("Screenshot folder is created:");
			}
			
			
			String screenshotFileName = "Screenshot" + "_" + "Fail" + "_" + getFormatedDateForScreenshots(new Date())+ ".png";
			try {
				String screenshotFilePath = screenShotsFolderName +"\\" +screenshotFileName;
				Log.info("ScreenshotFilePath:"+screenshotFilePath);
				destFile = new File(screenshotFilePath);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.toString());
		}
		return destFile;
	}

	public String getFormatedDateForScreenshots(Date date) {
		SimpleDateFormat dateAndTimeformat = null;
		try {
			dateAndTimeformat = new SimpleDateFormat("dd_MM_yyyy_kk_mm_ss");
		} catch (Exception e) {
			Log.error(e.toString());
		}

		return dateAndTimeformat.format(date);
	}
}


	
	


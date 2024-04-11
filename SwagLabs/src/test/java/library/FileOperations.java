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
/**
 * File related operations like reading property files, taking screenshot for failure are placed here
 */
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


	
	


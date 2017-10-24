package com.reader.csvreader;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 *This is main class which create task scheduler for every 60 secs 
 */

public class ScheduledTask  {

	//Delimiters used in the CSV file
	private static final String COMMA_DELIMITER = ",";
	private static String inputfilepath="";
	private static String outputfileName="";
	private static final String CONFIG_FILE="resources\\application.properties";
	
	final static Logger logger = Logger.getLogger(ScheduledTask.class);

	/**
	 * This is used call ReadCSVFile method after every 60 sec.
	 * @param 
	 * @return void
	 */	
	/*public void run() {
		try {
		if((inputfilepath != null && !inputfilepath.isEmpty()) && (outputfileName != null && !outputfileName.isEmpty())){ 		//StringUtils.isEmpty(fileName)
			new ReadCSVFile().fileReader(COMMA_DELIMITER,inputfilepath,outputfileName);
		}else{
			logger.error("Sorry, something wrong with Configuration!");
			throw new CustomException("properties not configured properly in'" + CONFIG_FILE + "'");
		}
		} catch ( CustomException ex) {
			ex.printStackTrace();
		}
	
	}*/
	
	/**
	 * This is starting point of application. It will read configuration and create schedule task
	 * This method create fixed thread pool with the help of executorService. we can also use cachethreadpool so that it will create new thread whenever required 
	 * @param 
	 * @return void
	 */	
	public static void main(String args[])
	{
		Properties prop = new Properties();
		InputStream input = null;
		boolean keepRunning = true;

		try {

			input = new FileInputStream(CONFIG_FILE);
			logger.info("Configuration reading started...");
			if (input != null) {
				prop.load(input);
			} else {
				throw new FileNotFoundException("property file '" + CONFIG_FILE + "' not found in the classpath");
			}
			inputfilepath=prop.getProperty("inputfilepath");
			outputfileName=prop.getProperty("outfilename");
			logger.info("Input File Names is "+inputfilepath+" \nOutput File Name is"+outputfileName);

			 File Folder = new File(inputfilepath);
		     File files[];
		     files = Folder.listFiles();
			
		     if(files.length>0)
		        {
		        	ExecutorService executor = Executors.newFixedThreadPool(2);
		        	for(int i = 0;i<files.length; i++){
		        		logger.info("file reading started...");
		        		logger.info(files[i]);

		        		Runnable worker = new WorkerThread("" + files[i],outputfileName,COMMA_DELIMITER);
		        		executor.execute(worker);

		        	}
		            executor.shutdown();
		            while (!executor.isTerminated()) {
		            }
		            logger.info("Finished all threads");
		        }
		        else{
		        	logger.info("No File found...");
		        }
			
		} catch (IOException  ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("Exception in closing file object");
					e.printStackTrace();
				}
			}
		}
	}
}
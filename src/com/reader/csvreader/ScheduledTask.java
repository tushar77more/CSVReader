package com.reader.csvreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

import org.apache.log4j.Logger;

import com.customexceptions.CustomException;
/**
 *This is main class which create task scheduler for every 60 secs 
 */

public class ScheduledTask extends TimerTask {

	//Delimiters used in the CSV file
	private static final String COMMA_DELIMITER = ",";
	private static String inputfileName="";
	private static String outputfileName="";
	private static final String CONFIG_FILE="resources\\application.properties";
	
	final static Logger logger = Logger.getLogger(ScheduledTask.class);

	/**
	 * This is used call ReadCSVFile method after every 60 sec.
	 * @param 
	 * @return void
	 */	
	public void run() {
		try {
		if((inputfileName != null && !inputfileName.isEmpty()) && (outputfileName != null && !outputfileName.isEmpty())){ 		//StringUtils.isEmpty(fileName)
			new ReadCSVFile().fileReader(COMMA_DELIMITER,inputfileName,outputfileName);
		}else{
			logger.error("Sorry, something wrong with Configuration!");
			throw new CustomException("properties not configured properly in'" + CONFIG_FILE + "'");
		}
		} catch ( CustomException ex) {
			ex.printStackTrace();
		}
	
	}
	
	/**
	 * This is starting point of application. It will read configuration and create schedule task
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
			inputfileName=prop.getProperty("inputfilename");
			outputfileName=prop.getProperty("outfilename");
			logger.info("Input File Names is "+inputfileName+" \nOutput File Name is"+outputfileName);

			Timer time = new Timer(); // Instantiate Timer Object
			ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
			time.schedule(st, 0, 60000); // Create Repetitively task for every 60 secs

			while(keepRunning) {
				logger.info("Execution in Main Thread....");
				Thread.sleep(120000);
				if (!keepRunning) {
					logger.info("Application Terminates");
					System.exit(0);
				}
			}
		} catch (IOException | InterruptedException ex) {
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
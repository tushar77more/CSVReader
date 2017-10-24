package com.reader.csvreader;

import org.apache.log4j.Logger;

import com.customexceptions.CustomException;

/**
 * This is worker thread prepared for actual file process
 * 
 * implements Runnable
 */

public class WorkerThread implements Runnable {
  
    private String inputfile;
    private String outputfile;
    private String delimiter;
    
    final static Logger logger = Logger.getLogger(WorkerThread.class);
    
    public WorkerThread(String inputfile,String outputfile,String delimiter){
        this.inputfile=inputfile;
        this.outputfile=outputfile;
        this.delimiter=delimiter;
    }

    @Override
    public void run() {
    	logger.info(" File "+inputfile+" reading started");
        processCommand();
        logger.info(" File "+inputfile+" reading End");
    }

    /**
	 * This method calling CSV file reader to read file content
	 * @param 
	 * @return void
	 */
    protected void processCommand() {
        try {
        		if((inputfile != null && !inputfile.isEmpty()) && (outputfile != null && !outputfile.isEmpty())){ 		//StringUtils.isEmpty(fileName)
        			new ReadCSVFile().fileReader(delimiter,inputfile,outputfile);
        		}else{
        			logger.error("Sorry, something wrong with Configuration!");
        			throw new CustomException("properties not configured properly in properties file");
        		}
        } catch (CustomException e) {
        	logger.info("Exception in file reading "+e);
        }
    }

    @Override
    public String toString(){
        return this.outputfile;
    }
}
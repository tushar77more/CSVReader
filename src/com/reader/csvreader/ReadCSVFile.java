package com.reader.csvreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.log4j.Logger;
import java.util.Timer;

import com.customexceptions.CustomException;

/*
 * This class is used for reading csv file
 */
public class ReadCSVFile 
{
	private static String outputfileName="";
	final static Logger logger = Logger.getLogger(ReadCSVFile.class);

	/**
	 * This is used to read delimiter separated input file and create employee list object
	 * @param delimiter,fileName,outfileName
	 * @return void
	 */
	public void fileReader(String delimiter,String fileName,String outfileName){
		BufferedReader br = null;
		try
		{
			outputfileName=outfileName;
			//Reading the csv file
			br = new BufferedReader(new FileReader(fileName));
			List<Employee> empList = new ArrayList<Employee>();

			String line = "";
			//Read to skip the header
			br.readLine();
			logger.info("Reading from input file");
			while ((line = br.readLine()) != null) 
			{
				String[] employeeDetails = line.split(delimiter);

				if(employeeDetails.length > 0 )
				{
					Employee emp = new Employee(employeeDetails[0],Integer.parseInt(employeeDetails[1]),
							employeeDetails[2],employeeDetails[3],
							employeeDetails[4]);
					empList.add(emp);
				}
			}

			createJSONObject(fileName,empList);
		}
		catch(Exception ee )
		{
			logger.error("Exception in file creating Employee object",ee);
		}
		finally
		{
			try{
				br.close();
			}
			catch(IOException ie)
			{
				logger.error("Error occured while closing the BufferedReader",ie);
				ie.printStackTrace();
			}
		}

	}

	/**
	 * This is used to create JSON Object from employee list
	 * @param empList
	 * @return void
	 */
	private void createJSONObject(String fileName,List<Employee> empList){
		logger.info("Creating JSON object");
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Date now = new Date();
		for(Employee e : empList)
		{
			jsonArray.add(e);

		}
		jsonObject.put("Employee Details", jsonArray);
		//try (FileWriter file = new FileWriter(outputfileName+now.getYear()+"-"+now.getMonth()+"-"+now.getDate()+"_"+now.getHours()+"."+now.getMinutes()+"."+now.getSeconds()+"."+now.get)) {
		try (FileWriter file = new FileWriter(outputfileName,true)) {

			file.write(jsonObject.toJSONString());
			file.flush();

		} catch (IOException ex) {
			logger.error("Exception in json object writting");
			ex.printStackTrace();
		}
	}
}
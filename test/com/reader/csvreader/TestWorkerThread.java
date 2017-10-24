package com.reader.csvreader;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class TestWorkerThread {

	@Test
	public void testprocessCommand1(){
		
		try{
		List<Employee> empList = new ArrayList<Employee>();
		
		Employee emp1 = new Employee("name1",30,
				"Male","Address1",
				"City1");
		Employee emp2 = new Employee("name2",20,
				"Male","Address2",
				"City2");
		Employee emp3 = new Employee("name3",25,
				"Female","Address3",
				"City3");
		Employee emp4 = new Employee("name4",28,
				"Female","Address4",
				"City3");
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		empList.add(emp3);
		
		WorkerThread obj=new WorkerThread("C:\\workspace\\test\\CSVReader\\inputfile\\EmployeeDetails5.csv", "EmployeeDetails.json",",");
		obj.processCommand();
		
		}catch(Exception ex){
			Assert.assertNotNull(ex);
			
		}
		
	}
	
	@Test
	public void testprocessCommand2(){
		
		try{

		WorkerThread obj=new WorkerThread("", "EmployeeDetails.json",",");
		obj.processCommand();
		
		}catch(Exception ex){
			Assert.assertNotNull(ex);
			
		}
		
	}
}

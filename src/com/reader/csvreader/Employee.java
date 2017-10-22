package com.reader.csvreader;

public class Employee 
{
    private String empName;
    private int empAge;
    private String empGender;
    private String empAddress;
    private String empCity;
    
    public Employee(String empName, 
                     int empAge, String empGender,String empAddress,String empCity) {
        this.empName = empName;
        this.empAge=empAge;
        this.empGender = empGender;
        this.empAddress = empAddress;
        this.empCity = empCity;
    }
    
   

    public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public int getEmpAge() {
		return empAge;
	}



	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}



	public String getEmpGender() {
		return empGender;
	}



	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}



	public String getEmpAddress() {
		return empAddress;
	}



	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}



	public String getEmpCity() {
		return empCity;
	}



	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}



	@Override
    public String toString() {
        return "Employee {empName=" + empName + ", empAge=" + empAge
                + ", empGender=" + empGender + ", empAddress=" + empAddress + ", empCity=" + empCity +"}";
    }
}
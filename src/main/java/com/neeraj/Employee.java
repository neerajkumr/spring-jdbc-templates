package com.neeraj;

public class Employee {
	private String eName;
	private int eId;
	private String DOB;

	public Employee() {
		super();
	}

	public Employee(String eName, int eId, String dOB) {
		super();
		this.eName = eName;
		this.eId = eId;
		DOB = dOB;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	@Override
	public String toString() {
		return "Employee eName=" + eName + " \n eId=" + eId + "\n DOB=" + DOB + "";
	}

}

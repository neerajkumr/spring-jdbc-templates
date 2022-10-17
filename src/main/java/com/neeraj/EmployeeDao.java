package com.neeraj;

public interface EmployeeDao {

	public void save(Employee e);

	public void getAll();

	public void getByID(int id);
}

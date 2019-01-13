package dao;

import java.util.List;
import entity.Employee;

public interface EmpDao {
	public List<Employee> findALL() throws Exception;
	public void insertEmp(Employee e) throws Exception; 
	public void deleteEmpById(int id) throws Exception; 
	public void updateEmp(Employee e) throws Exception; 
	public Employee findEmpById(int id) throws Exception;
}

package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.EmpDao;
import entity.Employee;
import util.C3p0Utils;

public class EmpDaoImpl implements EmpDao {

	public List<Employee> findALL() throws Exception {
		ArrayList<Employee> list = new ArrayList<Employee>();
		Connection conn = C3p0Utils.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from t_emp");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Employee e = new Employee();
			e.setId(rs.getInt("id"));
			e.setName(rs.getString("name"));
			e.setAge(rs.getInt("age"));
			e.setSalary(rs.getDouble("salary"));
			list.add(e);
		}
		C3p0Utils.close(rs, ps, conn);
		return list;
	}
	
	public void insertEmp(Employee e) throws Exception {
		Connection conn = C3p0Utils.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into t_emp(name,age,salary) values(?,?,?)");
		ps.setString(1, e.getName());
		ps.setInt(2,e.getAge());
		ps.setDouble(3, e.getSalary());
		ps.executeUpdate();
		System.out.println("插入成功");
		C3p0Utils.close(ps, conn);
		
	}
	
	public void deleteEmpById(int id) throws Exception {
		Connection conn = C3p0Utils.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from t_emp where id=?");
		ps.setInt(1,id);
		ps.executeUpdate();
		
		C3p0Utils.close(ps, conn);
	}
	
	public void updateEmp(Employee e) throws Exception {
		Connection conn = C3p0Utils.getConnection();
		PreparedStatement ps = conn.prepareStatement("update t_emp set name=?,age=?,salary=? where id=?");
		ps.setString(1, e.getName());
		ps.setInt(2,e.getAge());
		ps.setDouble(3, e.getSalary());
		ps.setInt(4, e.getId());
		ps.executeUpdate();
		
		C3p0Utils.close(ps, conn);
	}
	
	public Employee findEmpById(int id) throws Exception {
		Employee e = null;
		Connection conn = C3p0Utils.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from t_emp where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			e = new Employee();
			e.setId(rs.getInt("id"));
			e.setName(rs.getString("name"));
			e.setAge(rs.getInt("age"));
			e.setSalary(rs.getDouble("salary"));
		}
		C3p0Utils.close(rs, ps, conn);
		return e;
		
	}


	
}

package dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.EmpDao;
import entity.Employee;
import util.C3p0Utils;

public class EmpDaoImpl implements EmpDao {

	public List<Employee> findALL() throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from t_emp";
		List<Employee> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Employee>(Employee.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insertEmp(Employee emp) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "insert into t_emp(name,age,salary) values(?,?,?)";
		try {
			int update = queryRunner.update(sql, emp.getName(), emp.getAge(), emp.getSalary());
			System.out.println("插入" + update + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteEmpById(int id) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "delete from t_emp where id=?";
		try {
			int update = queryRunner.update(sql, id);
			System.out.println("删除" + update + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmp(Employee emp) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "update t_emp set name=?,age=?,salary=? where id=?";
		try {
			int update = queryRunner.update(sql, emp.getName(),emp.getAge(),emp.getSalary(),emp.getId());
			System.out.println("修改" + update + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Employee findEmpById(int id) throws Exception {
		Employee emp = null;
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from t_emp where id=?";
		try {
			emp = queryRunner.query(sql, new BeanHandler<Employee>(Employee.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
}

package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.UserDAO;
import entity.User;
import util.C3p0Utils;

public class UserDAOImpl implements UserDAO {

	public void addUser(User user) {
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "insert into t_user(username,name,password,gender) values(?,?,?,?)";
		try {
			int update = queryRunner.update(sql, user.getUsername(),user.getName(),user.getPassword(),user.getGender());
			System.out.println("增加" + update + "名用户");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUserByID(int id) {
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "delete from t_user where id=?";
		try {
			int update = queryRunner.update(sql, id);
			System.out.println("删除" + update + "名用户");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "update t_user set username=?,name=?,password=?,gender=? where id=?";
		try {
			int update = queryRunner.update(sql, user.getUsername(),user.getName(),user.getPassword(),user.getGender());
			System.out.println("更新了" + update + "名用户");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<User> findAllUsers() {
		ArrayList<User> userlist = new ArrayList<User>();
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from t_user";
		try {
			userlist = (ArrayList<User>) queryRunner.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userlist;
	}
	
	public User findUserByUsername(String username) {
		User user = null;
		QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from t_user where username=?";
		try {
			user = (User) queryRunner.query(sql,new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean login(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = C3p0Utils.getConnection();
			String sql = "select count(*) countUser from t_user where username=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {
				int result = rs.getInt("countUser");
				if (result == 1) {
					System.out.println("登录成功");
					return true;
				} else {
					System.out.println("登录失败");
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(rs, ps, conn);
		}
		return false;
	}
}

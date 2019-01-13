package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.UserDAO;
import entity.User;
import util.C3p0Utils;

public class UserDAOImpl implements UserDAO {

	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = C3p0Utils.getConnection();
			ps = conn.prepareStatement("insert into t_user(username,name,password,gender) values(?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getGender());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(ps, conn);
		}
	}

	public void deleteUserByID(int ID) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = C3p0Utils.getConnection();
			ps = conn.prepareStatement("delete from t_user where id=?");
			ps.setInt(1, ID);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(ps, conn);
		}
	}

	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = C3p0Utils.getConnection();
			ps = conn.prepareStatement("update t_user set username=?,name=?,password=?,gender=? where id=?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getGender());
			ps.setInt(5, user.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(ps, conn);
		}
	}


	public ArrayList<User> findAllUsers() {
		ArrayList<User> userlist = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = C3p0Utils.getConnection();
			ps = conn.prepareStatement("select * from t_user");
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				userlist.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(rs, ps, conn);
		}
		return userlist;
	}

	public User findUserByUsername(String username) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = C3p0Utils.getConnection();
			ps = conn.prepareStatement("select * from t_user where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(rs, ps, conn);
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

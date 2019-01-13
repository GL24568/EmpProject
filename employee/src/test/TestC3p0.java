package test;

import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import util.C3p0Utils;

public class TestC3p0 {

	public static void main(String[] args) {

		try {
			System.out.println(C3p0Utils.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}

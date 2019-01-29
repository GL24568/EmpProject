package test;

import org.junit.Test;

import dao.imp.EmpDaoImpl;
import dao.imp.UserDAOImpl;
import util.C3p0Utils;


public class DaoTest {

	@Test
    public void testCon() throws Exception {
		System.out.println(C3p0Utils.getConnection());
	}
	
	@Test
	public void test2() {
		// TODO Auto-generated method stub
		EmpDaoImpl daoImpl = new EmpDaoImpl();
		try {
			daoImpl.findALL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		// TODO Auto-generated method stub
		UserDAOImpl daoImpl = new UserDAOImpl();
		try {
			System.out.println(daoImpl.findAllUsers());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

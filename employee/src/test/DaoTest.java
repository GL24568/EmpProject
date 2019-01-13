package test;

import org.junit.Test;

import util.C3p0Utils;


public class DaoTest {

	@Test
    public void testCon() throws Exception {
		System.out.println(C3p0Utils.getConnection());
	}
}

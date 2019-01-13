package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import dao.EmpDao;
import dao.UserDAO;
import dao.imp.EmpDaoImpl;
import dao.imp.UserDAOImpl;
import entity.Employee;
import entity.User;

public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		if("/login".equals(action)) {
			String number1 = request.getParameter("number");
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30 * 60); 
			String number2 = (String)session.getAttribute("number");
			if(!number1.equalsIgnoreCase(number2)) {
				request.setAttribute("checkcode_msg", "验证码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			String username = request.getParameter("username");
			String password = request.getParameter("pwd");
			
			UserDAO dao = new UserDAOImpl();
			try {
				User user = dao.findUserByUsername(username);
				if("".equals(username.trim())) {
					request.setAttribute("username", "用户名不能为空");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else if("".equals(password.trim())) {
					request.setAttribute("password", "密码不能为空");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				} else	if(user != null) {
					if(dao.login(username, password)) {
						session.setAttribute("user", user);
						response.sendRedirect("list.do");
					} else {
						request.setAttribute("result", "用户名或密码错误");
						request.getRequestDispatcher("/index.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			} 
		} else if("/regist".equals(action)) {
			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String password = request.getParameter("pwd");
			String gender = request.getParameter("gender");
			
			if("".equals(username.trim())) {
				request.setAttribute("username", "用户名不能为空");
				System.out.println("用户名不能为空");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
			} else if("".equals(name.trim())) {
				request.setAttribute("name", "姓名不能为空");
				System.out.println("姓名不能为空");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
			} else if("".equals(password.trim())) {
				request.setAttribute("password", "密码不能为空");
				System.out.println("密码不能为空");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
			} else {
				UserDAO dao = new UserDAOImpl(); 
				User user = dao.findUserByUsername(username);
				if(user != null) {
					request.setAttribute("result", "用户已存在");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
				} else {
					user = new User();
					user.setUsername(username);
					user.setName(name);
					user.setPassword(password);
					user.setGender(gender);
					dao.addUser(user);
					System.out.println("falg");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
					
				}
			}
		} else	if(action.equals("/list")) {
			
				HttpSession session = request.getSession();
				Object obj = session.getAttribute("user");
				if(obj == null) {
					response.sendRedirect("/employee/index.jsp");
					return;
				}
				EmpDao dao = new EmpDaoImpl();
			try {	
				List<Employee> employees = dao.findALL();
				request.setAttribute("employees", employees);
				RequestDispatcher rd = request.getRequestDispatcher("emplist.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		} else if(action.equals("/delete")) {
			HttpSession session = request.getSession();
			User obj = (User)session.getAttribute("user");
			System.out.println(obj);
			Factory<org.apache.shiro.mgt.SecurityManager> factory =
					new IniSecurityManagerFactory("classpath:shiro.ini");
					org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
					SecurityUtils.setSecurityManager(securityManager);
					Subject subject = SecurityUtils.getSubject();
					UsernamePasswordToken token = new UsernamePasswordToken(obj.getName(), obj.getPassword());
					try {
						subject.login(token);
					} catch (AuthenticationException e) {
						e.printStackTrace();
					}
					if (subject.isAuthenticated()) {
						System.out.println("success");
						int id = Integer.parseInt(request.getParameter("id"));
						try {
							EmpDao dao = new EmpDaoImpl();
							dao.deleteEmpById(id);	
							response.sendRedirect("list.do");
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
							throw new ServletException(e);
						}	 
					}else {
						response.sendRedirect("/employee/error2.jsp");
					}
					subject.logout();
			
		} else if("/add".equals(action)) {
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			double salary = Double.parseDouble(request.getParameter("salary"));

			HttpSession session = request.getSession();
			User obj = (User)session.getAttribute("user");
			System.out.println(obj);
			Factory<org.apache.shiro.mgt.SecurityManager> factory =
					new IniSecurityManagerFactory("classpath:shiro.ini");
					org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
					SecurityUtils.setSecurityManager(securityManager);
					Subject subject = SecurityUtils.getSubject();
					UsernamePasswordToken token = new UsernamePasswordToken(obj.getName(), obj.getPassword());
					try {
						subject.login(token);
					} catch (AuthenticationException e) {
						e.printStackTrace();
					}
					if (subject.isAuthenticated()) {
						System.out.println("success");
						try {
							
							EmpDao dao = new EmpDaoImpl();
							Employee e = new Employee();
							e.setName(name);
							e.setAge(age);
							e.setSalary(salary);
							dao.insertEmp(e);
							response.sendRedirect("list.do");
						} catch (Exception e) {
							e.printStackTrace();
							throw new ServletException(e);
						} 
					}else {
						response.sendRedirect("/employee/error2.jsp");
					}
			subject.logout();
			
			
		} else if("/load".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				EmpDao dao = new EmpDaoImpl();
				Employee e = dao.findEmpById(id);
				
				request.setAttribute("e", e);
				request.getRequestDispatcher("updateEmp.jsp").forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			} 
		} else if("/modify".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			double salary = Double.parseDouble(request.getParameter("salary"));
			
			HttpSession session = request.getSession();
			User obj = (User)session.getAttribute("user");
			System.out.println(obj);
			Factory<org.apache.shiro.mgt.SecurityManager> factory =
					new IniSecurityManagerFactory("classpath:shiro.ini");
					org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
					SecurityUtils.setSecurityManager(securityManager);
					Subject subject = SecurityUtils.getSubject();
					UsernamePasswordToken token = new UsernamePasswordToken(obj.getName(), obj.getPassword());
					try {
						subject.login(token);
					} catch (AuthenticationException e) {
						e.printStackTrace();
					}
					if (subject.isAuthenticated()) {
						System.out.println("success");
						try {
							EmpDao dao = new EmpDaoImpl();
							Employee e = new Employee();
							e.setId(id);
							e.setName(name);
							e.setAge(age);
							e.setSalary(salary);
							dao.updateEmp(e);
							response.sendRedirect("list.do");
						} catch (Exception e) {
							e.printStackTrace();
							throw new ServletException(e);
						} 
					}else {
						response.sendRedirect("/employee/error2.jsp");
					}
			subject.logout();
		}
	}
}

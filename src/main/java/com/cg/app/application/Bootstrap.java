package com.cg.app.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cg.app.account.ui.AccountCUI;

public class Bootstrap {
	/*
	 * static { try { Class.forName("com.mysql.jdbc.Driver"); Connection connection
	 * = DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankapp_db",
	 * "root", "root"); PreparedStatement preparedStatement =
	 * connection.prepareStatement("DELETE FROM ACCOUNT");
	 * preparedStatement.execute(); } catch (ClassNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	public static void main(String[] args) {
		/* AccountCUI.start(); */
		  ApplicationContext context =new ClassPathXmlApplicationContext("context.xml");
		  AccountCUI cui=context.getBean(AccountCUI.class);
		  cui.start();
		  
		/*
		 * Organization org = (Organization) context.getBean("organization");
		 * System.out.println(org.toString());
		 */
	}

}

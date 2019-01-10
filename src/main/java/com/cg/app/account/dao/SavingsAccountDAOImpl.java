package com.cg.app.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.util.DBUtil;
import com.cg.app.exception.AccountNotFoundException;
@Repository
public class SavingsAccountDAOImpl implements SavingsAccountDAO {
@Autowired
private JdbcTemplate template;
	public SavingsAccount createNewAccount(SavingsAccount account)
			throws ClassNotFoundException, SQLException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)");
		 * preparedStatement .setInt(1, account.getBankAccount().getAccountNumber());
		 * preparedStatement.setString(2, account.getBankAccount()
		 * .getAccountHolderName()); preparedStatement.setDouble(3,
		 * account.getBankAccount() .getAccountBalance());
		 * preparedStatement.setBoolean(4, account.isSalary());
		 * preparedStatement.setObject(5, null); preparedStatement.setString(6, "SA");
		 * preparedStatement.executeUpdate(); preparedStatement.close();
		 * DBUtil.commit();
		 * 
		 */
		
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",new Object[] {
				account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(),
				account.getBankAccount().getAccountBalance(),
				account.isSalary(),null,"SA"});
				return account;
	}
		
		
		
	public List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		/*
		 * List<SavingsAccount> savingsAccount = new ArrayList<SavingsAccount>();
		 * Connection connection = DBUtil.getConnection(); Statement statement =
		 * connection.createStatement(); ResultSet resultSet =
		 * statement.executeQuery("SELECT * FROM account ORDER BY account_hn"); while
		 * (resultSet.next()) { int accountNumber = resultSet.getInt(1); String
		 * accountHolderName = resultSet.getString("account_hn"); double accountBalance
		 * = resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * SavingsAccount savingsAccountList = new SavingsAccount(accountNumber,
		 * accountHolderName, accountBalance, salary);
		 * savingsAccount.add(savingsAccountList); } DBUtil.commit();
		 */
		return template.query("SELECT * FROM account ORDER BY account_hn", new DAOMapper());
	}
	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException {
		/*
		 * List<SavingsAccount> savingsAccountList = new ArrayList<SavingsAccount>();
		 * Connection connection = DBUtil.getConnection(); Statement statement =
		 * connection.createStatement(); ResultSet resultSet =
		 * statement.executeQuery("SELECT * FROM account ORDER BY account_hn DESC");
		 * while (resultSet.next()) { int accountNumber = resultSet.getInt(1); String
		 * accountHolderName = resultSet.getString("account_hn"); double accountBalance
		 * = resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * SavingsAccount savingsAccount = new SavingsAccount(accountNumber,
		 * accountHolderName, accountBalance, salary);
		 * savingsAccountList.add(savingsAccount); } DBUtil.commit();
		 */
		return template.query("SELECT * FROM account ORDER BY account_hn DESC", new DAOMapper());
	}
	public List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException {

		/*
		 * List<SavingsAccount> savingsAccountList = new ArrayList<SavingsAccount>();
		 * Connection connection = DBUtil.getConnection(); Statement statement =
		 * connection.createStatement(); ResultSet resultSet =
		 * statement.executeQuery("SELECT * FROM account ORDER BY account_bal"); while
		 * (resultSet.next()) { int accountNumber = resultSet.getInt(1); String
		 * accountHolderName = resultSet.getString("account_hn"); double accountBalance
		 * = resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * SavingsAccount savingsAccount = new SavingsAccount(accountNumber,
		 * accountHolderName, accountBalance, salary);
		 * savingsAccountList.add(savingsAccount); } DBUtil.commit();
		 */
		return template.query("SELECT * FROM account ORDER BY account_bal", new DAOMapper());
	}

	public List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws ClassNotFoundException, SQLException {
		/*
		 * List<SavingsAccount> savingsAccountList = new ArrayList<SavingsAccount>();
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatementQuery = connection
		 * .prepareStatement("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal"
		 * ); preparedStatementQuery.setDouble(1, minimumBalance);
		 * preparedStatementQuery.setDouble(2, maximumBalance); ResultSet resultSet =
		 * preparedStatementQuery.executeQuery(); while (resultSet.next()) { int
		 * accountNumber = resultSet.getInt(1); String accountHolderName =
		 * resultSet.getString("account_hn"); double accountBalance =
		 * resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * SavingsAccount savingsAccount = new SavingsAccount(accountNumber,
		 * accountHolderName, accountBalance, salary);
		 * savingsAccountList.add(savingsAccount); } DBUtil.commit();
		 */
		return template.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal", new Object[] {minimumBalance,maximumBalance},new DAOMapper());
	}

	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue)
			throws ClassNotFoundException, SQLException {
		/*
		 * List<SavingsAccount> savingsAccountList = new ArrayList<SavingsAccount>();
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatementQuery = connection
		 * .prepareStatement("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal DESC"
		 * ); preparedStatementQuery.setDouble(1, minimumBalanceValue);
		 * preparedStatementQuery.setDouble(2, maximumBalanceValue); ResultSet resultSet
		 * = preparedStatementQuery.executeQuery(); while (resultSet.next()) { int
		 * accountNumber = resultSet.getInt(1); String accountHolderName =
		 * resultSet.getString("account_hn"); double accountBalance =
		 * resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * SavingsAccount savingsAccount = new SavingsAccount(accountNumber,
		 * accountHolderName, accountBalance, salary);
		 * savingsAccountList.add(savingsAccount); } DBUtil.commit();
		 */
		return template.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal DESC", new Object[] {minimumBalanceValue,maximumBalanceValue},new DAOMapper());
	}

	
	


	public List<SavingsAccount> getAllSavingsAccount()
			throws ClassNotFoundException, SQLException {
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT");
		while (resultSet.next()) {// Check if row(s) is present in table
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber,
					accountHolderName, accountBalance, salary);
			savingsAccounts.add(savingsAccount);
		}
		DBUtil.commit();
		return savingsAccounts;
	}

	
	public void updateBalance(int accountNumber, double currentBalance)
			throws ClassNotFoundException, SQLException {
		/*
		 * Connection connection = DBUtil.getConnection();
		 * connection.setAutoCommit(false); PreparedStatement preparedStatement =
		 * connection
		 * .prepareStatement("UPDATE ACCOUNT SET account_bal=? where account_id=?");
		 * preparedStatement.setDouble(1, currentBalance); preparedStatement.setInt(2,
		 * accountNumber); preparedStatement.executeUpdate();
		 */
		template.update("UPDATE ACCOUNT SET account_bal=? where account_id=?", new Object[] {currentBalance,
				accountNumber});
	}

	
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("SELECT * FROM account where account_id=?");
		 * preparedStatement.setInt(1, accountNumber); ResultSet resultSet =
		 * preparedStatement.executeQuery(); SavingsAccount savingsAccount = null; if
		 * (resultSet.next()) { String accountHolderName =
		 * resultSet.getString("account_hn"); double accountBalance =
		 * resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * savingsAccount = new SavingsAccount(accountNumber, accountHolderName,
		 * accountBalance, salary); return savingsAccount; } throw new
		 * AccountNotFoundException("Account with account number " + accountNumber +
		 * " does not exist.");
		 */
		
		return template.queryForObject("SELECT * FROM account where account_id=?", new Object[] {
				accountNumber},new DAOMapper());
		
	}

	
	public SavingsAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {

		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("DELETE FROM account where account_id=?");
		 * preparedStatement.setInt(1, accountNumber); preparedStatement.execute();
		 * DBUtil.commit();
		 */
		template.update("DELETE FROM account where account_id=?",new Object[] {
				accountNumber});
				
		return null;
	}

	
	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("SELECT account_bal FROM account where account_id=?");
		 * preparedStatement.setInt(1, accountNumber); ResultSet resultSet =
		 * preparedStatement.executeQuery(); if (resultSet.next()) { double
		 * accountBalance = resultSet.getDouble(1); return accountBalance; }
		 * DBUtil.commit();
		 */
		double balance=template.queryForObject("SELECT account_bal FROM account where account_id=?",new Object[] {accountNumber},Double.class);
			return  balance;
	}

	
	public SavingsAccount searchAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		/*Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM account where account_id=?");
		preparedStatement.setInt(1, accountNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		SavingsAccount savingsAccount = null;
		if (resultSet.next()) {
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salary");
			savingsAccount = new SavingsAccount(accountNumber,
					accountHolderName, accountBalance, salary);*/
		 
			
			return template.queryForObject("SELECT * FROM account where account_id=?", new Object[] {
					accountNumber},new DAOMapper());
		
		
	}

	public SavingsAccount updateAccount(SavingsAccount savingsaccount)
			throws SQLException, ClassNotFoundException {
		template.update("UPDATE account SET account_hn=?,salary=? where account_id=?",new Object[] {
				savingsaccount.getBankAccount().getAccountNumber(),
				savingsaccount.getBankAccount().getAccountHolderName(),
				savingsaccount.isSalary()});
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("UPDATE ACCOUNT SET account_hn=?,salary=? where account_id=?"
		 * ); preparedStatement.setString(1, savingsaccount.getBankAccount()
		 * .getAccountHolderName()); preparedStatement.setBoolean(2,
		 * savingsaccount.isSalary()); preparedStatement.setInt(3,
		 * savingsaccount.getBankAccount() .getAccountNumber());
		 * preparedStatement.executeUpdate(); DBUtil.commit();
		 */
		return savingsaccount;
	}


	public void commit() throws SQLException {
		DBUtil.commit();
	}

}

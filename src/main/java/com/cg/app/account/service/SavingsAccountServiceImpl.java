package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.dao.SavingsAccountDAO;
import com.cg.app.account.factory.AccountFactory;
import com.cg.app.exception.AccountNotFoundException;
@Service
@Transactional
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		
	}

	
	public SavingsAccount createNewAccount(String accountHolderName,
			double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(
				accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	
	public SavingsAccount updateAccount(SavingsAccount savingsaccount) throws SQLException, ClassNotFoundException {
		
		return savingsAccountDAO.updateAccount(savingsaccount);
	}
	public List<SavingsAccount> getAllSavingsAccount()
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}


	public void deposit(SavingsAccount account, double amount)
			throws ClassNotFoundException, SQLException {
		/*
		 * if (amount > 0) {  currentBalance += amount;
		 * savingsAccountDAO.updateBalance(account.getBankAccount() .getAccountNumber(),
		 * currentBalance); // savingsAccountDAO.commit(); } else { throw new
		 * InvalidInputException("Invalid Input Amount!"); }
		 */
		double currentBalance = account.getBankAccount()
				 .getAccountBalance();
		 currentBalance += amount;
		 savingsAccountDAO.updateBalance(account.getBankAccount() .getAccountNumber(),currentBalance);
	}

	public void withdraw(SavingsAccount account, double amount)
			throws ClassNotFoundException, SQLException {
		/*
		 * if
		 * (amount > 0 && currentBalance >= amount) { currentBalance -= amount;
		 * savingsAccountDAO.updateBalance(account.getBankAccount() .getAccountNumber(),
		 * currentBalance); // savingsAccountDAO.commit(); } else { throw new
		 * InsufficientFundsException( "Invalid Input or Insufficient Funds!"); }
		 */
		 double currentBalance = account.getBankAccount().getAccountBalance();
		 currentBalance -= amount;
		savingsAccountDAO.updateBalance(account.getBankAccount() .getAccountNumber(),currentBalance);
	}

	
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver,
			double amount) throws ClassNotFoundException, SQLException {
		/*
		 * try {
		 * 
		 * DBUtil.commit(); } catch (InvalidInputException | InsufficientFundsException
		 * e) { e.printStackTrace(); DBUtil.rollback(); } catch (Exception e) {
		 * e.printStackTrace(); DBUtil.rollback(); }
		 * 
		 */
		deposit(receiver, amount);
		withdraw(sender, amount);
			
	}

	
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	
	public SavingsAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {

		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	
	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {

		return  savingsAccountDAO.checkBalance(accountNumber);
	}
	public SavingsAccount searchAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {

		return  savingsAccountDAO.searchAccount(accountNumber);
	}

	public List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountHolderName();
	}

	
	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountHolderNameInDescendingOrder();
	}

	
	public List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountBalance();
	}

	
	public List<SavingsAccount> sortByBalanceRange(int minimumBalance,
			int maximumBalance) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByBalanceRange(minimumBalance,maximumBalance);
	}

	
	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(
			int minimumBalanceValue, int maximumBalanceValue) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByBalanceRangeInDescendingOrder(minimumBalanceValue,maximumBalanceValue);
	}
}

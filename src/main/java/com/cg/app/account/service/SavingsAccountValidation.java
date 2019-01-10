package com.cg.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

@Aspect
@Component
public class SavingsAccountValidation {

	Logger logger = Logger.getLogger(SavingsAccountValidation.class.getName());

	@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {	
		Object[] params = pjp.getArgs();
		SavingsAccount currentBalance = (SavingsAccount) params[0];
		double amount = (double) params[1];
		double currentbalance = currentBalance.getBankAccount().getAccountBalance();
		logger.info(" your Balance is: "+currentbalance);
		if (amount > 0 && amount <= currentbalance) {
		  pjp.proceed();
			logger.info(" amount withdrawn successfully: ");
		}
		else 
		{
			/* logger.info(" please enter a valid amount: "); */
			throw new InsufficientFundsException("Insufficient Funds");
		}
		
	
	}
	
	
	@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] params = pjp.getArgs();
//		SavingsAccount account = (SavingsAccount) params[0];
//		double currentBalance = account.getBankAccount().getAccountBalance();
		double amountToDeposit = (double) params[1];
		if (amountToDeposit > 0) {
			pjp.proceed();
		}
		else
		{
			//throw new InvalidInputException("Invalid Funds");
			logger.info("please enter a valid amount");
		}
	}
}


package operations;

import exceptions.BusinessException;

public class Withdraw extends Operation{

	private double balance;
	private double amount;
	private double withdrawLimit;
	
	
	public Withdraw(double balance, double withdrawLimit) {
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
	}

	
	private void validateWithdraw() {
		if (this.amount > this.withdrawLimit) {
			throw new BusinessException("Erro de saque: A quantia excede o limite de saque");
		} 
		if (this.amount > this.balance) {
			throw new BusinessException("Erro de saque: Saldo insuficiente");
		}
	}
	
	@Override
	public double action(double balance, double value) {
		this.amount = value;
		validateWithdraw();
		return balance-= value;
	}
	
}

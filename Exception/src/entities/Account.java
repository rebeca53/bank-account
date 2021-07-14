package entities;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private Double balance;
	private Double withdrawLimit;
	private UserData userData;
	public Account() {
	}

	public Account(Double balance, Double withdrawLimit, String holder, Integer CPF, String birthday){
	
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.userData = new UserData(holder,CPF,birthday);
	}

	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdrawLimit() {
		return withdrawLimit;
	}

	public void setWithdrawLimit(Double withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	public String getuserData() {
		return this.userData.toString();
	}
	public Integer getCPF() {
		return this.userData.getCPF();
	}

	@Override
	public String toString() {
		return "Conta [saldo= " + balance + " limite conta= " + withdrawLimit + userData + "]";
	}
	public String formatStringTxt() {
		return "Account = ;" + balance + ";" + withdrawLimit + ";"+ userData.formatStringTxt() ;
	}
		

	
}

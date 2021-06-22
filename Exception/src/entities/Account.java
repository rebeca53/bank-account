package entities;


public class Account{

	private Integer number;
	private Double balance;
	private Double withdrawLimit;
	private UserData userData;
	public Account() {
	}

	public Account(Integer number, Double balance, Double withdrawLimit, String holder, Integer CPF, String birthday){
		this.number = number;
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.userData = new UserData(holder,CPF,birthday);
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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
		

	
}

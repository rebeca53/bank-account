package operations;

import exceptions.InvalidBillException;

public class Deposit extends  Operation{

	private double amount;
	
	
	@Override
	public double action(double balance, double value) {
		this.amount = value;
		presentialDeposit();
		return amount +=balance;
	}
	
	private double presentialDeposit() throws InvalidBillException {
		if(this.amount % 2 == 0) {
			System.out.print(this.amount);
			return amount;			
		}
		else {
			throw new InvalidBillException(amount);
		}
	}
}

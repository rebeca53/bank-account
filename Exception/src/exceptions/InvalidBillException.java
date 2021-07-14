package exceptions;

public class InvalidBillException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private double invalidBill;
	
	public InvalidBillException(double bill) {
		super("Valor que deseja depositar não conduz com os valores de notas existentes");
		invalidBill = bill;
	}

	public double getInvalidBill() {
		
		return invalidBill;
	}
	
	
}
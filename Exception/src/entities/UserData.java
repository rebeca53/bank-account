package entities;

public final class UserData {
	/*
	 * Get the data from user and turn them immutable
	 * */
	
	private final String fullName;
	private final Integer CPF;
	private final String birthday;
	
	public UserData(String fullName, Integer CPF, String birthday){
		this.fullName = fullName;
		this.CPF = CPF;
		this.birthday = birthday;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	public Integer getCPF(){
		return this.CPF;
	}
	public String getBirthday() {
		return this.birthday;
	}
	
	@Override
	public String toString() {
		return" Titular = "+fullName+" CPF = "+ CPF +" Data de Nascimento= "+birthday; 
	}
	public String formatStringTxt() {
		return fullName+";"+ CPF +";"+birthday; 
	}
}

package operations;

import java.io.*;

import java.util.LinkedList;

import entities.Account;

public class FileWriterReader {
	String account = null;
	private LinkedList<Account> accounts = new LinkedList<Account>();
	File tempDir = new File(System.getProperty("java.io.tmpdir"));
	File file = new File(tempDir, "accounts.txt");
	
	public FileWriterReader() {
		this.readFile();
	}
	public LinkedList<Account> getAccounts(){
		return this.accounts;
	}
	public void readFile(){
		Account acc;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
	        }
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			
			while (line != null) {
				
				String[] vect = line.split(";");
				Double balance = Double.parseDouble(vect[1]);
				Double withdrawLimit = Double.parseDouble(vect[2]);
				String holder = vect[3];
				Integer CPF = Integer.parseInt(vect[4]);
				String birthday = vect[5];
				acc = new Account(balance, withdrawLimit, holder, CPF, birthday);
				accounts.add(acc);
				
				line = br.readLine();
				}
			br.close();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void createAccount(Account acc) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true))){
			this.account = acc.formatStringTxt();
			
			bw.write(this.account);
			bw.newLine();
			this.accounts.add(acc);
			bw.flush();
			
		}catch(IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		
	}
	public void removeAccount(Account acc) {

		for (Account account : this.accounts) {
			if(acc.equals(account))
				this.accounts.remove(acc);
		}
			
	}
	public Account validation(int order, String value) {

		try(BufferedReader br = new BufferedReader(new FileReader(this.file))) {

			for (Account acc : this.accounts) {
				String[] vect = acc.formatStringTxt().split(";");
				if (vect[order].equals(value)) {
					return acc;
				}		
			}
			
		}catch(IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		
		return null;
		
	}
	public void attAccounts() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))){
			for (Account account : this.accounts) {
				bw.write(account.formatStringTxt());
				bw.newLine();
			}
			bw.flush();
		}catch(IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	
	}
	
}
	
	

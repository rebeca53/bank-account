package aplication;
import java.util.Scanner;
import java.util.LinkedList; 

import entities.Account;
import exceptions.BusinessException;
import exceptions.InvalidBillException;
import operations.Deposit;
import operations.FileWriterReader;
import operations.Operation;
import operations.Withdraw;
public class Controller {
	
	private String holder;
	private Double balance;
	private Double withdrawLimit;
	private View view = new View();
	private Account account;
	private FileWriterReader file;
	private Byte choice = 1;
	private Scanner sc;
	
	public Controller() {
		sc = new Scanner(System.in);
		file = new FileWriterReader();
		start();
	}

	public void start() {
		
		try{
			while(this.choice != 0) {
				accountsControll();
				
			}
				
			
		}catch(BusinessException e) {
			
			this.view.print(e.getMessage());
			
			
		}catch(InvalidBillException b) {
			this.view.print(b.getMessage());
		}catch(Exception a) {
			this.view.print("Erro de digitação");
		}
		finally {
			sc.close();
			this.view.print("programa encerrado");
		
		}
		
	}
	public Account createAccount(){
		view.print("Informe os dados da conta");
		
		view.print("Titular: ");
		sc.nextLine();
		this.holder = sc.nextLine();
		
		view.print("Data de nascimento: ");
		String birthday = sc.nextLine();
		
		view.print("Digite seu CPF(apenas numero: ");
		Integer CPF = sc.nextInt();
		this.account = file.validation(4, Integer.toString(CPF));
		if (this.account == null) {
			view.print("Saldo inicial: ");
			this.balance = sc.nextDouble();
			
			view.print("Limite de saque: ");
			this.withdrawLimit = sc.nextDouble();
			
			this.account = new Account(balance, withdrawLimit, holder, CPF, birthday);
			file.createAccount(this.account);
						
		}else {
			this.view.print("Conta ja existe com o CPF informado");
		}
		
		run();
		return account;
		
	}
	
	public void run(){
		while (choice != 0) {
			
			view.options();
			this.choice= sc.nextByte();
			Operation op = accountChoices();
			if (op != null) {
				this.view.print("Digite o valor: ");
				double amount = sc.nextDouble();
				Double result = op.action(account.getBalance(), amount);
				this.account.setBalance(result);
				this.view.print("Novo Saldo: "+ result.toString());
				file.attAccounts();
			}
		}
		
		
	}
	public void accountsControll() {
		this.view.accountMenu();
		this.choice= sc.nextByte();
		Integer CPF = 0;
		switch(choice) {
		case 1:
			this.account = createAccount();
			
			break;
		case 2:
			this.view.print("Digite seu CPF(apenas numero: ");
			CPF = sc.nextInt();
			this.account = file.validation(4, Integer.toString(CPF));
			
			if (this.account != null) {
			
				this.file.removeAccount(this.account);
				this.file.attAccounts();
				this.view.print("Removida com sucesso");
				accountsControll();
				
			}else {
				this.view.print("Conta não foi encontrada");
			}
			break;
		case 3:
			this.view.print("Digite seu CPF(apenas numero: ");
			CPF = sc.nextInt();
			this.account = file.validation(4, Integer.toString(CPF));
		
			if (this.account != null) {
				run();
			}else {
				this.view.print("Conta não foi encontrada, crie uma nova: ");
				this.account = createAccount();
				
			}
			break;
		case 4:
			this.printAccount();
		}
	}
	public	void printAccount() {
		LinkedList<Account> accounts = file.getAccounts();
		if(!accounts.isEmpty()) {
			this.view.print("---------Contas cadastradas---------");
			for(Account acc : accounts) {
				this.view.print(acc.toString()+ "\n ");
			}
			this.view.print("-----------------------------------");
		}else {
			this.view.print("Nenhuma conta cadastrada no sistema \n ");
		}
		
		
	}
	public Operation accountChoices() {
		
		
		Operation op = null;
		
		switch(choice) {
		
			case 1:
				op = new Deposit();
				this.view.print("Depósito por valor de cédula");
			
				break;
			case 2:
				
				op = new Withdraw(account.getBalance(),account.getWithdrawLimit());
				break;
			case 3:
				String data= account.getuserData();
				this.view.print(data);
				this.view.print(" Saldo Atual: " + account.getBalance().toString());
				break;
			
			case 0:
				accountsControll();
				break;
				
		}
		return op;
	}
		
	
}
package aplication;
import java.util.Scanner;
import java.util.LinkedList; 

import entities.Account;
import exceptions.BusinessException;
import exceptions.InvalidBillException;
import operations.Deposit;
import operations.Operation;
import operations.Withdraw;
public class Controller {
	
	private int number;
	private String holder;
	private Double balance;
	private Double withdrawLimit;
	private View view = new View();
	private Account account;
	private LinkedList<Account> accounts = new LinkedList<Account>();
	private Byte choice = 1;
	private Scanner sc;
	
	public Controller() {
		sc = new Scanner(System.in);
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
			System.out.println("programa encerrado");
		
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
		
		this.account = this.findAccount(CPF);	
		if (this.account == null) {
			view.print("Saldo inicial: ");
			this.balance = sc.nextDouble();
			
			view.print("Limite de saque: ");
			this.withdrawLimit = sc.nextDouble();
			
			this.number = this.accounts.size();
			
			this.account = new Account(number,balance,withdrawLimit,holder,CPF, birthday);
			this.accounts.add(this.account);
			
		}else {
			view.print("Conta ja existe com o CPF informado");
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
				view.print("Digite o valor: ");
				double amount = sc.nextDouble();
				Double result = op.action(account.getBalance(), amount);
				account.setBalance(result);
				view.print("Novo Saldo: "+ result.toString());
			}
		}
		
		
	}
	public void accountsControll() {
		view.accountMenu();
		this.choice= sc.nextByte();
		Integer CPF = 0;
		switch(choice) {
		case 1:
			this.account = createAccount();
			
			break;
		case 2:
			view.print("Digite seu CPF(apenas numero: ");
			CPF = sc.nextInt();
			this.account = findAccount(CPF);
			if (this.account != null) {
				this.accounts.remove(this.account);
				view.print("Removida com sucesso");
				accountsControll();
				
			}else {
				view.print("Conta não foi encontrada");
			}
			break;
		case 3:
			view.print("Digite seu CPF(apenas numero: ");
			CPF = sc.nextInt();
			this.account = findAccount(CPF);
			if (this.account != null) {
				run();
			}else {
				view.print("Conta não foi encontrada, crie uma nova: ");
				this.account = createAccount();
				
			}
			break;
		}
	}
	public Account findAccount(Integer CPF) {
		
		this.account = null;
		for(int i = 0; i < accounts.size(); i++) {
			this.account = accounts.get(i);
			if(this.account.getCPF() == CPF) {
				return this.account;
			}
		}
		
		return this.account;
		
		
		
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
				view.print(data);
				view.print(" Saldo Atual: " + account.getBalance().toString());
				break;
			
			case 0:
				accountsControll();
				break;
				
		}
		return op;
	}
		
	
}
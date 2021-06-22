package aplication;


public class View {
	
	
	public View() {
		
		
	}
	public void options() {
		print("---------Conta Bancária------------");
		print("1- Depositar");
		print("2- Sacar");
		print("3- Dados pessoais");
		print("0- Voltar");
	
	}
	public void accountMenu() {
		print("------Controlle de Contas-----------");
		print("1- Criar Conta");
		print("2- Deletar Conta");
		print("3- Acessar Conta");
		print("0- Encerrar");
	}
	public void print(String msg) {
		System.out.println(msg);
	}
	
}

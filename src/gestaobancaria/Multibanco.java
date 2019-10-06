package gestaobancaria;

import java.util.Scanner;

public class Multibanco extends InteraccaoBanco{

	public static void main(String[] args) {
		
		System.out.println("Multibanco");
		System.out.println();
		System.out.print("Insira seu login: ");
		Scanner key = new Scanner(System.in);
		int login = key.nextInt();
		System.out.print("Insira a sua password: ");
		int passwd = key.nextInt();
		Banco.iniciar();
		Cliente c =  Banco.validarLogin(login, passwd);
		if(c != null){
			if(c.activo){
				cli=c;
				System.out.println("Bem vindo Sr(�) " + c.nome);
				
				processaMenuContas(c.obterContas());
			}else{
				System.out.println("N�o pode opera sobre um cliente inactivo");
			}
		}else{
			System.out.println("Dados de login inv�lidos");
		}
		Banco.guardarDados();
	}
}

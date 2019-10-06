package gestaobancaria;

import java.util.Scanner;

public class Balcao extends InteraccaoBanco{
	
	public static Cliente buscarIdCliente(){
		Scanner key = new Scanner(System.in);
		System.out.print("Insira o id do cliente: ");
		int idcliente = key.nextInt();
		cli = Banco.procurarCliente(idcliente);
		if(cli!= null){
			return cli;
		} else { 
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		int op;
		Banco.iniciar(); // necess�rio para iniciar o array
						 // clientes = new ArrayList<Cliente>();
		do{
			
			System.out.println("- Menu Principal - ");
			System.out.println();
			System.out.println("1 -> Criar cliente");
			System.out.println("2 -> Desativar cliente");
			System.out.println("3 -> Criar conta");
			System.out.println("4 -> Desativar conta");
			System.out.println("5 -> Listar clientes");
			System.out.println("6 -> Opera��es sobre cliente");
			System.out.println("7 -> Sair");
			System.out.print("[:");
			
			op = key.nextInt();
			
			switch(op){
				case 1: //Ok
						System.out.println("- Criar cliente");
						System.out.print("Insira o nome do cliente: ");
						String nome = key.next();
						System.out.print("Insira o password(somente n�meros): ");
						int passwd = key.nextInt();
						int nc = Banco.gerarNumCliente();
						Banco.criarCliente(nome, nc, passwd);
						System.out.println("Sr(�) "+nome+", seu n�mero de cliente � "+ nc);
						System.out.println();
					break;
					
				case 2: 
						System.out.println("- Desativar cliente");
						
						cli = buscarIdCliente();
						if (cli != null){
							System.out.print("Deseja desativa-lo s/n: ");
							String opconta = key.next();
							opconta.toLowerCase();
							if((opconta == "s")||(opconta == "n")){
								cli.activo = false;
								System.out.println(cli.nome + " desativado");
							}else{
								System.out.println("Op��o inv�lida");
							}
						}else{
							System.out.println("Cliente n�o encontrado");
						}
						System.out.println();
					break;
					
				case 3: // Ok?
						System.out.println("- Criar conta");
						cli = buscarIdCliente();
						if (cli != null){
							System.out.print("Insria o tipo de conta a criar\n1 - D�bito  2 - Prazo :");
							int opconta = key.nextInt();
							if((opconta == 1)||(opconta == 2)){
								System.out.println("Nib da conta: "+Banco.criarConta(cli, opconta));
							}else{
								System.out.println("Tipo inv�lido");
							}
						}else{
							System.out.println("Cliente n�o encontrado");
						}
						System.out.println();
					break;
					
				case 4: 
						System.out.println("- Desativar conta");
						cli = buscarIdCliente();
						if (cli != null){
							System.out.print("Insira o n�mero da conta:");
							int nibc = key.nextInt();
							con = cli.obterConta(nibc);
							if (con != null){
								con.desactivar();
								System.out.println("Conta desativada");
							}else{
								System.out.println("Conta n�o encontrada");
							}
							
						}else{
							System.out.println("Cliente n�o encontrado");
						}
						System.out.println();

					break;
					
				case 5:  // OK!
						System.out.println("- Listar clientes");
						System.out.println("Insira o nome do cliente a listar(Enter para listar todos): ");
						key.nextLine();
						String criterionome = key.nextLine();
						System.out.println("cliente a procurar: " + criterionome);
						Banco.listarClientes(criterionome);
						System.out.println();
					break;
					
				case 6: 
						System.out.println("- Opera��es sobre cliente");
						cli = buscarIdCliente();
						if (cli != null){
							processaMenuContas(cli.obterContas());
						}else{
							System.out.println("Cliente n�o encontrado");
						}
						System.out.println();
					break;
				case 7:
						System.out.println("");
					break;
					
				default: System.out.println("Op��o inv�lida\n");
					break;
			}
			
		}while(op != 7 );
		System.out.println("Volte sempre!");
		Banco.guardarDados();

	}
}

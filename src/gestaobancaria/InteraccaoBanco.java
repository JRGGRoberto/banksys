package gestaobancaria;

import java.util.ArrayList;
import java.util.Scanner;

import contas.Conta;

public abstract class InteraccaoBanco {
	
	
		
	protected static Cliente cli; //À medida que os menus avançam o cliente a ser usado é guardado nesta variavel
	protected static Conta con;//À medida que os menus avançam a conta a ser usada é guardada nesta variavel
	
	/**
	 * Este metodo está em repetição a mostrar o menu de operações disponiveis numa conta.
	 * Para cada uma das opções existentes e através de um switch solicita a informação
	 * necessaria ao utilizador e invoca os metodos correspondentes.
	 */
	protected static void processaMenuConta(){
		int opcao2;
	//	@SuppressWarnings("resource")
		Scanner key = new Scanner(System.in);
		do{
			System.out.println("Menu Operações Conta [ " + con.obterNib() + " ]");
			System.out.println();
			System.out.println("1 -> Levantar");
			System.out.println("2 -> Depositar");
			System.out.println("3 -> Transferir");
			System.out.println("4 -> Obter extrato");
			System.out.println("5 -> Obter saldo");
			System.out.println("6 -> Obter informaçõe");
			System.out.println("7 -> Sair do menu");
			opcao2 = key.nextInt();
			switch(opcao2){
				case 1: // Levantar
						System.out.println("Quanto dinheiro pretente levantar?");
						double levant = key.nextDouble();
						if(con.levantar(levant)){
							System.out.println("Dinheiro levantado com sucesso");
						} else{
							System.out.println("Não possui o saldo suficiente para esta operação");
						}
						System.out.println();
					break;
					
				case 2: // Depositar
						System.out.println("Quanto dinheiro quer depositar?");
						double depos = key.nextDouble();
						con.depositar(depos);
						System.out.println("Dinheiro depositado com sucesso");
						System.out.println();
					break;
					
				case 3: // Transferir
						System.out.println("Qual o nib da conta que pretende transferir?");
						int contd = key.nextInt();
						if (Banco.obterConta(contd) != null){
							System.out.println("Qual o valor a transferir?");
							double val = key.nextDouble();
							if(con.transferir(val, Banco.obterConta(contd))){
								System.out.println("Transferência efetuada com sucesso");
							} else{
								System.out.println("Não possui o saldo suficiente para esta operação");
							}
							
						}else{
							System.out.println("A conta destino especificada não existe");
							
						}
						System.out.println();
					break;
					
				case 4:
						con.mostrarExtracto();
						System.out.println();
					break;
					
				case 5:
						con.mostrarSaldo();
						System.out.println();
					break;
					
				case 6: // informação da conta
						System.out.println(con.mostrarInformacoes());
						System.out.println();
					break;
				case 7:
						System.out.println("");
					break;
					
				default: System.out.println("Opção inválida\n");
					break;
			}
		}while(opcao2 != 7);
		
	}
	
	/**
	 * Este metodo está em repetição a mostrar o menu de contas disponiveis do cliente.
	 * De notar que APENAS AS CONTAS ACTIVAS são mostradas.
	 * Após ser seleccionada uma conta é invocado o metodo processaMenuConta referente à conta escolhida
	 * @param contascliente Cliente sobre o qual se quer visualizar as contas
	 */
	protected static void processaMenuContas(ArrayList<Conta> contascliente) {
		int opcao1;
		ArrayList<Conta> contaAtiva = new ArrayList<Conta>();
		Scanner key = new Scanner(System.in);
		int i;
		boolean repetir = true;
		while(repetir){
			repetir = true;
			i = 0;
			System.out.println("Menu Contas");
			contaAtiva.clear();
			for(Conta ct: contascliente){
				if(ct.estaActiva()){
					i++;
					System.out.println(i + " -> " + ct.obterTipo() +" - Nib: " + ct.obterNib());
					contaAtiva.add(ct);
				}
			}
			i++;
			System.out.println((i) + " -> Sair do menu");
			
			opcao1 = key.nextInt();
			
	//		System.out.println("[opcao1: "+opcao1+"]");
	//		System.out.println("[contaAtiva.size(): "+contaAtiva.size()+"]");
	//		System.out.println("[i: "+i+"]");
			
			if(opcao1 == i){
				System.out.println("Sessão fechada");
				repetir = false;
			} else if((opcao1 <= contaAtiva.size()) && (opcao1 > 0)){
				con = contaAtiva.get(opcao1-1);
				processaMenuConta();
			} else {
				repetir = true;
			}
		}	
	}
	
	
	/**
	 * pode ser apagado, 
	 * apenas para testar...
	 * @param args
	
	public static void main(String[] args) {
		Banco.iniciar();
		Cliente c = Banco.procurarCliente(111);
		cli = c;
	}
	 */
}

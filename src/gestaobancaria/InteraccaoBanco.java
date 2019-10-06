package gestaobancaria;

import java.util.ArrayList;
import java.util.Scanner;

import contas.Conta;

public abstract class InteraccaoBanco {
	
	
		
	protected static Cliente cli; //� medida que os menus avan�am o cliente a ser usado � guardado nesta variavel
	protected static Conta con;//� medida que os menus avan�am a conta a ser usada � guardada nesta variavel
	
	/**
	 * Este metodo est� em repeti��o a mostrar o menu de opera��es disponiveis numa conta.
	 * Para cada uma das op��es existentes e atrav�s de um switch solicita a informa��o
	 * necessaria ao utilizador e invoca os metodos correspondentes.
	 */
	protected static void processaMenuConta(){
		int opcao2;
	//	@SuppressWarnings("resource")
		Scanner key = new Scanner(System.in);
		do{
			System.out.println("Menu Opera��es Conta [ " + con.obterNib() + " ]");
			System.out.println();
			System.out.println("1 -> Levantar");
			System.out.println("2 -> Depositar");
			System.out.println("3 -> Transferir");
			System.out.println("4 -> Obter extrato");
			System.out.println("5 -> Obter saldo");
			System.out.println("6 -> Obter informa��e");
			System.out.println("7 -> Sair do menu");
			opcao2 = key.nextInt();
			switch(opcao2){
				case 1: // Levantar
						System.out.println("Quanto dinheiro pretente levantar?");
						double levant = key.nextDouble();
						if(con.levantar(levant)){
							System.out.println("Dinheiro levantado com sucesso");
						} else{
							System.out.println("N�o possui o saldo suficiente para esta opera��o");
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
								System.out.println("Transfer�ncia efetuada com sucesso");
							} else{
								System.out.println("N�o possui o saldo suficiente para esta opera��o");
							}
							
						}else{
							System.out.println("A conta destino especificada n�o existe");
							
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
					
				case 6: // informa��o da conta
						System.out.println(con.mostrarInformacoes());
						System.out.println();
					break;
				case 7:
						System.out.println("");
					break;
					
				default: System.out.println("Op��o inv�lida\n");
					break;
			}
		}while(opcao2 != 7);
		
	}
	
	/**
	 * Este metodo est� em repeti��o a mostrar o menu de contas disponiveis do cliente.
	 * De notar que APENAS AS CONTAS ACTIVAS s�o mostradas.
	 * Ap�s ser seleccionada uma conta � invocado o metodo processaMenuConta referente � conta escolhida
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
				System.out.println("Sess�o fechada");
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

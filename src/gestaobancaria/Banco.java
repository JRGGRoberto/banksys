package gestaobancaria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import transaccoes.Deposito;
// import transaccoes.CapitalizacaoJuros;
import transaccoes.Levantamento;
import transaccoes.Transferencia;
import contas.Conta;
import contas.Prazo;
import contas.Debito;

public class Banco {

	private static final String FICHEIROCLIENTE = "dados/clientes.csv";
	private static final String FICHEIROCONTAS =  "dados/contas.csv";
	private static final String FICHEIROTRANSACCOES = "dados/transaccoes.csv";
//	private static final String FICHEIROCONFIGURACOES = "dados/configuracoes.txt";
	
	private static ArrayList<Cliente> clientes;
	private static int numerocliente; //utilizado para as numeracoes dos clientes
	private static int numeroconta;	//utilizado para as numeracoes das contas
	
   public static void iniciar(){
		clientes = new ArrayList<Cliente>();
	
		/*
		numeroconta = 2451;
		numerocliente = 1543;
		simularDados();  */
		Banco.carregarDados();
	}
	


	public static int gerarNumConta() {
		return numeroconta++;
	}
	
	public static int gerarNumCliente() {
		return numerocliente++;
	}
	

	//METODOS CLIENTES
	
	/**
	 * Desactiva o cliente com o id passado como parametro. Para isto deve usar
	 * o metodo procurar cliente do banco para encontrar o cliente com esse id. Caso exista
	 * define o estado como inactivo através do metodo desactivar do cliente
	 * @param idcliente id do cliente a desactivar
	 * @return booleano a indicar se foi ou nao possivel de desactivar o cliente
	 */
	public static boolean desactivarCliente(int idcliente){
		for(Cliente c: clientes){
			if(c.userid == idcliente){
				c.activo = false;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Lista os clientes do banco que tem o nome igual ao recebido como parametro.
	 * Caso seja passada uma string vazia entao sao listados todos os clientes
	 * @param criterionome nome do cliente a listar
	 */
//	@SuppressWarnings("unchecked")
	public static void listarClientes(String criterionome){
		
		Collections.sort(clientes);
		
		if (criterionome.isEmpty()){
			for(Cliente c: clientes){
				System.out.println(c.obterInformacoes());
			}
		} else{
			for(Cliente c: clientes){
				if(c.nome.equalsIgnoreCase(criterionome)){
						System.out.println(c.obterInformacoes());
				}
			}
		}
		
	}
	
	/**
	 * Procura o cliente com o id recebido como parametro e devolve-o caso exista.
	 * Caso não exista devolve null. Este metodo e utilizado noutros metodos do banco.
	 * @param userid id do cliente a procurar
	 * @return devolve o cliente com o id procurado ou null
	 */
	public static Cliente procurarCliente(int userid){
		for(Cliente c: clientes){
			if(c.userid == userid){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Utiliza o userid para encontrar o respectivo cliente e se existir confirma
	 * que a password e a correcta para esse utilizador. Caso isso se verifique 
	 * devolve o cliente que fez login
	 * @param userid id do utilizador que esta a fazer login
	 * @param password passwor dod utilizador que esta a fazer login
	 * @return Cliente que acabou de fazer login ou null caso não coincidam as credênciais
	 */
	public static Cliente validarLogin(int userid, int password) {

		for(Cliente cl: clientes){
			if((cl.userid == userid)&&(cl.password == password)) {
				return cl;
			}
		}
		return null;
	}
	
	/**
	 * Cria um cliente com os dados recebidos e adiciona-o à lista de 
	 * clientes do banco
	 * @param nome nome do cliente a adicionar
	 * @param userid userid do cliente a adicionar
	 * @param password password do cliente a adicionar
	 */
	public static void criarCliente(String nome, int userid, int password){
		clientes.add( new Cliente(nome, userid, password));
		
	}
	
	public static void criarCliente(String nome, int userid, int password, boolean activo){
		clientes.add( new Cliente(nome, userid, password, activo));
		
	}
	
	
	/**
	 * Procurar em todos os clientes por uma conta ccom o nib recebido com parâmetro
	 * Devolve o objecto conta caso exista ou null caso não exista
	 * @param nib da conta a procurar
	 * @return Conta com o nib especificado
	 */
	public static Conta obterConta(int nib){
		for(Cliente c: clientes){
			if (c.obterConta(nib) != null){
				return c.obterConta(nib);
			}
		}
		return null;
	}
	
	/*
	 * Procura o cliente com o id recebido por parâmetro. Caso este exista adiciona
	 * o objecto conta recebido por parâmetro às contas deste cliente
	 * @param idcliente id do cliente a adicionar a Conta
	 * @param c Conta a adicionar
	 */
	private static void adicionarConta(int idcliente, Conta c){
		for(Cliente cl: clientes){
			if(cl.userid == idcliente){
				cl.conta.add(c);
			}
		}
	}
	
	/**
	 * Cria um conta para o cliente c com o tipo conta
	 * ESTE METODO ASSUME QUE O TIPO DE CONTA E VALIDO (1- DEBITO / 2- PRAZO)
	 * @param c O cliente sobre o qual vai ser criada a conta
	 * @param tipoconta o tipo da conta a criar
	 * @return O nib da nova conta criada
	 */
	public static int criarConta(Cliente c, int tipoconta){
		if (tipoconta == 1){
			c.conta.add(new Debito());
			return c.conta.get(c.conta.size()-1).obterNib();
		}
		if (tipoconta == 2){
			c.conta.add(new Prazo());
			return c.conta.get(c.conta.size()-1).obterNib();
		}
		return 0;
	}
	
	/**
	 * Projecto Final - Parte II
	 * Página 8/15
	 * 3.1 Testes
	 */
	public static void simularDados(){
		Cliente joao = new Cliente("joao", 111,222);
		joao.adicionarConta(new Prazo());
		joao.adicionarConta(new Debito());
		
		Cliente pedro = new Cliente("pedro", 123, 456);
		pedro.adicionarConta(new Prazo());
		pedro.adicionarConta(new Debito());
		
		clientes.add(joao);
		clientes.add(pedro);
	}
	
	

	//25-05-2015 2h 19:00
	private static void carregarDados(){
		File ficheiroclientes = new File(FICHEIROCLIENTE);
		File ficheirocontas = new File(FICHEIROCONTAS);
		File ficheirotransacoes = new File(FICHEIROTRANSACCOES);
	//	File ficheiroconfiguracoes = new File(FICHEIROCONFIGURACOES);
		
		if(!ficheiroclientes.exists()){
			System.out.println("Não é possivel carregar dados pois não existe o ficheiro de clientes para carregar.");
			return;
		}
		
		if(!ficheirocontas.exists()){
			System.out.println("Não é possivel carregar dados pois não existe o ficheiro de contas para carregar.");
			return;
		}
		
		if(!ficheirotransacoes.exists()){
			System.out.println("Não é possivel carregar dados pois não existe o ficheiro de transações para carregar.");
			return;
		}
		
		
		try{
			importarClientes(ficheiroclientes);
			importarContas(ficheirocontas);
			importarTransaccoes(ficheirotransacoes);
		} catch(Exception e){
			System.out.println("Erro na importação dos ficheiros");
		}
	}
	
	
	private static void importarClientes(File ficheiroclientes) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(ficheiroclientes));
		String texto = br.readLine();
		
		while (texto != null){
			String[] dadosficheiro = texto.split(";");
			String nome = dadosficheiro[0];
			int userid = Integer.parseInt(dadosficheiro[1]);
			int password = Integer.parseInt(dadosficheiro[2]);
			boolean activo = Boolean.parseBoolean(dadosficheiro[3]);
			
			Banco.criarCliente(nome, userid, password, activo);
			texto = br.readLine();
		}
		br.close();
	}
	
	private static void importarContas(File ficheiroclientes) throws IOException, ParseException{
		BufferedReader br = new BufferedReader(new FileReader(ficheiroclientes));
		String texto = br.readLine();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		int tipoconta;
		while (texto != null){
			String[] dadosficheiro = texto.split(";");
			int userid = Integer.parseInt(dadosficheiro[0]);
			String tConta = dadosficheiro[1];
			if (tConta.equals("Debito")){
				tipoconta = 1;
			} else {
				tipoconta = 2;
			}
			double saldo = Double.parseDouble(dadosficheiro[2]);
			int nib = Integer.parseInt(dadosficheiro[3]);
			boolean activo = Boolean.parseBoolean(dadosficheiro[4]);			
			Date datacriacao = formato.parse(dadosficheiro[5]);
			if(tipoconta == 1){
				Banco.adicionarConta(userid, new Debito(saldo, nib, activo, datacriacao));
			} else {
				double jurosAcumulado = Double.parseDouble(dadosficheiro[6]);
				Date validadeConta = formato.parse(dadosficheiro[7]);
				Banco.adicionarConta(userid, new Prazo(saldo, nib, activo, datacriacao, jurosAcumulado, validadeConta));
			}
			texto = br.readLine();
		}
		br.close();
	}
	
	private static void importarTransaccoes(File ficheirotransaccoes) throws IOException, ParseException{
		BufferedReader br = new BufferedReader(new FileReader(ficheirotransaccoes));
		String texto = br.readLine();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		while(texto != null){
			String[] dadosficheiro = texto.split(";");
			int    nib  = Integer.parseInt(dadosficheiro[0]);
			String tipo = dadosficheiro[1];
			int    nib1 = Integer.parseInt(dadosficheiro[2]);
			double val  = Double.parseDouble(dadosficheiro[3]);
			Date   dtop = formato.parse(dadosficheiro[4]);
			
			if((tipo.equals("Deposito"))|| (tipo.equals("DEPOSI"))){
				Banco.obterConta(nib).adicionarTransaccao(new Deposito(dtop, nib1, val));
				
			} else if((tipo.equals("Levantamento"))||(tipo.equals("LEVANT"))){
				
				Banco.obterConta(nib).adicionarTransaccao(new Levantamento(dtop, Banco.obterConta(nib1), val));
			} else if((tipo.equals("Transferencia"))||(tipo.equals("TRANSF"))){
				
				int    nib3 = Integer.parseInt(dadosficheiro[5]);
				Banco.obterConta(nib).adicionarTransaccao(new Transferencia(dtop, Banco.obterConta(nib1), val, Banco.obterConta(nib3)));
			}
			texto = br.readLine();
		}
		br.close();
	}
	
	private static void gurdartransaccoes(){
		try{
			PrintWriter pw = new PrintWriter(FICHEIROTRANSACCOES);
			
			for(Cliente c: clientes){
				for(int i=0; i < c.conta.size(); i++){
					for(int j=0; j < c.conta.get(i).obterTransaccoes().size(); j++ ){
						pw.append(c.conta.get(i).obterNib() + ";");
						c.conta.get(i).obterTransaccoes().get(j).escreverFicheiro(pw);
					}
				}
			}
			pw.flush();
			pw.close();
		} catch(FileNotFoundException e){
			System.out.println("A pasta para gravação de contas não existe");
		}
	}	
	
	
	
	private static void guardarContas(){
		try{
			PrintWriter pw = new PrintWriter(FICHEIROCONTAS);
			
			for(Cliente c: clientes){
				for(int i=0; i < c.conta.size(); i++){
					pw.append(c.userid + ";");
					c.conta.get(i).escreverFicheiro(pw);
				}
			}
			pw.flush();
			pw.close();
		} catch(FileNotFoundException e){
			System.out.println("A pasta para gravação de contas não existe");
		}
	}
	

	private static void guardarClientes(){
		try{
			PrintWriter pw = new PrintWriter(FICHEIROCLIENTE);
			
			for(Cliente c: clientes){
				c.escreverFicheiro(pw);
			}
			pw.flush();
			pw.close();
		} catch(FileNotFoundException e){
			System.out.println("A pasta para gravação de clientes não existe");
		}
	}
	
	public static void guardarDados(){
		gurdartransaccoes();
		guardarContas();
		guardarClientes();
	}
}

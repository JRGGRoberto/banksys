package gestaobancaria;
import java.io.PrintWriter;
import java.util.ArrayList;

import contas.Conta;

public class Cliente implements Comparable<Object>{
	protected String nome;
	protected int password;
	protected int userid;
	protected boolean activo;
	protected ArrayList<Conta> conta = new ArrayList<Conta>();
	
	@Override
	public int compareTo(Object arg0){
		Cliente clienteAComparar = (Cliente)arg0;
		return nome.compareTo(clienteAComparar.nome);
	}
	
	Cliente(String nome, int userid, int password){
		this.nome = nome;
		this.userid = userid;
		this.password = password;
		this.activo = true;
	}
	
	Cliente(String nome, int userid, int password, boolean activo){
		this.nome = nome;
		this.userid = userid;
		this.password = password;
		this.activo = activo;
	}
	
	
	
	public String obterInformacoes(){
		return "Nome: " + this.nome + "\nUserid: " + this.userid;
	}
	
	public void adicionarConta(Conta conta){
		this.conta.add(conta);
	}
	
	public ArrayList<Conta> obterContas(){
		return conta;
		
	}
	
	public Conta obterConta(int nib){
		for(Conta c: conta){
			if(nib == c.obterNib()){
				return c;
			}
		}
		return null;	
	}
	
	public void escreverFicheiro(PrintWriter pw){
		pw.write(nome + ";" + userid + ";" + password + ";" + activo + "\n");
	}
	
}
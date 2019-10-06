package transaccoes;

import java.io.PrintWriter;
import java.util.Date;

public abstract class Transaccao {
	protected String tipo;
	protected Date dthora;
	protected int nib;
	protected double valor;
	
	public Transaccao(String tipo, Date dthora, int nib, double valor) {
		this.tipo = tipo;
		this.dthora = dthora;
		this.nib = nib;
		this.valor = valor;
	}
	
	public abstract String mostrar();
	
	public String obterTipo(){
		return tipo;
	};
	
	public abstract void escreverFicheiro(PrintWriter pw);
	
}

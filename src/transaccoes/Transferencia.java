package transaccoes;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import contas.Conta;

public class Transferencia extends Transaccao{

	protected int nibdest;
	
	/**
	 * Transferencia
	 * @param dthora Date 
	 * @param conta  Conta
	 * @param valor  double
	 * @param contaDest Conta
	 */
	public Transferencia(Date dthora, Conta conta, double valor, Conta contaDest) {
		super("Transferencia", dthora, conta.obterNib(), valor);
		nibdest = contaDest.obterNib();
		
	}

	@Override
	public String mostrar() {
		return this.tipo + " - " + this.dthora.toString() + " - " + this.nib + " - " + this.nibdest + " - " + this.valor + "€";
	}
	
/*
protected String tipo;
	protected Date dthora;
	protected int nib;
	protected double valor;
//	nif     tipo 			nif2	valor 	dtoperacao				nif3
	*/
	public void escreverFicheiro(PrintWriter pw){
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		pw.write(this.tipo + ";" + this.nib + ";" + this.valor + ";" + formato.format(this.dthora).toString() + ";" + this.nibdest + "\n");
	}
}

/*
 * 
 pw.write(this.tipo + ";" + this.nib + ";" + this.valor + ";" + this.dthora + "\n");
 
int    nib  = Integer.parseInt(dadosficheiro[0]);
String tipo = dadosficheiro[1];
int    nib1 = Integer.parseInt(dadosficheiro[2]);
double val  = Double.parseDouble(dadosficheiro[3]);
Date   dtop = formato.parse(dadosficheiro[4]);

if (tipo =="Transferencia"){
int    nib3 = Integer.parseInt(dadosficheiro[5]);
}
Banco.obterConta(nib).adicionarTransaccao(new Transferencia(dtop, Banco.obterConta(nib1), val, Banco.obterConta(nib3)));

*
*/
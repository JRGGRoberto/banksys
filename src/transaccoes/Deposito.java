package transaccoes;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import contas.Conta;

public class Deposito extends Transaccao{

	public Deposito(Date dthora, int nib, double valor) {
		super("Deposito", dthora, nib, valor);
	}


	public Deposito(Date dthora, Conta conta, double valor) {
		super("Deposito", dthora, conta.obterNib(), valor);
	}


	@Override
	public String mostrar() {
		return this.tipo + " - " + this.dthora.toString() + " - " + this.nib + " - " + this.valor + "€";
	}


	@Override
	public void escreverFicheiro(PrintWriter pw) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		pw.write(this.tipo + ";" + this.nib + ";" + this.valor + ";" + formato.format(this.dthora).toString() + "\n");
		
	}
	

}

package transaccoes;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import contas.Conta;

public class Levantamento extends Transaccao{

	public Levantamento(Date dthora, Conta conta, double valor) {
		super("Levantamento", dthora, conta.obterNib(), valor);
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
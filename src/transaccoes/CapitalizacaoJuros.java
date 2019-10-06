package transaccoes;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import contas.Conta;

public class CapitalizacaoJuros extends Transaccao{
	
	protected int niib;
	
	public CapitalizacaoJuros(Date dthora, Conta conta, double valor) {
		super(conta.obterTipo(), dthora, conta.obterNib(), valor);
		niib = conta.obterNib();
		conta.depositar(valor);
	}


	@Override
	public String mostrar() {
		return "Capitalização - " + this.dthora.toString() + " - " + niib  + " - " + this.valor + "€";
	
	}

	@Override
	public void escreverFicheiro(PrintWriter pw) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		pw.write("CapitalizacaoJuros;" + nib + ";" + valor + ";" + formato.format(this.dthora).toString() +";"+ niib + "\n");
		
	}

}

package contas;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import transaccoes.Levantamento;
import transaccoes.Transferencia;

public class Debito extends Conta{
	
	public Debito() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Debito(double saldo, int nib, boolean activa, Date datacriacao) {
		super(saldo, nib, activa, datacriacao);
	}
	
	
	public void escreverFicheiro(PrintWriter pw){
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		pw.write("Debito;"+saldo + ";" + nib + ";" + activa + ";" + formato.format(datacriacao).toString() + "\n");
		//	123;  Debito;   0.0;        2454;        true;         27-03-2012 15:47:03
	}
	
	@Override
	public boolean levantar(double valor) {
		if(this.saldo >= valor){
			this.saldo -= valor;
			transaccoes.add(new Levantamento(new Date(),this,valor));	
			return true;
		} else
			return false;
	}
	
	@Override
	public boolean transferir(double valor, Conta contadestino) {
		if(this.saldo >= valor){
			this.saldo -= valor;
			contadestino.saldo += valor;
			transaccoes.add(new Transferencia(new Date(),this,valor,contadestino));	
			contadestino.adicionarTransaccao(new Transferencia(new Date(),this,valor,contadestino));
			return true;
		} else
			return false;
	}

	@Override
	public String mostrar() {
		// TODO Auto-generated method stub
		return "Saldo: " + this.saldo + " | Nib: "+ this.nib +" | Data: " + this.datacriacao + " | Activa: " + this.activa;
	}

	@Override
	public String mostrarInformacoes() {
		return "Saldo: " + this.saldo + " | Nib: "+ this.nib +" | Data: " + this.datacriacao + " | Activa: " + this.activa  + " | Qnt transações: " + this.transaccoes.size();
	}

	@Override
	public void mostrarSaldo() {
		// TODO Auto-generated method stub
		System.out.println("Saldo: " + this.saldo);
		
	}

	@Override
	public String obterTipo() {
		// TODO Auto-generated method stub
		return "Debito";
	}

}




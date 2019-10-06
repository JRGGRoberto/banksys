package contas;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import transaccoes.Levantamento;
import transaccoes.Transferencia;

public class Prazo extends Conta{

	static final float txjuros = (float) 0.05;
	
	protected double jurosAcumulado;
	protected Date validadeConta;
	
	public void escreverFicheiro(PrintWriter pw){
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		
		pw.write("Prazo;"+saldo + ";" + nib + ";" + activa + ";" + formato.format(datacriacao).toString() + ";" + jurosAcumulado + ";" + formato.format(validadeConta).toString() + "\n");
	     // 123;  Prazo;    0.0;       2453;         true;                   27-03-2012 15:47:03;                     0.0;                27-03-2013 15:47:03
	}
		
	
	public Prazo() {
		super();
		validadeConta  = new Date(new Date().getTime()+365*24*60*60*1000);
		jurosAcumulado=0;
	}
	
	public Prazo(double saldo, int nib, boolean activa, Date datacriacao, double juros, Date validad) {
		super(saldo, nib, activa, datacriacao);
		this.jurosAcumulado = juros;
		this.validadeConta = validad;
	}
	
	
	@Override
	public boolean levantar(double valor) {
		//////
		if(valor < 0){
			return false;
		}
		//////
		double tempoDecorrido = 0;
		if(this.saldo >= valor){
			this.saldo -= valor;
			transaccoes.add(new Levantamento(new Date(),this,valor));	
			tempoDecorrido = validadeConta.getTime() - new Date().getTime();
			this.jurosAcumulado = tempoDecorrido * txjuros * valor;
			
			return true;
		} else
			return false;
	}

	@Override
	public boolean transferir(double valor, Conta contadestino) {
		double tempoDecorrido = 0;
		if(this.saldo >= valor){
			this.saldo -= valor;
			contadestino.saldo += valor;
			transaccoes.add(new Transferencia(new Date(),this,valor,contadestino));	
			contadestino.adicionarTransaccao(new Transferencia(new Date(),this,valor,contadestino));
		 	tempoDecorrido = validadeConta.getTime() - new Date().getTime();
			this.jurosAcumulado = tempoDecorrido * txjuros * valor;
			return true;
		} else
			return false;
	}

	@Override
	public String mostrar() {
		double tempoDecorrido = 0;
		tempoDecorrido = validadeConta.getTime() - new Date().getTime();
		return Double.toString(tempoDecorrido * txjuros + saldo);
	}

	@Override
	public String mostrarInformacoes() {
		// TODO Auto-generated method stub
		return "Saldo: " + this.saldo + " | Nib: "+ this.nib +" | Data: " + this.datacriacao + " | Activa: " + this.activa;
	}

	@Override
	public void mostrarSaldo() {
		// TODO Auto-generated method stub
		System.out.println("Saldo: " + this.saldo);
	}

	@Override
	public String obterTipo() {
		// TODO Auto-generated method stub
		return "Prazo";
	}

}


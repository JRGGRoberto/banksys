package outros;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Teste {

	public static void main(String[] args) {
		try{
			PrintWriter pw = new PrintWriter("dados/teste.txt");
			
			String a = "123";
			pw.append(a);
			pw.append("456");
			pw.write("aee");
			
			pw.flush();
			pw.close();
		} catch(FileNotFoundException e){
			System.out.println("A pasta para gravação de contas não existe");
		}

	}

}

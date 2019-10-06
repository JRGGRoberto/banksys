package outros;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//import javax.swing.JFrame;
//https://www.youtube.com/watch?v=IZKxMDLs658
import javax.swing.*;

public class Tela  extends JFrame implements ActionListener{

	JLabel lb1, lb2;
	JTextField tf_entrada;
	JButton btn, btn2;

	public Tela(){
		setTitle("Form1");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
	//	getContentPane().setBackground(new Color(0,0,50));
	//	getContentPane().setBackground(Color.red);
		
		int linha = 20;
		int coluna = 20;
		lb1 = new JLabel("123456789012345678901230");
		lb2 = new JLabel("Hey1");
		
		tf_entrada = new JTextField();
		btn = new JButton("OK");
		btn2 = new JButton("OK2");
		
		
		lb1.setBounds(coluna, linha      , 700, 40);
		lb2.setBounds(coluna, linha += 20, 700, 40);
		tf_entrada.setBounds(coluna+35, linha+10, 200, 20);
		btn.setBounds(coluna+60, linha + 50     , 80, 30);
		btn2.setBounds(coluna+200, linha + 50     , 80, 30);
		
		
		lb1.setFont(new Font("Courier", Font.BOLD, 20));
		
	//	getContentPane().add(lb1);
		getContentPane().add(lb2);
		getContentPane().add(tf_entrada);
		getContentPane().add(btn);
		getContentPane().add(btn2);
		
		lb1.computeVisibleRect(getBounds());
		
		btn.addActionListener(this);
		btn2.addActionListener(this);
		
	//	getContentPane().removeAll();
	}
	
	public static void main(String[] args) {
		JFrame obj_teste = new Tela();
		obj_teste.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent acao){
		if(acao.getSource() == btn){
			int qnt = tf_entrada.getText().length();
			JOptionPane.showMessageDialog(null, "Tamanho do texto " + qnt);
		}
		
		if(acao.getSource() == btn2){
			int qnt = tf_entrada.getText().length();
			JOptionPane.showMessageDialog(null, "Ok2 - do texto " + qnt);
		}
	}
}
package outros;
import java.util.Calendar;

public class DtTime {
	
	public static String getTD(){
		Calendar c = Calendar.getInstance();
		
		String minuto =  Integer.toString(c.get(Calendar.MINUTE));
		if (minuto.length() < 2){
			minuto = "0"+minuto;
		}
		String mes = Integer.toString(c.get(Calendar.MONTH) +1);
		if (mes.length() < 2){
			mes = "0"+mes;
		}
				
		String h = Integer.toString(c.get(Calendar.HOUR_OF_DAY)) + ":" + minuto;
		String body = 
				 h + " - " 
				+ Integer.toString(c.get(Calendar.DAY_OF_MONTH)) 
				+ "/" + mes
				+ "/" + Integer.toString(c.get(Calendar.YEAR));
		;
		return body;
	}
	
	public static void main(String[] args) {
		System.out.println(getTD());
	}
}

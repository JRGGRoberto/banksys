package outros;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TesteData {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		Date   dtop = formato.parse("27-03-2013 15:47:03");
		System.out.println(dtop.toString());
		System.out.println(formato.format(dtop).toString());
		
	}

}

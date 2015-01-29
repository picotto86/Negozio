import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;


public class Leggi_Totali {
	
	public Leggi_Totali(){
		
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		BufferedReader in = null;
		String linea=null;
		
		try {
			in = new BufferedReader(new FileReader("totali.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		try {
			while ((linea = in.readLine()) != null) {
				
				String[] st = linea.split(";");
				java.util.Date daConfrontare = null;
				
				String data = st[0];
				String tot = st[1];
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
				try {
					daConfrontare = sdf.parse(data);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.util.Date oggi = java.util.GregorianCalendar.getInstance().getTime();
				oggi.setHours(0);
				oggi.setMinutes(0);
				oggi.setSeconds(0);
				
				System.out.println(sdf.format(daConfrontare));
				System.out.println(sdf.format(oggi));

				if(sdf.format(daConfrontare).equals(sdf.format(oggi))) {
				    System.out.println("oggi  uguale a daConfrontare");
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
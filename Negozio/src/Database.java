import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Database {

	public static Map<String, Sigaretta> database;
	public static List<Sigaretta> vendita;
	static float tot_giornata;
	public Database() {

		database = new HashMap<String, Sigaretta>();
		vendita = new ArrayList<Sigaretta>();
	}

	public static int salvaDb(){
	
	int i=0;
	
	Object[][] rowData=new Object[database.size()][11];
	
	try{
	
		FileOutputStream file = new FileOutputStream("mag.csv");
    
		PrintStream Output = new PrintStream(file);
	
		System.out.println("Magazzino:");
		
		for (Iterator<Sigaretta> it = database.values().iterator(); it.hasNext() || i < database.size(); i++) {

			Sigaretta s = it.next();
			
			rowData[i][0] = s.tipo;			
			rowData[i][1] = s.nome;
			rowData[i][2] = s.cod;
			rowData[i][3] = new Float(s.prezzoxchilo);
			rowData[i][4] = new Float(s.vecchiaqta);
			rowData[i][5] = new Float(s.nuovaqta);
			rowData[i][6] = new Float(s.prezzo_confezione);
			rowData[i][7] = new Float(s.peso);
			rowData[i][8] = new String(s.barcode);
			rowData[i][9] = new String(s.barcode_stecca);
			rowData[i][10]= new Integer(s.qtaxstecca);

			System.out.println(rowData[i][0]+";"+rowData[i][1]+";"+rowData[i][2]+";"
			+rowData[i][3]+";"+rowData[i][4]+";"+rowData[i][5]+";"+rowData[i][6]+";"
			+rowData[i][7]+";"+rowData[i][8]+";"+rowData[i][9]+";"+rowData[i][10]);
			
			
			Output.println((rowData[i][0]).toString().toUpperCase()+";"+(rowData[i][1]).toString().toUpperCase()+
			";"+rowData[i][2]+";"+rowData[i][3]+";"+rowData[i][4]+";"+rowData[i][5]+";"+rowData[i][6]+";"
			+rowData[i][7]+";"+rowData[i][8]+";"+rowData[i][9]+";"+rowData[i][10]);
			
		}
	
	}
	
	catch (IOException e) {
	      System.out.println("Errore: " + e);
	      System.exit(1);
	}     
	      FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedWriter out = null;
		try {
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
			
			java.util.Date oggi = java.util.GregorianCalendar.getInstance().getTime();
			oggi.setHours(0);
			oggi.setMinutes(0);
			oggi.setSeconds(0);
			
	          // apro il file
	          fstream = new FileInputStream("totali.csv");
	      
	          // prendo l'inputStream
	          in = new DataInputStream(fstream);
	          BufferedReader br = new BufferedReader(new InputStreamReader(in));
	          String strLine;
	          StringBuilder fileContent = new StringBuilder();
	      boolean flag=false;
	          // Leggo il file riga per riga
	          while ((strLine = br.readLine()) != null) {
	            
	             String[] st = strLine.split(";");
					java.util.Date daConfrontare = null;
					
					String data = st[0];
					String tot = st[1];
				
					try {
						daConfrontare = sdf.parse(data);
					} catch (ParseException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
	            
	             if(sdf.format(daConfrontare).equals(sdf.format(oggi))){
	                // se la riga ï¿½ uguale a quella ricercata
	                fileContent.append(sdf.format(oggi)+";"+tot_giornata+System.getProperty("line.separator"));
	                flag=true;
	                
	             } else {
	            	 
	            	
	                // ... altrimenti la trascrivo cosâ€œ com'ï¿½
	                fileContent.append(strLine);
	                fileContent.append(System.getProperty("line.separator"));
	             }
	          }
	          if(flag==false){
	        	  
	        	  fileContent.append(sdf.format(oggi)+";"+tot_giornata+System.getProperty("line.separator"));
	        	  
	        	  
	          }
	   
	          // Sovrascrivo il file con il nuovo contenuto (aggiornato)
	          FileWriter fstreamWrite = new FileWriter("totali.csv");
	          out = new BufferedWriter(fstreamWrite);
	          out.write(fileContent.toString());
	      
	       } catch (Exception e1) {
	          e1.printStackTrace();
	  
	       } finally {
	          // chiusura dell'output e dell'input
	          try {
	             fstream.close();
	             out.flush();
	             out.close();
	             in.close();
	          } catch (IOException e2) {
	             e2.printStackTrace();
	          }
	       }
	    
	
	
	return 0;
}

	@SuppressWarnings("deprecation")
	public int caricaDb() throws IOException {
		
		BufferedReader in = null;
		String linea;
		try {
			in = new BufferedReader(new FileReader("mag.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return 0;
		}
		String[] st;
		String cod;
		String nome;
		String tipo;
		String barcode,barcode_stecca;
		float prezzoxchilo, vecchiaqta,nuovaqta,prezzo_confezione,peso;
		String qtaxstecca;
		Sigaretta sig;
		while ((linea = in.readLine()) != null) {
			
			System.out.println(linea);

			st = linea.split(";");
			
			DecimalFormat df = new DecimalFormat("###.##");
			//System.out.println("kilobytes (DecimalFormat) : " + df.format(kilobytes));

			tipo = st[0];
			nome = st[1];
			cod = st[2];
			prezzoxchilo = Float.parseFloat(st[3]);
			vecchiaqta=(float) (Math.rint(Float.parseFloat(st[4])*Math.pow(10,2))/Math.pow(10,2));	
			nuovaqta=(float) (Math.rint(Float.parseFloat(st[5])*Math.pow(10,2))/Math.pow(10,2));
			prezzo_confezione=Float.parseFloat(st[3])*Float.parseFloat(st[7])/Float.parseFloat(st[10]);
			peso=Float.parseFloat(st[7]);
			barcode=st[8];
			barcode_stecca=st[9];
			qtaxstecca=(st[10]);
			sig = new Sigaretta(tipo,nome,cod,prezzoxchilo ,vecchiaqta,nuovaqta,prezzo_confezione
			,peso,barcode,barcode_stecca,qtaxstecca);

			database.put(sig.barcode, sig);
			

		}
		
		
		try {
			in = new BufferedReader(new FileReader("totali.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		try {
			while ((linea = in.readLine()) != null) {
				
				 st = linea.split(";");
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
				    System.out.println("oggi ï¿½ uguale a daConfrontare");
				    
				    tot_giornata=Float.parseFloat(st[1]);
				    
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		return 1;
	}
}
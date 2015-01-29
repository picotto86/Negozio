import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class TabellaOrdine extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -718426940244746988L;
	String[] columnNames = { "Tipo", "Nome", "Codice","Vecchia qta","In Arrivo","Qta Attuale","Consumo","Ordine" };
	Object[][] rowData;
	static float consumo;
	int i,len;
	
	public TabellaOrdine(){
		
		len=Database.database.size();
		consumo=0;
		rowData = new Object[len][9];
		i = 0;
		boolean flag=false;
		
		 Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File("output.xls"));
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //Seleziono il foglio sul quale voglio operare (il primo foglio ha indice 0)
         Sheet sheet = workbook.getSheet(0);
          
         //Leggo tutte le righe
         int riga = 1;//indice riga, parto da 1 per saltare l'intestazione dei campi
         int numeroRighe = sheet.getRows();//calcolo quante righe ci sono nel foglio
          
        
		for (Iterator<Sigaretta> it = Database.database.values().iterator(); it
				.hasNext() || i < len; i++) {

			Sigaretta s = it.next();

			rowData[i][0] = s.tipo;
			rowData[i][1] = s.nome;
			rowData[i][2] = s.cod;
			rowData[i][3] = s.vecchiaqta;
			flag=false;
			//Attento che c'ï¿½ una riga in piï¿½ per l'intestazione!
	         for(int j = 1; j < numeroRighe; j++){
	        	 
	        	 LabelCell lc = (LabelCell) sheet.getCell(0, j); 
	                String codC = lc.getString(); 
	                
	                if(s.cod.compareTo(codC)==0){
	                	
	                	NumberCell nc = (NumberCell) sheet.getCell(1, j); 
		                double qtaC = nc.getValue(); 
	                	rowData[i][4]=new Float(qtaC)/s.peso;
	                	flag=true;
	                	
	                }
	        	 
	         }
	         if(flag==false){
	        
	        	 rowData[i][4] = new Float(0.0);//qta in arrivo
	        	 
	         }
			
			
			rowData[i][5] = s.nuovaqta;
			rowData[i][6] = (float) (Math.rint((s.vecchiaqta-s.nuovaqta)*Math.pow(10,2))/Math.pow(10,2));
			rowData[i][7] = new Float(0);
			rowData[i][8] = s.peso;
			consumo=consumo+ (s.vecchiaqta-s.nuovaqta)*10*s.prezzoxchilo*s.peso/new Integer(s.qtaxstecca);
		}
		
		workbook.close();

		
		
	}

	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public String getColumnName(int c) {
		// TODO Auto-generated method stub
		return columnNames[c];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return len;
	}

	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
		return rowData[r][c];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		// TODO Auto-generated method stub
		switch (col) {
		
		case 3:
			rowData[row][3]=new Float(value.toString());
			Magazzino.simpleModel.setValueAt(rowData[row][3], row, 4);
			break;
		case 4:
			rowData[row][4]=new Float(value.toString());
			
			break;
		case 5:
			rowData[row][5]=new Float(value.toString());
			Magazzino.simpleModel.setValueAt(rowData[row][5], row, 5);
			break;
		
		case 7:
		
		rowData[row][7]=new Float( value.toString());
		
		break;
		
		}
		
	}

}
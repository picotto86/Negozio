import java.util.Iterator;

import javax.swing.table.AbstractTableModel;


public class TabellaRicerca extends AbstractTableModel {
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 7118553400239610193L;

	String[] columnNames = { "Tipo", "Nome", "Codice","Prezzo confezione","Barcode" };
	
	int len,i,j;
	Object[][] rowData;

	public TabellaRicerca(String arg0) {
		
		len=Database.database.size();
		
		rowData = new Object[len][5];
		i = 0;
		j= 0;
		for (Iterator<Sigaretta> it = Database.database.values().iterator(); it
				.hasNext() || i < len;i++) {

			Sigaretta s = it.next();
			
			if(s.nome.contains(arg0.toUpperCase())){

			rowData[j][0] = s.tipo;
			rowData[j][1] = s.nome;
			rowData[j][2] = s.cod;
			rowData[j][3] = new Float(s.prezzo_confezione);
			rowData[j][4] = s.barcode;
			j=j+1;
			}
		}

	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return j;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return rowData[arg0][arg1];
	}
	
	public String getColumnName(int c) {
		return columnNames[c];
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	
	

}
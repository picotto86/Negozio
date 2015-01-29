import javax.swing.table.AbstractTableModel;

public class TabellaVendita extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3887746332499981602L;
	String[] columnNames = { "Tipo", "Nome", "Codice","Prezzo per chilo", "qta","Prezzo confezione","Tot","Barcode","Barcode Stecca" };
	
	int len,i;
	Object[][] rowData;
	
	static float prezzo=0;
	
	public TabellaVendita(){
		
		len=Database.vendita.size();
		
		rowData = new Object[len][9];
		i = 0;
		
		prezzo=0;
		
		for ( i=0;i<len;i++){
				
			Sigaretta s = Database.vendita.get(i);

			rowData[i][0] = s.tipo;
			rowData[i][1] = s.nome;
			rowData[i][2] = s.cod;
			rowData[i][3] = new Float(s.prezzoxchilo);
			rowData[i][4] = new Float(s.nuovaqta);
			rowData[i][5] = new Float(s.prezzo_confezione); 
			rowData[i][6] = new Float(s.prezzoxchilo)*s.peso*s.nuovaqta/new Integer(s.qtaxstecca);
			rowData[i][7] = s.barcode;
			rowData[i][8] = s.barcode_stecca;
			
			prezzo = (float) (Math.round( (prezzo + (Float)(rowData[i][6]))* Math.pow( 10, 2 ) )/Math.pow( 10, 2 ));

		}
		
		
		
	}
	
	public void setValueAt(Object value, int row, int col) {
		Sigaretta s;
		switch (col) {
		
		case 4:
			
			float vecchiaqta=(Float) rowData[row][4];
			
			s=Database.vendita.get(row);
			s.nuovaqta=new Float(value.toString());
			rowData[row][4]=new Float(value.toString());
			System.out.println("nuovo qta: "+ new Float(s.nuovaqta).toString());
			prezzo=prezzo - vecchiaqta*(Float)rowData[row][5] +s.nuovaqta*(Float)rowData[row][5];
			rowData[row][6] = new Float(s.prezzoxchilo)*new Float(s.nuovaqta)*new Float(s.peso)/new Integer(s.qtaxstecca);
			System.out.println("vecchia qta:"+vecchiaqta);
			System.out.println("nuovo prezzo:"+prezzo);
			Vendita.label_importo.setText(Float.toString(TabellaVendita.prezzo));
			Vendita.refreshTable();
			Vendita.tabella.revalidate();
			Vendita.textField.requestFocusInWindow();

			break;
			
		
		default:
			break;
		}
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.length;
	}

	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
		return rowData[r][c];
	}
	
	public String getColumnName(int c) {
		return columnNames[c];
	}

	public boolean isCellEditable(int row, int col) {
		return (col == 4);
	}

}
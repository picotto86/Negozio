import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class FinestraRicerca extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6242928832932769892L;
	static TableModel simpleModel;
	static JTable tabella;	
	static JScrollPane scrollPane;
	static TableRowSorter<TableModel> sorter;

	
	
	public FinestraRicerca(String arg0) {
		// TODO Auto-generated constructor stub
		super();
		
		
		setSize(470, 480);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    JPanel panel_1 = new JPanel();
	    getContentPane().add(panel_1, BorderLayout.CENTER);
	    simpleModel = new TabellaRicerca(arg0);
	    tabella = new JTable(simpleModel);
	    
	    tabella.setRowSorter(sorter);
	    
	    //tabella.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 tabella.getColumnModel().getColumn(1).setPreferredWidth(270);
		 
	    scrollPane= new JScrollPane(tabella);
	    
	    panel_1.add(scrollPane);
	    
	    //scrollPane.getViewport().add(tabella);
	     
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    
	    ListSelectionModel cellSelectionModel = tabella.getSelectionModel();
		
		
		 sorter= new TableRowSorter<TableModel>(simpleModel);
	cellSelectionModel
			.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	
	cellSelectionModel
			.addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent e) {
					int[] selectedRow = tabella.getSelectedRows();
					int[] selectedColumns = tabella.getSelectedColumns();
					System.out.println("la linea selezionata e:"+ selectedRow[0]);
					System.out.println("il prodotto  :"+ tabella.getValueAt(selectedRow[0],1) );
					
					String cod=(String) tabella.getValueAt(selectedRow[0],4);
					
					dispose();
					
					Vendita.aggiungi_barcode(cod);
					
					Vendita.refreshTable();
					
					Vendita.label_importo.setText(Float.toString(TabellaVendita.prezzo));
					validate();
					Vendita.textField.requestFocusInWindow();

					
					
				}
			});

	}
	
}
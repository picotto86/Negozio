import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class Vendita extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7958955120941747461L;
	static TableModel simpleModel;
	static JTable tabella;	
	static JScrollPane scrollPane;
	static JButton btnconferma;
	
	static JLabel label_importo;
	static JLabel label_importo_giornata;
	

	
	static JTextField textField;
	private JTextField textField_1;
	
	public static void refreshTable() {
		// TODO Auto-generated method stub
		simpleModel=new TabellaVendita();
		tabella.setModel(simpleModel);
		Vendita.tabella.validate();
		
	}
	
	public static void aggiungi_barcode(String barcode) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		boolean flag=false;
		
		System.out.println("ci sono prima del for quando flag=0");
		System.out.println(simpleModel.getRowCount());
		
		for(int i=0;i< simpleModel.getRowCount();i++){
			
			if(simpleModel.getValueAt(i, 7).toString().equals(barcode)|| simpleModel.getValueAt(i,8).toString().equals(barcode)){
				
				System.out.println("ci sono quando l'elemento Ã¨ giÃ  nella tabella");
				
				if(simpleModel.getValueAt(i, 7).toString().equals(barcode)){
					
					System.out.println("uguale a 7");
					
					simpleModel.setValueAt(new Float((Float)simpleModel.getValueAt(i, 4)+1), i, 4);
	
					System.out.println(simpleModel.getValueAt(i,4));
				
				}else{
					
					Sigaretta s=Database.database.get(simpleModel.getValueAt(i,7));
					
					
					simpleModel.setValueAt(new Float((Float)simpleModel.getValueAt(i, 4)+new Float(s.qtaxstecca)), i, 4);
					
					System.out.println(simpleModel.getValueAt(i,4));
					
				}
				flag=true;

			}
			
		}
		
		if(flag == false){
			
			System.out.println("ci sono quando devo creare un nuovo elemento dell'acquisto");
			
			Sigaretta s,s1;
			
			s1=Database.database.get(barcode);
			
			if(s1==null){
				
				System.out.println("barcode non presente nel database dei pacchetti,proseguo nelle stecche");
				
				int len = Database.database.size();
				
				int i = 0;
				for (Iterator<Sigaretta> it = Database.database.values().iterator(); it
						.hasNext() || i < len; i++) {

					Sigaretta s2 = it.next();
					

					if(s2.barcode_stecca.equals(barcode)){
						
						s=new Sigaretta(s2.tipo,s2.nome,s2.cod,s2.prezzoxchilo,s2.nuovaqta,new Float(s2.qtaxstecca),
								s2.prezzo_confezione,s2.peso,s2.barcode,s2.barcode_stecca,s2.qtaxstecca);
						
						Database.vendita.add( s);
						break;
					}
					
				}
				
			}else{
			
				System.out.println("il nome riferito al barcode ï¿½ "+ s1.nome);
			
				s=new Sigaretta(s1.tipo,s1.nome,s1.cod,s1.prezzoxchilo,
				s1.nuovaqta,new Float(1),s1.prezzo_confezione,s1.peso,s1.barcode,s1.barcode_stecca,s1.qtaxstecca);
				
				System.out.println("Il codice inserito ï¿½ "+s.cod);
				Database.vendita.add( s);
			
			}
			
				
		}
		
	}



	public Vendita(){
		
		super();
		setLayout(new BorderLayout(0, 0));
		
	
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 2,0 ,0));
		
		JLabel lblBarcode = new JLabel("Barcode");
		panel_1.add(lblBarcode);
		
		simpleModel = new TabellaVendita();
		
		textField = new JTextField() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 4017044499346473867L;

			public void addNotify() {
	            super.addNotify();
	            requestFocus();
	        }
	    };
		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
			    
					System.out.println("Ho premuto invio"); 
				    
					String barcode=textField.getText();
					aggiungi_barcode(barcode);
					textField.setText("");
					
					
					refreshTable();
					
					label_importo.setText(Float.toString(TabellaVendita.prezzo));
					validate();
			    
					
				}
			}
			
		});
				
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		panel_1.add(lblDescrizione);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_1.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				    
					System.out.println("Ho premuto invio"); 
				    
					String descr=textField_1.getText();
					aggiungixdescrizione(descr);
					textField_1.setText("");
					Vendita.textField.requestFocusInWindow();

						
				}
			}
		});
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		tabella = new JTable(simpleModel);
		
		
		scrollPane= new JScrollPane(tabella);
		scrollPane.setPreferredSize(new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width-50, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-200));
	   
	       tabella.getColumnModel().getColumn(1).setPreferredWidth(270);

		
		panel.add(scrollPane);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				
						JPanel panel_2 = new JPanel();
						add(panel_2, BorderLayout.SOUTH);
						
						btnconferma = new JButton("Conferma");
						btnconferma.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								int j=0;
								
								for (j=0;j<simpleModel.getRowCount();j++){
									
									Sigaretta s=Database.database.get(simpleModel.getValueAt(j,7));
									s.nuovaqta=s.nuovaqta - (Float)simpleModel.getValueAt(j,6)/s.prezzo_confezione/Float.parseFloat(s.qtaxstecca);
									
								}
								Database.vendita = new ArrayList<Sigaretta>();
								Database.tot_giornata=Database.tot_giornata+TabellaVendita.prezzo;
								TabellaVendita.prezzo=0;
								
								
								label_importo.setText(Float.toString(TabellaVendita.prezzo));
								label_importo_giornata.setText(Float.toString(Database.tot_giornata));

								refreshTable();
								revalidate();
								Vendita.textField.requestFocusInWindow();
								
								
								Magazzino.refreshTable();
								
							}
						});
						
						panel_2.setLayout(new GridLayout(3, 1, 0, 0));
						
						JLabel lblPrezzo = new JLabel("Prezzo");
						JLabel lblIncassoGiornata=new JLabel("Progressivo Giornata");
						panel_2.add(lblIncassoGiornata);
						label_importo_giornata=new JLabel(Float.toString(Database.tot_giornata));
						panel_2.add(label_importo_giornata);
						
						panel_2.add(lblPrezzo);
						
						label_importo = new JLabel(Float.toString(TabellaVendita.prezzo));
						panel_2.add(label_importo);
						
						panel_2.add(btnconferma);
						
						JButton btnscarta = new JButton("Scarta");
						panel_2.add(btnscarta);
		
		btnscarta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Database.vendita = new ArrayList<Sigaretta>();
				TabellaVendita.prezzo=0;
				label_importo.setText(Float.toString(TabellaVendita.prezzo));

				refreshTable();
				revalidate();
				Vendita.textField.requestFocusInWindow();

			}
		});
		
	}

	 void aggiungixdescrizione(String arg0) {
		// TODO Auto-generated method stub

		 FinestraRicerca fin=new FinestraRicerca(arg0);
		 
		 fin.setVisible(true);
	 
		 
	 }
	
}
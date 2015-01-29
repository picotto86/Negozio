import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter.SortKey;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Magazzino extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3804755664029479284L;
	static TableModel simpleModel;
	static JTable tabella;	
	static JScrollPane scrollPane;
	private JTextField textField;
	static TableRowSorter<TableModel> sorter;
	private JButton btnNewButton;
	
	public static void refreshTable() {
		// TODO Auto-generated method stub
		simpleModel = new TabellaMagazzino();
		sorter= new TableRowSorter<TableModel>(simpleModel);
		tabella.revalidate();
	}
	
	
	public Magazzino() {
		
		super();
		
		setLayout(new BorderLayout(0, 0));
		
		simpleModel = new TabellaMagazzino();
		
		tabella = new JTable(simpleModel);
		 sorter= new TableRowSorter<TableModel>(simpleModel);
		 
		 
	    tabella.setRowSorter(sorter);
		 scrollPane= new JScrollPane(tabella);
		 
		 tabella.setAutoCreateRowSorter( false );
	       List<SortKey> sortKeys = new ArrayList<SortKey>();
	       sortKeys.add( new SortKey( 0, SortOrder.ASCENDING ) );
	       sortKeys.add( new SortKey( 1, SortOrder.ASCENDING ) );
	      
	       tabella.getRowSorter().setSortKeys( sortKeys );
	      
	       tabella.getColumnModel().getColumn(1).setPreferredWidth(270);
		
		scrollPane.getViewport().add(tabella);
		 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane,BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setRowHeaderView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAggiungi = new JButton("aggiungi");
		panel.add(btnAggiungi);
		btnAggiungi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FinestraAggiungi fr=new FinestraAggiungi();
				fr.setVisible(true);
				
				fr.addWindowListener(new WindowListener() {
					
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
					 
					simpleModel=new TabellaMagazzino();
					tabella.setModel(simpleModel);
					validate();
					
					}
					
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				
				});
				
			}
		});
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(17);
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
			    
			    filtra(textField.getText());
			   
				}
			}

			private void  filtra (String nome) {
				// TODO Auto-generated method stub
				if (nome.length() == 0) {
			          sorter.setRowFilter(null);
			        } else {
			          sorter.setRowFilter(RowFilter.regexFilter(nome.toUpperCase()));
			        }
			}
			
			
		});
		
		 
		JButton btnCerca = new JButton("cerca");
		panel.add(btnCerca);
		
		btnNewButton = new JButton("Stampa Magazzino");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				
					tabella.print(JTable.PrintMode.FIT_WIDTH); // STAMPO
				
				} catch (PrinterException ex) {
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
					
				}
				
			}
				
			
		});
		add(btnNewButton, BorderLayout.NORTH);
		
		JTextArea text_tot_mag=new JTextArea("Totale Magazzino : "+ Float.toString(TabellaMagazzino.tot_magazzino));
		add(text_tot_mag,BorderLayout.SOUTH);
		
		btnCerca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				filtra(textField.getText());
			}
			private void filtra(String nome) {
				// TODO Auto-generated method stub
				if (nome.length() == 0) {
			          sorter.setRowFilter(null);
			        } else {
			          sorter.setRowFilter(RowFilter.regexFilter(nome.toUpperCase()));
			        }
			}
		});
		
		ListSelectionModel cellSelectionModel = tabella.getSelectionModel();
		cellSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cellSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						int[] selectedRow = tabella.getSelectedRows();
						int[] selectedColumns = tabella.getSelectedColumns();
						System.out.println("la linea selezionata e:"+ selectedRow[0]);

					}
				});
		

		
	}
	
	
}
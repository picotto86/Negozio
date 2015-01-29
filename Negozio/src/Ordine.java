import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import java.awt.GridLayout;
import javax.swing.JTextField;


public class Ordine extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5309867864535348585L;

	 static TableModel simpleModel;
	static JTable tabella;	
	static JScrollPane scrollPane;
	static TableRowSorter<TableModel> sorter;
	private JTextField textField_consumo;
	private JButton btnApriord;
	
	public static void refreshTable() {
		// TODO Auto-generated method stub
		simpleModel = new TabellaOrdine();
		sorter= new TableRowSorter<TableModel>(simpleModel);
		tabella.revalidate();
	}
	

	
	public Ordine(){
		super();
		setLayout(new BorderLayout(0, 0));
		
		
		simpleModel = new TabellaOrdine();
		
		tabella = new JTable(simpleModel);
		sorter= new TableRowSorter<TableModel>(simpleModel);
		 
		 
	    tabella.setRowSorter(sorter);
		 scrollPane= new JScrollPane(tabella);
		 
		 tabella.setAutoCreateRowSorter( true );
	       List<SortKey> sortKeys = new ArrayList<SortKey>();
	       sortKeys.add( new SortKey( 0, SortOrder.ASCENDING ) );
	       sortKeys.add( new SortKey( 1, SortOrder.ASCENDING ) );
	       tabella.getRowSorter().setSortKeys( sortKeys );
		
		//scrollPane.getViewport().add(tabella);
		 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane,BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Crea file Excel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WorkbookSettings wbSetting = new WorkbookSettings();     
				wbSetting.setUseTemporaryFileDuringWrite(true);     
				WritableWorkbook workbook=null;
				
					try {
						workbook = Workbook.createWorkbook(new File("output.xls"), wbSetting);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				WritableSheet sheet = workbook.createSheet("Foglio1", 0);
				int j=1;
				for(int i = 0;i<simpleModel.getRowCount();i++){
					
					if(((Float) simpleModel.getValueAt(i,7))!=0){
						
						System.out.println((String)simpleModel.getValueAt(i,2)+" "+simpleModel.getValueAt(i,3).toString());
						
						jxl.write.Label label1 = new jxl.write.Label(0, j,  simpleModel.getValueAt(i,2).toString());
						
						try {
							sheet.addCell(label1);
						} catch (RowsExceededException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (WriteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						jxl.write.Number number = new jxl.write.Number(1, j, new Double(simpleModel.getValueAt(i,7).toString())
						*new Double(simpleModel.getValueAt(i,8).toString()));
					
						j++;
						
						try {
							sheet.addCell(number);
						} catch (RowsExceededException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (WriteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
					
						
					
				}
				
				try {
					workbook.write();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					workbook.close();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
			
		});
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		panel_1.add(btnNewButton);
		
		btnApriord = new JButton("Apri File Ordine");
		
		btnApriord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				JFileChooser fileChooser = new JFileChooser();
				  int n = fileChooser.showOpenDialog(Ordine.this);
				  
				  if(n==JFileChooser.APPROVE_OPTION){
					  File f=fileChooser.getSelectedFile();
					  System.out.println(f.getPath());
					  
					  Workbook workbook = null;
						try {
							workbook = Workbook.getWorkbook(f);
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
				         
				         int i;
				         for(int j = 1; j < numeroRighe; j++){
				        	 
				        	
				        	 
				        	 LabelCell lc = (LabelCell) sheet.getCell(0, j); 
				             String codC = lc.getString(); 
				                
				             for (i=0; i<simpleModel.getRowCount();i++){

				            	 
				            	 if(simpleModel.getValueAt(i,2).toString().compareTo(codC)==0){
				            		 
				            		 float peso=Float.parseFloat(Magazzino.simpleModel.getValueAt(i,7).toString());
				                	
				        				NumberCell nc = (NumberCell) sheet.getCell(1, j); 
					                
				        				double qtaC = nc.getValue(); 
				        				
				        				simpleModel.setValueAt(Double.toString(qtaC/peso),i,7);
				        				
				        			}
				                }
				                
				         }
				         
					  
					  
				  }
				
				
			}
		});
		
		panel_1.add(btnApriord);
		
		
		textField_consumo = new JTextField();
		textField_consumo.setEditable(false);
		panel_1.add(textField_consumo);
		textField_consumo.setColumns(10);
		textField_consumo.setText(String.valueOf(TabellaOrdine.consumo));
		
		JButton btn_azzera_cons = new JButton("Consolida Ordine");
		
		btn_azzera_cons.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(int i=0;i<simpleModel.getRowCount();i++){
					
					//campo 3=4+5
					//campo 4=0
					//campo5=campo 3
					
					simpleModel.setValueAt(new Float( (Float) simpleModel.getValueAt(i,4))+
					new Float( (Float) simpleModel.getValueAt(i,5)),i, 3);
					simpleModel.setValueAt(0,i,4);
					simpleModel.setValueAt(simpleModel.getValueAt(i,3),i,5);
					
					
				}
				
				Gui.mag=new Magazzino();
				Gui.ord=new Ordine();
				textField_consumo.setText(String.valueOf(TabellaOrdine.consumo));
				refreshTable();
				File f = new File("output.xls");
				f.delete();
				
				WorkbookSettings wbSetting = new WorkbookSettings();     
				wbSetting.setUseTemporaryFileDuringWrite(true);     
				WritableWorkbook workbook=null;
				
					try {
						workbook = Workbook.createWorkbook(new File("output.xls"), wbSetting);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				WritableSheet sheet = workbook.createSheet("Foglio1", 0);
				
				try {
					workbook.write();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					workbook.close();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
				
			}
		});
		panel_1.add(btn_azzera_cons);
		
		

		
	}

}
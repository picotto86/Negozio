import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class FinestraAggiungi extends JDialog implements ActionListener{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5993557541889520769L;
	String[] tipi_sig= {"Sigarette", "Trinciati per sigaretta","Trinciati per pipa","Sigari","Sigaretti"};	
	 JTextArea codice=new JTextArea("Codice");
	 JTextArea tipo=new JTextArea("Tipo");
	 JTextArea nome=new JTextArea("Nome");
	 JTextArea vecchiaqta=new JTextArea("Vecchia Quantita");
	 JTextArea nuovaqta=new JTextArea("Nuova Quantita");
	 JTextArea prezzoxchilo=new JTextArea("Prezzo/Kg");
	 JTextArea peso=new JTextArea("Peso");
	 JTextArea prezzo_confezione=new JTextArea("Prezzo confezione");
	 JTextArea barcode = new JTextArea("Barcode Pacchetto");
	 JTextArea barcode_stecca = new JTextArea("Barcode Stecca");
	 JTextArea qtaxstecca = new JTextArea("Pacchetti per Stecca");
	 JTextField campo_codice=new JTextField();
	 JComboBox campo_tipo=new JComboBox(tipi_sig);
	 JTextField campo_nome=new JTextField();
	 JTextField campo_vecchiaqta=new JTextField();
	 JTextField campo_nuovaqta=new JTextField();
	 JTextField campo_prezzoxchilo=new JTextField();
	 JTextField campo_peso=new JTextField();
	 JTextField campo_prezzoconfezione=new JTextField();
	 JTextField campo_barcode = new JTextField();
	 JTextField campo_barcode_stecca = new JTextField();
	 JTextField campo_qtaxstecca = new JTextField();
	
	 public FinestraAggiungi(){

		super();
		
		setSize(400, 250);
		this.setModal(true);
		getContentPane().setLayout(new GridLayout(0, 2));
		campo_barcode.setColumns(10);
		nome.setEditable(false);
		vecchiaqta.setEditable(false);
		nuovaqta.setEditable(false);
		prezzoxchilo.setEditable(false);
		tipo.setEditable(false);
		prezzo_confezione.setEditable(false);
		barcode.setEditable(false);
		barcode_stecca.setEditable(false);
		codice.setEditable(false);
		
		getContentPane().add(tipo);
		getContentPane().add(campo_tipo);
		getContentPane().add(nome);
		getContentPane().add(campo_nome);
		getContentPane().add(codice);
		getContentPane().add(campo_codice);
		getContentPane().add(prezzoxchilo);
		getContentPane().add(campo_prezzoxchilo);
		getContentPane().add(vecchiaqta);
		getContentPane().add(campo_vecchiaqta);
		getContentPane().add(nuovaqta);
		getContentPane().add(campo_nuovaqta);
		getContentPane().add(prezzo_confezione);
		getContentPane().add(campo_prezzoconfezione);
		getContentPane().add(peso);
		getContentPane().add(campo_peso);
		getContentPane().add(barcode);
		getContentPane().add(campo_barcode);
		getContentPane().add(barcode_stecca);
		getContentPane().add(campo_barcode_stecca);
		getContentPane().add(qtaxstecca);
		getContentPane().add(campo_qtaxstecca);
		
		
		codice.setColumns(2);
		
		
		barcode.setText("Barcode");
		barcode_stecca.setText("Barcode Stecca");

		
		
		JButton aggiungi=new JButton("Aggiungi");
		getContentPane().add(aggiungi);
		
		aggiungi.addActionListener(this);
		
	}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Sigaretta s=new Sigaretta(
				campo_tipo.getSelectedItem().toString(),
				campo_nome.getText(),
				campo_codice.getText(),
				Float.parseFloat(campo_prezzoxchilo.getText()),
				Float.parseFloat(campo_vecchiaqta.getText()),
				Float.parseFloat(campo_nuovaqta.getText()),
				Float.parseFloat(campo_prezzoconfezione.getText()),
				Float.parseFloat(campo_peso.getText()),
				campo_barcode.getText(),
				campo_barcode_stecca.getText(),
				(campo_qtaxstecca.getText()));
				Database.database.put(s.barcode, s);
				this.dispose();
				Magazzino.refreshTable();
	
	}
	
	
}
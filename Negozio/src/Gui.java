import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.io.IOException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;

import java.awt.Toolkit;


public class Gui  {

	private static final Object BENVENUTO = "Benvenuto";
	static final Object MAGAZZINO = "Magazzino";
	private static final Object VENDITA = "Vendita";
	private static final Object STATISTICHE = "Statistiche";
	private static final Object ORDINE = "Ordine";
	static JFrame frame;
    public static JPanel panel,panel_1;
    public static PannelloNav nav_menu;
    static Magazzino mag;
    static Ordine ord;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database db=new Database();
					
					try {

						db.caricaDb();

					} catch (IOException e1) {

						// TODO Auto-generated catch block

						e1.printStackTrace();

					}

					Gui window = new Gui();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("tabacchi.jpg"));
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
				
					Vendita.btnconferma.doClick();
					Database.salvaDb();
					
			
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		int height=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int width=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		
		frame.setSize(width,height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MioMenuBar menubar=new MioMenuBar();
		
		nav_menu = new PannelloNav();
		
		panel_1 = new JPanel();
		panel_1.setLayout(new CardLayout(0, 0));
		
		mag=new Magazzino();
		Benvenuto benv=new Benvenuto();
		Vendita vend=new Vendita();
		ord=new Ordine();
		Statistiche stat=new Statistiche();
		
		panel_1.add(benv,BENVENUTO);
		panel_1.add(mag,MAGAZZINO);
		panel_1.add(vend,VENDITA);
		panel_1.add(ord,ORDINE);
		panel_1.add(stat,STATISTICHE);
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		frame.getContentPane().add(menubar);
		frame.getContentPane().add(nav_menu);
		frame.getContentPane().add(panel_1);
		impostaPagina(1);
	}
	
	public static void impostaFocus(){
		
		Vendita.textField.requestFocus();
		
	}
	
	public static void impostaPagina(int num){
		
		if (num == 1){
		
			CardLayout cl=(CardLayout)(panel_1.getLayout());
			cl.show(panel_1,(String) BENVENUTO);
			
		}
		if (num == 2){
			
			CardLayout cl=(CardLayout)(panel_1.getLayout());
			
			cl.show(panel_1,(String) MAGAZZINO);
			
		}
		if (num == 3){
			
			CardLayout cl=(CardLayout)(panel_1.getLayout());
			cl.show(panel_1,(String) VENDITA);
			Vendita.textField.requestFocus();
		}
		if (num == 4){
			
			CardLayout cl=(CardLayout)(panel_1.getLayout());
			cl.show(panel_1,(String) ORDINE);
			
		}
		if (num == 5){
			
			CardLayout cl=(CardLayout)(panel_1.getLayout());
			cl.show(panel_1,(String) STATISTICHE);
			
		}
		
	}

}
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MioMenuBar extends JPanel{
	
	public MioMenuBar(){
	super();
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu mnNewMenu = new JMenu("File");
	menuBar.add(mnNewMenu);
	
	JMenuItem mntmNewMenuItem = new JMenuItem("Chiudi");
	mnNewMenu.add(mntmNewMenuItem);
	
	mntmNewMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			Gui.frame.dispose();
			
		}
	});
	
	JMenuItem mntmNewMenuItem_1 = new JMenuItem("Salva");
	mnNewMenu.add(mntmNewMenuItem_1);
	
	JMenu mnNewMenu_1 = new JMenu("Archivi");
	menuBar.add(mnNewMenu_1);
	
	JMenuItem mntmNewMenuItem_2 = new JMenuItem("Fornitori");
	mnNewMenu_1.add(mntmNewMenuItem_2);
	
	JMenuItem mntmNewMenuItem_3 = new JMenuItem("Magazzino");
	
	mnNewMenu_1.add(mntmNewMenuItem_3);
	mntmNewMenuItem_3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CardLayout cl=(CardLayout)(Gui.panel_1.getLayout());
			cl.show(Gui.panel_1,(String) Gui.MAGAZZINO);
		}
	});
	
	JMenu mnNewMenu_2= new JMenu("Aiuto");
	menuBar.add(mnNewMenu_2);
	add(menuBar);
	
	}

}
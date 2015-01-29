import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class PannelloNav extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4234031465835555956L;

	public PannelloNav(){
		
		super();
		 JButton home=new JButton("Home");
		 this.add(home);
		 home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Gui.impostaPagina(1);
			}
		});
		 
		 JButton mag=new JButton("Magazzino");
		 this.add(mag);
		 mag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Gui.impostaPagina(2);

			}
		});
		 
		 JButton vend=new JButton("Vendita");
		 this.add(vend);
		 vend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Gui.impostaPagina(3);

			}
		});
		 JButton ord=new JButton("Ordine");
		 this.add(ord);
		 ord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Gui.impostaPagina(4);

			}
		});
		
		 JButton stat=new JButton("Statistiche");
		 this.add(stat);
		 stat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Gui.impostaPagina(5);

			}
		});
		 
		
	}

}
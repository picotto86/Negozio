import java.util.Iterator;
import javax.swing.table.AbstractTableModel;

class TabellaMagazzino extends AbstractTableModel {

        private static final long serialVersionUID = 1880289960097124552L;
        String[] columnNames = { "Tipo", "Nome", "Codice",
                        "Prezzo per chilo", "Vecchia qta","Nuova qta","Prezzo confezione","Peso","Barcode","Barcode Stecca","Pacchetti per Stecca" };
        Object[][] rowData;
        int i,len;
        
        static float tot_magazzino;

        
        public TabellaMagazzino() {
                
                len=Database.database.size();
                
                tot_magazzino=0;

                
                rowData = new Object[len][11];
                i = 0;
                for (Iterator<Sigaretta> it = Database.database.values().iterator(); it
                                .hasNext() || i < len; i++) {

                        Sigaretta s = it.next();

                        rowData[i][0] = s.tipo;
                        rowData[i][1] = s.nome;
                        rowData[i][2] = s.cod;
                        rowData[i][3] = new Float(s.prezzoxchilo);
                        rowData[i][4] = new Float(s.vecchiaqta);
                        rowData[i][5] = new Float(s.nuovaqta);
                        rowData[i][6] = new Float(s.prezzo_confezione);
                        rowData[i][7] = new Float(s.peso);
                        rowData[i][8] = s.barcode;
                        rowData[i][9] = s.barcode_stecca;
                        rowData[i][10]=new Integer(s.qtaxstecca);

                        tot_magazzino=tot_magazzino+s.nuovaqta*s.prezzoxchilo*s.peso;

                        
                }

        }

        public int getRowCount() {
                return len;
        }

        public int getColumnCount() {
                return columnNames.length;
        }

        public Object getValueAt(int r, int c) {
                
                return rowData[r][c];
                
        }

        public void setValueAt(Object value, int row, int col) {
                Sigaretta s;
                switch (col) {
                case 0:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.tipo=(String) value;
                        rowData[row][0]=(String)value;
                        System.out.println("nuova categoria: "+ s.tipo);
                        
                        break;
                
                case 1:
                
                        s=Database.database.get(getValueAt(row, 8));
                        s.nome=(String) value;
                        rowData[row][1]=(String)value;
                        System.out.println("nuovo nome: "+ s.nome);
                        
                        break;
                
                case 2:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.cod= new String(value.toString());
                        rowData[row][2]=new String( value.toString());
                        System.out.println("nuovo codice: "+ s.cod);
                        
                        break;
                        
                case 3:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.prezzoxchilo= new Float(value.toString());
                        rowData[row][3]=new Float( value.toString());
                        System.out.println("nuovo prezzo al chilo: "+ new Float(s.prezzoxchilo).toString());
                        
                        break;
                        
                case 4:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.vecchiaqta=new Float(value.toString());
                        rowData[row][4]=new Float(value.toString());
                        System.out.println("nuovo vecchia qta: "+ new Float(s.vecchiaqta).toString());

                        break;
                        
                case 5:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.nuovaqta=new Float(value.toString());
                        rowData[row][5]=new Float(value.toString());
                        System.out.println("nuovo nuova qta: "+ new Float(s.nuovaqta).toString());

                        break;
                        
                case 6:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.prezzo_confezione=new Float(value.toString());
                        rowData[row][6]=new Float(value.toString());
                        System.out.println("nuovo prezzo confezione: "+ new Float(s.prezzo_confezione).toString());

                        break;
                        
                case 7:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.peso=new Float(value.toString());
                        rowData[row][7]=new Float(value.toString());
                        System.out.println("nuovo peso: "+ new Float(s.peso).toString());

                        break;
                        
                
                case 8:
                        
                        s=Database.database.get(getValueAt(row, 8));
                        s.barcode= new String(value.toString());
                        rowData[row][8]=new String( value.toString());
                        System.out.println("nuovo barcode: "+ s.barcode.toString());

                        break;
                        
                case 9:
                        
                        s=Database.database.get(getValueAt(row,8));
                        s.barcode_stecca=new String(value.toString());
                        rowData[row][9]=new String(value.toString());
                        System.out.println("nuovo barcode stecca:"+ s.barcode_stecca.toString());
                        break;
                        
                case 10:
                        
                        s=Database.database.get(getValueAt(row,8));
                        s.qtaxstecca=(String) new String(value.toString());
                        rowData[row][10]=new String(value.toString());
                        System.out.println("nuova qtaxstecca:"+ s.qtaxstecca);
                        
                        
                        break;
                        
                        
                default:
                        break;
                }
        }

        public String getColumnName(int c) {
                return columnNames[c];
        }

        public boolean isCellEditable(int row, int col) {
                return (col != 0);
        }

}

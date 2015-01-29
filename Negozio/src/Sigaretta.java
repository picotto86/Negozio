public class Sigaretta {

	public String nome;
	public String cod;
	public String tipo;
	public String confezione;
	public float vecchiaqta,nuovaqta;
	public float prezzoxchilo;
	public float prezzo_confezione,peso;
	public String barcode;
	public String barcode_stecca;
	public String qtaxstecca;

	public Sigaretta(String tipo,String nome ,String cod,float prezzoxchilo,float vecchiaqta,float nuovaqta,float prezzo_confezione,float peso,String barcode,String barcode_stecca,String qtaxstecca) {

		this.tipo=tipo;
		this.nome = nome;
		this.cod = cod;
		this.prezzoxchilo = prezzoxchilo;
		this.vecchiaqta = vecchiaqta;
		this.nuovaqta=nuovaqta;
		this.prezzo_confezione=prezzo_confezione;
		this.peso=peso;
		this.barcode=barcode;
		this.barcode_stecca=barcode_stecca;
		this.qtaxstecca=qtaxstecca;
		
	}

}
package classi;

import java.util.Date;

public class Storico {
	private int ID;
	private Date dataora;
	private String operazione;
	private long ISBN;
	private int ID_Utente;
	private int ID_Recensione;
	
	public Storico(long isbn, int idutente, String operazione) {
		ISBN=isbn;
		ID_Utente=idutente;
		this.operazione=operazione;
	}
	
	public String StoricoToString() {
		String oper="inserito";
		if (operazione.equals("Modifica")) 
		{
			oper="modificato";
		}
		return "L'utente con ID "+this.ID_Utente+" ha "+oper+ " la pubblicazione con ISBN "+this.ISBN;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

}

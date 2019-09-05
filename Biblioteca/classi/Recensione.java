package classi;

import java.util.Date;

public class Recensione {
	
	private int ID;
	private String Testo;
	private boolean Pubblicata;
	private Date DataOra;
	private long ISBN;
	private int ID_Utente;
	
		
	public Recensione(int iD, String testo, boolean pubblicata, Date dataOra, long iSBN, int iD_Utente) {
		ID = iD;
		Testo = testo;
		Pubblicata = pubblicata;
		DataOra = dataOra;
		ISBN = iSBN;
		ID_Utente = iD_Utente;
	}

	public int getID() {
		return ID;
	}
	
	public String getTesto() {
		return Testo;
	}
	public boolean isPubblicata() {
		return Pubblicata;
	}
	public Date getDataOra() {
		return DataOra;
	}
	public long getISBN() {
		return ISBN;
	}
	public int getID_Utente() {
		return ID_Utente;
	}
	
	

}

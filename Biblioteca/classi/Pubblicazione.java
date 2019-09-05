package classi;

import java.util.Date;

public class Pubblicazione  implements Comparable<Pubblicazione>{
	private long ISBN;
	private String titolo;
	private String editore;
	private String descrizione;
	private String lingua;
	private int numeropagine;
	private Date datapubblicazione;
	private String indice;
	private String keywords;
	private boolean download;
	private String autori;
	private int numerolike;
	private Date ultimaristampa;
	private int ID_Utente;
	
	
	public Pubblicazione(long iSBN, String titolo, String editore, String descrizione, String lingua, int numeropagine,
			Date datapubblicazione, String indice, String keywords, boolean download, String autori) {
		ISBN = iSBN;
		this.titolo = titolo;
		this.editore = editore;
		this.descrizione = descrizione;
		this.lingua = lingua;
		this.numeropagine = numeropagine;
		this.datapubblicazione = datapubblicazione;
		this.indice = indice;
		this.keywords = keywords;
		this.download = download;
		this.autori = autori;
	}


	public Pubblicazione(long iSBN, String titolo, String editore, Date datapubblicazione, boolean download,
			String autori, int numerolike) {
		ISBN = iSBN;
		this.titolo = titolo;
		this.editore = editore;
		this.datapubblicazione = datapubblicazione;
		this.download = download;
		this.autori = autori;
		this.numerolike = numerolike;
		this.ultimaristampa=null;
	}
	
	public Pubblicazione(long iSBN, String titolo, String editore, Date datapubblicazione, boolean download,
			String autori) {
		ISBN = iSBN;
		this.titolo = titolo;
		this.editore = editore;
		this.datapubblicazione = datapubblicazione;
		this.download = download;
		this.autori = autori;
		this.numerolike=-1;
		this.ultimaristampa= null;
	}
	
	public Pubblicazione(long iSBN, String titolo, String editore, Date datapubblicazione, boolean download,
			String autori, int numerolike ,int idutente) {
		ISBN = iSBN;
		this.titolo = titolo;
		this.editore = editore;
		this.datapubblicazione = datapubblicazione;
		this.download = download;
		this.autori = autori;
		this.numerolike=-1;
		this.ultimaristampa= null;
		this.ID_Utente=idutente;
	}



	public Pubblicazione(long iSBN, String titolo, String editore, Date datapubblicazione, boolean download,
			String autori, Date ultimaristampa) {
		ISBN = iSBN;
		this.titolo = titolo;
		this.editore = editore;
		this.datapubblicazione = datapubblicazione;
		this.download = download;
		this.autori = autori;
		this.numerolike=-1;
		this.ultimaristampa = ultimaristampa;
	}


	public long getISBN() {
		return ISBN;
	}


	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getEditore() {
		return editore;
	}


	public void setEditore(String editore) {
		this.editore = editore;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getLingua() {
		return lingua;
	}


	public void setLingua(String lingua) {
		this.lingua = lingua;
	}


	public int getNumeropagine() {
		return numeropagine;
	}


	public void setNumeropagine(int numeropagine) {
		this.numeropagine = numeropagine;
	}


	public Date getDatapubblicazione() {
		return datapubblicazione;
	}


	public void setDatapubblicazione(Date datapubblicazione) {
		this.datapubblicazione = datapubblicazione;
	}


	public String getIndice() {
		return indice;
	}


	public void setIndice(String indice) {
		this.indice = indice;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public boolean isDownload() {
		return download;
	}


	public void setDownload(boolean download) {
		this.download = download;
	}


	public String getAutori() {
		return autori;
	}


	public void setAutori(String autori) {
		this.autori = autori;
	}


	public int getNumerolike() {
		return numerolike;
	}


	public void setNumerolike(int numerolike) {
		this.numerolike = numerolike;
	}


	public Date getUltimaristampa() {
		return ultimaristampa;
	}


	public void setUltimaristampa(Date ultimaristampa) {
		this.ultimaristampa = ultimaristampa;
	}

	public int compareTo(Pubblicazione a) {
		return this.titolo.compareTo(a.titolo);
		}


}

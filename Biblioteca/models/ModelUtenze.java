package models;

import java.util.List;

import classi.Utente;

public class ModelUtenze{
	
	private List<Utente> listautenti;

	public ModelUtenze(List<Utente> listautenti) {
		this.listautenti=listautenti;
	}

	public List<Utente> getListautenti() {
		return listautenti;
	}

	public void setListautenti(List<Utente> listautenti) {
		this.listautenti = listautenti;
	}


}

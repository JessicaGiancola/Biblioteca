package models;



import java.util.List;

import classi.Recensione;

public class ModelRecensioni {

	private List<Recensione> listar;
	
	public ModelRecensioni(List<Recensione> lista)
	{this.listar =lista;}

	public List<Recensione> getListar() {
		return listar;
	}

	public void setListar(List<Recensione> listar) {
		this.listar = listar;
	}
	
	
}

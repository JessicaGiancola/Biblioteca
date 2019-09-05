package models;

import java.util.List;

import classi.Pubblicazione;

public class ModelPubblicazioni {
	private List<Pubblicazione> catalogo;

	public ModelPubblicazioni(List<Pubblicazione> catalogo) {
		this.catalogo = catalogo;
	}

	public List<Pubblicazione> getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(List<Pubblicazione> catalogo) {
		this.catalogo = catalogo;
	}
	

}

package models;

import java.util.List;

import classi.Storico;

public class ModelStorico {
	private List<Storico> storici;

	public ModelStorico (List<Storico> s)
	{
		this.storici=s;
	}

	public List<Storico> getStorici() {
		return storici;
	}

	public void setStorici(List<Storico> storici) {
		this.storici = storici;
	}

}

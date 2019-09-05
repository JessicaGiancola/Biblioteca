package classi;

import java.util.Date;

public class Utente 
{
	private int ID;
	private String nome;
	private String cognome;
	private String email;
	private Date datanascita;
	private String password;
	private int tipo;
	
	public Utente(int ID,String nome, String cognome,String email, Date datanascita, String password, int tipo)
	{
		this.ID=ID;
		this.nome=nome;
		this.cognome=cognome;
		this.datanascita=datanascita;
		this.email=email;
		this.password=password;
		this.tipo=tipo;
		
	}
	
	public Utente(int ID, String nome, String cognome,int tipo) {
		this.ID=ID;
		this.nome=nome;
		this.cognome=cognome;
		this.tipo=tipo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDatanascita() {
		return datanascita;
	}
	public void setDatanascita(Date datanascita) {
		this.datanascita = datanascita;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getID() {
		return ID;
	}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", datanascita=" + datanascita
				+ ", tipo=" + tipo + "]";
	}
	
	

}

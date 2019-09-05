package controllers;

import database.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import models.*;
import views.*;
import classi.*;

public class ControllerMenu extends Controller{
	
	private ViewMenu vwmenu;
	
	public ControllerMenu(ViewMenu vwmenu) {
		super(vwmenu);
		this.vwmenu=vwmenu;
	}
	
	public void InitController() 
	{
		super.InitController();
		
		if(ControllerLogin.user.getTipo()==0) 
		{
			vwmenu.getBtnUtenti().setVisible(false);
			vwmenu.getBtnStorico().setVisible(false);
		}
		vwmenu.getBtnUtenti().addActionListener(a -> utenze());
		vwmenu.getBtnPubblicazioni().addActionListener(b -> pubblicazioni());
		vwmenu.getBtnRecensioni().addActionListener(c -> recensioni());
		vwmenu.getBtnStorico().addActionListener(d -> storico());
		
	}
	
	private void utenze() {

		List<Utente> listautenti = listautenzebase();
		
		ViewUtenze vwutenze = new ViewUtenze();
		ModelUtenze mdl = new ModelUtenze(listautenti);
		ControllerUtenze cu = new ControllerUtenze(mdl,vwutenze);
		cu.InitController();
	}
	
	public static List<Utente> listautenzebase()
	{
		List<Utente> listautenti = new ArrayList<Utente>();
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	   try 
	   {
		
		 Statement stmt = connessione.createStatement();
		 ResultSet rs = stmt.executeQuery("select ID,Nome,Cognome,Tipo from utenti");
		 
	   while (rs.next ())
	   {
		  
			 //recupero valori dal db e creo istanza utente
		       int idutente = rs.getInt("ID");
		       String nomeutente = rs.getString ("Nome");
		       String cognomeutente = rs.getString ("Cognome");
		       int tipo = rs.getInt ("Tipo");
		       
		       //creazione utente
			   Utente user=new Utente(idutente,nomeutente, cognomeutente, tipo);
			   listautenti.add(user);				   
	   }
	   rs.close();
	   stmt.close ();
	   conn.closeConnection();
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
		
		return listautenti;
		
	}
	
	public static List<Pubblicazione> pubblicazionebase()
	{
		List<Pubblicazione> catalogo= new ArrayList();
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	  
	   try 
	   {
		
		 Statement stmt = connessione.createStatement();
		 ResultSet rs = stmt.executeQuery("SELECT pubblicazione.ISBN, Titolo, Editore, DataPubblicazione,Autori,Dowload,NumeroLike FROM pubblicazione LEFT JOIN (SELECT ISBN,count('ID_Utente') as NumeroLike FROM mipiace GROUP BY ISBN) as piace ON pubblicazione.ISBN=piace.ISBN");
		 
	   while (rs.next ())
	   {
		  
			 //recupero valori dal db e creo istanza pubblicazione
	       	   long ISBN=rs.getLong("ISBN");
		       String titolo = rs.getString ("Titolo");
		       String editore = rs.getString ("Editore");
		       Date datapubblicazione=rs.getDate("DataPubblicazione");
		       String autori = rs.getString ("Autori");
		       boolean download=rs.getBoolean("Dowload");
		       int numerolike = rs.getInt("NumeroLike");
		       
		       //creazione pubblicazione
		       Pubblicazione libro=new Pubblicazione(ISBN,titolo,editore,datapubblicazione,download,autori,numerolike);
			   catalogo.add(libro);				   
	   }
	   rs.close();
	   stmt.close ();
	   conn.closeConnection();
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
		
		
		
		return catalogo;
		
	}
	
	private void pubblicazioni() 
	{
		List<Pubblicazione> catalogo= pubblicazionebase();
		
		ModelPubblicazioni mdp = new ModelPubblicazioni(catalogo);
		ViewPubblicazioni vwp = new ViewPubblicazioni();
		ControllerPubblicazioni cp = new ControllerPubblicazioni(mdp,vwp);
		cp.InitController();
	}
	
	public static List<Recensione> recensionibase()
	{
		List<Recensione> listarecensioni= new ArrayList();
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	  
	   try 
	   {
		
		 Statement stmt = connessione.createStatement();
		 ResultSet rs = stmt.executeQuery("select ID,ID_Utente,Testo,ISBN,DataOra, Pubblicata from recensioni");
		 
	   while (rs.next ())
	   {
		  
			 //recupero valori dal db e creo istanza recensioni
		       int idrecensione = rs.getInt("ID");
		       int idutente = rs.getInt("ID_Utente");
		       String testo = rs.getString ("Testo");
		       long ISBN=rs.getLong("ISBN");
		       Date dataora=rs.getDate("DataOra");
		       boolean pubblicata=rs.getBoolean("Pubblicata");
		       
		       //creazione recensioni
		       Recensione recensione=new Recensione(idrecensione,testo, pubblicata, dataora,ISBN,idutente);
			   listarecensioni.add(recensione);				   
	   }
	   rs.close();
	   stmt.close ();
	   conn.closeConnection();
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
		
		
		
		return listarecensioni;
	}
	
	private void recensioni() {
		
		List<Recensione> listarecensioni = recensionibase();
		
		ViewRecensioni vwrecensioni = new ViewRecensioni();
		ModelRecensioni mdr = new ModelRecensioni(listarecensioni);
		ControllerRecensioni cr = new ControllerRecensioni(mdr,vwrecensioni);
		cr.InitController();	
		
	}
	
	public static List<Storico> storicobase()
	{
		List<Storico> listastorico = new ArrayList();
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	   try 
	   {
		
		 Statement stmt = connessione.createStatement();
		 ResultSet rs = stmt.executeQuery("select Operazione,ID_Utente,ISBN from storico");
		 
	   while (rs.next ())
	   {	  
			 //recupero valori dal db e creo istanza storico
		       int idutente = rs.getInt("ID_Utente");
		       long ISBN = rs.getLong ("ISBN");
		       String operazione = rs.getString ("Operazione");
		       
		       //creazione storico
			   Storico striga=new Storico(ISBN,idutente, operazione);
			   listastorico.add(striga);				   
	   }
	   rs.close();
	   stmt.close ();
	   conn.closeConnection();
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
		
		return listastorico;
		
	}
	
	private void storico() {
		
		List<Storico> listastorico = storicobase();
		
		ViewStorico vwstorico = new ViewStorico();
		ModelStorico mds = new ModelStorico(listastorico);
		ControllerStorico cs = new ControllerStorico(mds,vwstorico);
		cs.InitController();
		
	}
	

}

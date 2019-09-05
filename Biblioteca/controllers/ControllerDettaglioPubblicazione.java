package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import classi.Pubblicazione;
import database.Connect;
import views.ViewDettaglioPubblicazione;

public class ControllerDettaglioPubblicazione extends Controller{
	
	private ViewDettaglioPubblicazione vwdpubb;
	private long ISBN;
	
	public ControllerDettaglioPubblicazione(ViewDettaglioPubblicazione vwdpubb, long iSBN) {
		super(vwdpubb);
		this.vwdpubb = vwdpubb;
		ISBN = iSBN;
	}
	
	public void InitController() 
	{
		super.InitController();
		Display(getLibro());
	}
	
	private Pubblicazione getLibro()
	{
		Pubblicazione libro=null;
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	   PreparedStatement s;
	   try 
	   {
		   
	   
	   s = connessione.prepareStatement ("select * from pubblicazione where ISBN= ?");
	   s.setLong(1, this.ISBN);
	   ResultSet rs = s.executeQuery();
	   
	   
	   while (rs.next ())
	   {

	       String titolo = rs.getString ("Titolo");
	       String editore = rs.getString ("Editore");
	       String descrizione = rs.getString ("Descrizione");
	       String lingua = rs.getString ("Lingua");
	       String indice = rs.getString ("Indice");
	       String keywords = rs.getString ("Keywords");
	       String autori = rs.getString ("Autori");
	       
	       Date datapubblicazione = rs.getDate("DataPubblicazione");
	       int numeropagine = rs.getInt("NumeroPagine");
	       boolean download = rs.getBoolean("Dowload");
	       
	       
	       libro=new Pubblicazione(ISBN,titolo, editore,descrizione, lingua, numeropagine, datapubblicazione,indice,keywords,download,autori);   

	   }
	   rs.close();
	   s.close ();
	   conn.closeConnection();
	   
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
		
		return libro;
	}
	
	private void Display(Pubblicazione libro)
	{
		vwdpubb.getLblNewLabel().setText("ISBN: "+this.ISBN);
		vwdpubb.getLblNewLabel_1().setText("Titolo: "+libro.getTitolo());
		vwdpubb.getLblNewLabel_2().setText("Editore: "+libro.getEditore());
		vwdpubb.getLblNewLabel_3().setText("Descrizione: "+libro.getDescrizione());
		vwdpubb.getLblNewLabel_4().setText("Lingua: "+libro.getLingua());
		vwdpubb.getLblNewLabel_5().setText("Numero Pagine: "+libro.getNumeropagine());

		vwdpubb.getLabel().setText("Data Pubblicazione: "+libro.getDatapubblicazione());
		vwdpubb.getLabel_2().setText("Indice: "+libro.getIndice());
		vwdpubb.getLabel_3().setText("Keywords: "+libro.getKeywords());
		
		String donwload = "Non Disponibile";
		
		if(libro.isDownload())
		{
			donwload = "Disponibile";
		}
		
		vwdpubb.getLabel_4().setText("Download: "+donwload);
		vwdpubb.getLabel_5().setText("Autori: "+libro.getAutori());

	}


}

package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;


import database.Connect;
import views.ViewInserisciRecensione;

public class ControllerInserisciRecensione extends Controller{
	
	private ViewInserisciRecensione vwinsrec;

	public ControllerInserisciRecensione(ViewInserisciRecensione vwinsrec) {
		super(vwinsrec);
		this.vwinsrec=vwinsrec;
	}
	
	public void InitController() 
	{
		super.InitController();
		popolacombobox();
		vwinsrec.getBtnInserisci().addActionListener(l -> InserisciRecensione());
	}
	
	private void popolacombobox()
	{
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		PreparedStatement s;
		   try 
		   {
			
			 s = connessione.prepareStatement ("SELECT DISTINCT pubblicazione.ISBN from pubblicazione LEFT JOIN recensioni on pubblicazione.ISBN=recensioni.ISBN WHERE  pubblicazione.ISBN NOt IN (SELECT ISBN FROM utenti INNER JOIN recensioni ON utenti.ID=recensioni.ID_Utente WHERE recensioni.ID_Utente=?);");
			 s.setInt(1, ControllerLogin.user.getID());
			 ResultSet rs = s.executeQuery();
			 
		   while (rs.next ())
		   {

			    long ISBN = rs.getLong("ISBN");
			    vwinsrec.getComboBox().addItem(ISBN);  
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
	}
	
	private void InserisciRecensione()
	{
		String testo = vwinsrec.getTextArea().getText();
		
		if(testo.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Non puoi inserire una recensione vuota", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			long ISBN =(long) vwinsrec.getComboBox().getSelectedItem();
			
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			PreparedStatement s;
			   try 
			   {
				   
				   s = connessione.prepareStatement ("insert into recensioni (Testo,Pubblicata,ISBN,ID_Utente) values(?,0,?,?)");
				   s.setString (1, testo);
				   s.setLong (2, ISBN);
				   s.setInt (3, ControllerLogin.user.getID());
				   
				   int rs = s.executeUpdate();
				
				   s.close ();
				   conn.closeConnection();
				   
				   if (rs>0)
				   {
					   JOptionPane.showMessageDialog(null, "Recensione inserita", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
				   }
				   else
				   {
					   JOptionPane.showMessageDialog(null, "Inserimento fallito", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
				   }
				
			   }
			   catch(SQLException e)
			   {
				   System.err.println ("Error message: " + e.getMessage ());
			       System.err.println ("Error number: " + e.getErrorCode ());
			   }
			
		}
		
		
		
	}

}

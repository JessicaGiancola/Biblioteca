package controllers;

import database.*;
import java.sql.*;
import javax.swing.JOptionPane;

import models.*;
import views.*;
import classi.Utente;

public class ControllerLogin extends Controller{
	
	private ModelLogin mdlogin;
	private ViewLogin vwlogin;
	public static Utente user;
	
	public ControllerLogin(ModelLogin mdlogin, ViewLogin vwlogin)  {
		super(vwlogin);
		this.mdlogin = mdlogin;
		this.vwlogin=vwlogin;
	}
	
	public void InitController() 
	{
		super.InitController();
		vwlogin.getBtnLogin().addActionListener(l -> autenticazione(vwlogin.getNomeutente().getText(),vwlogin.getPassword().getPassword()));
	}
	
	
	private void autenticazione(String username, char[] password)
	{
		String pswfinal="";
		
		for(int i=0;i<password.length;i++)
		{
			pswfinal+=password[i];
		}
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	   PreparedStatement s;
	   try 
	   {
		   
	   
	   s = connessione.prepareStatement ("select * from utenti where Email= ?");
	   s.setString (1, username);
	   ResultSet rs = s.executeQuery();
	   
	   boolean utentetrovato = false;
	   boolean passwordcorretta=false;
	   
	   while (rs.next ())
	   {
		   String dbpassword = rs.getString ("Password");
		   
		   passwordcorretta = pswfinal.equals(dbpassword);
		   utentetrovato=true;
		   
		   if(passwordcorretta)
		   {
			 //recupero valori dal db e creo istanza utente
		       int idutente = rs.getInt("ID");
		       String nomeutente = rs.getString ("Nome");
		       String cognomeutente = rs.getString ("Cognome");
		       Date datanascita = rs.getDate("DataNascita");
		       int tipo = rs.getInt ("Tipo");
		       
		       //creazione utente
		       user=new Utente(idutente,nomeutente, cognomeutente,username, datanascita, pswfinal, tipo);
			   JOptionPane.showMessageDialog(null, user.toString(), "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		   }
		   else
		   {
			   JOptionPane.showMessageDialog(null, "Password errata", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		   }
		   

	   }
	   rs.close();
	   s.close ();
	   conn.closeConnection();
	   
	   if(!utentetrovato || !passwordcorretta)
	   {
		   JOptionPane.showMessageDialog(null, "Autenticazione fallita", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE); 
	   }
	   else
	   {
		   JOptionPane.showMessageDialog(null, "Autenticazione riuscita", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		   vwlogin.getFrm().setVisible(false);
		   ViewMenu vwmenu = new ViewMenu();
		   ControllerMenu cm = new ControllerMenu(vwmenu);
		   cm.InitController();
	   }
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
		
	}

}

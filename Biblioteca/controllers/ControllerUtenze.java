package controllers;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import classi.*;
import database.Connect;
import models.ModelPubblicazioni;
import models.ModelUtenze;
import views.ViewPubblicazioni;
import views.ViewUtenze;

public class ControllerUtenze extends Controller {
	
	private ModelUtenze mdutenze;
	private ViewUtenze vwutenze;
	
	public ControllerUtenze(ModelUtenze mdutenze,ViewUtenze vwutenze) {
		super(vwutenze);
		this.mdutenze=mdutenze;
		this.vwutenze = vwutenze;
	}
	
	public void InitController() 
	{
		super.InitController();
		vwutenze.getBtnQuery4().addActionListener(l -> Query4());
		vwutenze.getBtnModifica().addActionListener(l -> ModificaTipoUtente());
		vwutenze.getBtnElenco().addActionListener(l -> ElencoPubblUtente());
		vwutenze.getBtnUtenti().addActionListener(l -> Utenti());
		
		CreateTable(mdutenze.getListautenti());
	}
	
	private void CreateTable(List<Utente> listautenti) {
		
		DefaultTableModel dtm= new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		dtm.setColumnIdentifiers(vwutenze.getColumnname());
		//String [] header= vwutenze.getColumnname();
		//dtm.addRow(header);
		
		
		ListIterator<Utente> i= listautenti.listIterator();
		
		while(i.hasNext()) {
			Utente obj= i.next();
			String tipo= "Passivo";
			if (obj.getTipo() == 1)
			{
				tipo = "Attivo";
			}
			Object[] row= {obj.getID(),obj.getNome(), obj.getCognome(), tipo};
			//Object [] row = {listautenti.getNome(),mdutenze.getListautenti().get(i).getCognome(),mdutenze.getListautenti().get(i).getTipo(), "Modifica tipo", "Elenco pubblicazioni"};	
			//Object [] row= {i.next().getNome(), ((Utente) i).getCognome(),((Utente) i).getTipo(),"Modifica tipo","Elenco pubblicazioni"};
			dtm.addRow(row);
		}
		
		JTable table = new JTable(dtm);
		//table.setBounds(10, 63, 661, 287);
		
		table.removeColumn(table.getColumn("ID"));
		
		//JPanel tablePanel = new JPanel(new BorderLayout());
		//tablePanel.setBounds(10, 63, 661, 287);
		vwutenze.setTable(table);
		
		vwutenze.getPanel().add(vwutenze.getTable(), BorderLayout.CENTER);
		vwutenze.getPanel().add(vwutenze.getTable().getTableHeader(), BorderLayout.NORTH);
		
	    //JTableButtonMouseListener tabella= new JTableButtonMouseListener(table);
	   
		vwutenze.getFrm().getContentPane().add(vwutenze.getPanel());
		vwutenze.getPanel().revalidate();
		vwutenze.getPanel().repaint();
	}
	
	private void Utenti() {
		
		mdutenze.setListautenti(ControllerMenu.listautenzebase());
		
	   vwutenze.getPanel().remove(vwutenze.getTable());
	   CreateTable(mdutenze.getListautenti());
	}
	
	private void Query4() {
		List<Utente> listautenti = new ArrayList<Utente>();
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	   try 
	   {
		
		 Statement stmt = connessione.createStatement();
		 ResultSet rs = stmt.executeQuery("SELECT DISTINCT utenti.ID,Nome,Cognome,DataNascita,email,Tipo FROM utenti INNER JOIN storico on storico.ID_Utente=utenti.ID WHERE storico.Operazione='Inserimento' ORDER BY storico.DataOra");
		 
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
	   
	   mdutenze.setListautenti(listautenti);
	   
	   vwutenze.getPanel().remove(vwutenze.getTable());
	   CreateTable(mdutenze.getListautenti());
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
		//SELECT DISTINCT (Nome,Cognome,DataNascita,email) FROM utenti INNER JOIN storico on storico.ID_Utente=utenti.ID WHERE storico.Operazione='Inserimento' ORDER BY storico.DataOra ;
	}
	
	private void ModificaTipoUtente() {
		JTable table = vwutenze.getTable();
		int row = table.getSelectedRow();
		
		if (row>=0)
		{
			int idselected = (int) table.getModel().getValueAt(row, 0);
			String tipoutente = (String) table.getModel().getValueAt(row, 3);
			int tipodb;
			if(tipoutente.equals("Passivo"))
			{
				tipoutente="Attivo";
				tipodb=1;
			}
			else
			{
				tipoutente="Passivo";
				tipodb=0;
			}
			
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		   PreparedStatement s;
		   try 
		   {
			   
		   
		   s = connessione.prepareStatement ("update utenti set Tipo = ? where ID =  ?");
		   s.setInt (1, tipodb);
		   s.setInt(2, idselected);
		   int rs = s.executeUpdate();
		   
		   s.close ();
		   conn.closeConnection();
		   
		   if (rs>0)
		   {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setValueAt(tipoutente, row, 3);
		   }
		   else
		   {
			   JOptionPane.showMessageDialog(null, "Modifica fallita", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		   }
			   
		   }
		   catch(SQLException e)
		   {
			   System.err.println ("Error message: " + e.getMessage ());
		       System.err.println ("Error number: " + e.getErrorCode ());
		   }
			
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Devi selezionare prima una riga", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void ElencoPubblUtente() {
		
		List<Pubblicazione> catalogo= new ArrayList();
		
		JTable table = vwutenze.getTable();
		int row = table.getSelectedRow();
		
		if (row>=0)
		{
			int idutente = (int) table.getModel().getValueAt(row, 0);
					
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		   PreparedStatement s;
		   try 
		   {
			   
		   
		   s = connessione.prepareStatement ("Select * from pubblicazione where ID_Utente=?");
		   s.setLong(1, idutente);
		   ResultSet rs = s.executeQuery();
		   
		   while(rs.next())
			   
		   {
			   //recupero valori dal db e creo istanza pubblicazione
	       	   long ISBN=rs.getLong("ISBN");
		       String titolo = rs.getString ("Titolo");
		       String editore = rs.getString ("Editore");
		       Date datapubblicazione=rs.getDate("DataPubblicazione");
		       String autori = rs.getString ("Autori");
		       boolean download=rs.getBoolean("Dowload");
		       
		       //creazione pubblicazione
		       Pubblicazione libro=new Pubblicazione(ISBN,titolo,editore,datapubblicazione,download,autori);
			   catalogo.add(libro);		
		   }
		   
		   s.close ();
		   conn.closeConnection();
		   
		   if (catalogo.isEmpty())
		   {
			   JOptionPane.showMessageDialog(null, "Nessuna pubblicazione per questo utente", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		   }
		   else
		   {
			   ViewPubblicazioni vwp=new ViewPubblicazioni();
			   ModelPubblicazioni mdp=new ModelPubblicazioni(catalogo);
			   ControllerPubblicazioni cp= new ControllerPubblicazioni(mdp,vwp);
			   cp.InitController();
		   }	 
			   
		   }
		   catch(SQLException e)
		   {
			   System.err.println ("Error message: " + e.getMessage ());
		       System.err.println ("Error number: " + e.getErrorCode ());
		   }
			
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Devi selezionare prima una riga", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		}

}


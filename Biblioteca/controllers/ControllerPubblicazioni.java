package controllers;

import models.ModelPubblicazioni;
import models.ModelRecensioni;
import views.ViewDettaglioPubblicazione;
import views.ViewPubblicazioni;
import views.ViewRecensioni;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classi.Pubblicazione;
import classi.Recensione;
import database.Connect;

public class ControllerPubblicazioni  extends Controller{
	
	private ModelPubblicazioni mdp;
	private ViewPubblicazioni vwpubb;
	
	public ControllerPubblicazioni(ModelPubblicazioni mdp, ViewPubblicazioni vwpubb) {
		super(vwpubb);
		this.mdp = mdp;
		this.vwpubb = vwpubb;
	}
	
	public void InitController() 
	{
		super.InitController();
		vwpubb.getBtnDettaglioPubblicazione().addActionListener(l->DettaglioLibro());
		vwpubb.getBtnElencoRecensioni().addActionListener(l-> ElencoRecensioni());
		vwpubb.getBtnMiPiace().addActionListener(l->MiPiace());
		vwpubb.getChckbxQuery16().addActionListener(l-> Query16());
		vwpubb.getChckbxQuery17().addActionListener(l-> Query17());
		vwpubb.getChckbxQuery2().addActionListener(l-> Query2());
		vwpubb.getChckbxQuery3().addActionListener(l-> Query3());
		vwpubb.getChckbxQuery6().addActionListener(l-> Query6());
		vwpubb.getTextField().addActionListener(l -> Cerca());
		vwpubb.getBtnQuery18().addActionListener(l -> Query18());
		
		CreateTable(mdp.getCatalogo());
	}
	
	private void CreateTable(List<Pubblicazione> listap)
	{
		DefaultTableModel dtm= new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		dtm.setColumnIdentifiers(vwpubb.getColumnname());
		
		ListIterator<Pubblicazione> i= listap.listIterator();
		
		while(i.hasNext()) {
			Pubblicazione obj= i.next();
			
			String download="Non disponibile";
			Object[] row= {obj.getISBN(),obj.getTitolo(),obj.getEditore(),obj.getDatapubblicazione(),obj.getAutori(),download};
			
			if(obj.isDownload())
			{
				download="Disponibile";
			}
			
			if(obj.getNumerolike()>=0)
			{
				 row= new Object [] {obj.getISBN(),obj.getTitolo(),obj.getEditore(),obj.getDatapubblicazione(),obj.getAutori(),obj.getNumerolike(),download};
			}
		
			if(obj.getUltimaristampa()!=null)
			{
				 row=  new Object [] {obj.getISBN(),obj.getTitolo(),obj.getEditore(),obj.getDatapubblicazione(),obj.getAutori(),obj.getUltimaristampa(),download};
			}
			
			
			dtm.addRow(row);
		}
		
		JTable table = new JTable(dtm);
		
		vwpubb.setTable(table);
		
		vwpubb.getScrollPane().add(vwpubb.getTable());
		vwpubb.getScrollPane().add(vwpubb.getTable().getTableHeader());
		
		vwpubb.getScrollPane().setViewportView(table);
		
		vwpubb.getFrm().getContentPane().add(vwpubb.getScrollPane());
		vwpubb.getScrollPane().revalidate();
		vwpubb.getScrollPane().repaint();
		
	}
	
	private void Cerca()
	{
		List<Pubblicazione> catalogo= new ArrayList();
		JTable table = vwpubb.getTable();
		
		String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Download"};
		
		vwpubb.setColumnname(columnname);
		
		String testoricerca = vwpubb.getTextField().getText();
		
		Connect conn= new Connect();	
		Connection connessione=conn.getConnection();
		
	   PreparedStatement s;
	   try 
	   {  
	   
	   s = connessione.prepareStatement ("SELECT * FROM pubblicazione WHERE ISBN LIKE ? OR Titolo LIKE ? OR Keywords LIKE ? OR Autori LIKE ?");
	   s.setString(1, "%"+testoricerca+"%");
	   s.setString(2, "%"+testoricerca+"%");
	   s.setString(3, "%"+testoricerca+"%");
	   s.setString(4, "%"+testoricerca+"%");
	   
	   ResultSet rs = s.executeQuery();
	   
	   
	   while (rs.next ())
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
	   rs.close();
	   s.close ();
	   conn.closeConnection();
	   mdp.setCatalogo(catalogo);
		
	   }
	   catch(SQLException e)
	   {
		   System.err.println ("Error message: " + e.getMessage ());
	       System.err.println ("Error number: " + e.getErrorCode ());
	   }
	   
	   vwpubb.getScrollPane().remove(vwpubb.getTable());
		CreateTable (mdp.getCatalogo());
	   
	}
	
	private void DettaglioLibro()
	{
		JTable table = vwpubb.getTable();
		int row = table.getSelectedRow();
		
		if (row>=0)
		{
			long ISBN = (long) table.getModel().getValueAt(row, 0);
			ViewDettaglioPubblicazione vwdpubb = new ViewDettaglioPubblicazione();
			ControllerDettaglioPubblicazione cdpubb = new ControllerDettaglioPubblicazione(vwdpubb,ISBN);
			cdpubb.InitController();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Devi selezionare prima una riga", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	private void ElencoRecensioni()
	{
		List<Recensione> listarec= new ArrayList();
		
		JTable table = vwpubb.getTable();
		int row = table.getSelectedRow();
		
		if (row>=0)
		{
			long ISBN = (long) table.getModel().getValueAt(row, 0);
					
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		   PreparedStatement s;
		   try 
		   {
			   
		   
		   s = connessione.prepareStatement ("Select * from recensioni where ISBN=? and Pubblicata=1");
		   s.setLong(1, ISBN);
		   ResultSet rs = s.executeQuery();
		   
		   while(rs.next())
			   
		   {
			  //recupero i dati dal db e creo istanza recensioni
			   int id=rs.getInt("ID");
		       int idutente = rs.getInt("ID_Utente");
		       String testo = rs.getString ("Testo");
		       Date dataora=rs.getDate("DataOra");
		       boolean pubblicata=rs.getBoolean("Pubblicata");
		       //creazione recensione
			   Recensione recriga=new Recensione(id,testo, pubblicata,dataora,ISBN,idutente);
			   listarec.add(recriga);		
		   }
		   
		   s.close ();
		   conn.closeConnection();
		   
		   if (listarec.isEmpty())
		   {
			   JOptionPane.showMessageDialog(null, "Nessuna recensione pubblicata per questo ISBN", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		   }
		   else
		   {
			   ViewRecensioni vwrec=new ViewRecensioni();
			   ModelRecensioni mdrec=new ModelRecensioni(listarec);
			   ControllerRecensioni cr= new ControllerRecensioni(mdrec,vwrec);
			   cr.InitController();
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
	
	private void MiPiace()
	{
		JTable table = vwpubb.getTable();
		int row = table.getSelectedRow();
		
		if (row>=0)
		{
			long ISBN = (long) table.getModel().getValueAt(row, 0);
			int idutente= ControllerLogin.user.getID();
					
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		   PreparedStatement s;
		   try 
		   {
			   
		   
		   s = connessione.prepareStatement ("Select * from mipiace where ID_Utente= ? and ISBN=?");
		   s.setInt (1, idutente);
		   s.setLong(2, ISBN);
		   ResultSet rs = s.executeQuery();
		   
		   boolean trovato=false;
		   
		   while(rs.next())
			   
		   {
			   trovato=true;
		   }
		   
		   if(!trovato)
		   {
			   s = connessione.prepareStatement ("insert into mipiace (ID_Utente,ISBN) values (?,?)");
			   s.setInt (1, idutente);
			   s.setLong(2, ISBN);
			   int res = s.executeUpdate();
			   if (res>0)
			   {
				   DefaultTableModel model = (DefaultTableModel)table.getModel();
				   int nummipiace= (int) model.getValueAt(row, 5);
				   nummipiace++;
					model.setValueAt(nummipiace, row, 5);
			   }
			   else
			   {
				   JOptionPane.showMessageDialog(null, "Inserimento fallito", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
			   }
			   
   		   }
		   else
		   {
			   JOptionPane.showMessageDialog(null, "Hai già inserito il tuo mi piace", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		   }
		   
		   
		   
		   s.close ();
		   conn.closeConnection();
		   
		 
			   
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
	
	
	private void Query16()
	{ //download disponibile
		
		String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Numero like","Download"};
		
		vwpubb.setColumnname(columnname);
		
		if(vwpubb.getChckbxQuery16().isSelected())
		{
			List<Pubblicazione> catalogo= new ArrayList();
			
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		  
		   try 
		   {
			
			 Statement stmt = connessione.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT pubblicazione.ISBN, Titolo, Editore, DataPubblicazione,Autori,Dowload,NumeroLike FROM pubblicazione LEFT JOIN (SELECT ISBN,count('ID_Utente') as NumeroLike FROM mipiace GROUP BY ISBN) as piace ON pubblicazione.ISBN=piace.ISBN where Dowload=1");
			 
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
		   mdp.setCatalogo(catalogo);
			
		   }
		   catch(SQLException e)
		   {
			   System.err.println ("Error message: " + e.getMessage ());
		       System.err.println ("Error number: " + e.getErrorCode ());
		   }
			
		}
		
		else
		{
			mdp.setCatalogo(ControllerMenu.pubblicazionebase());
		}
		

	   vwpubb.getScrollPane().remove(vwpubb.getTable());
		CreateTable (mdp.getCatalogo());
		
	}
	
	private void Query17()
	{ //data ultima ristampa
		
		String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Ultima Ristampa","Download"};
		vwpubb.setColumnname(columnname);
		
		if(vwpubb.getChckbxQuery17().isSelected())
		{
			List<Pubblicazione> catalogo= new ArrayList();
			
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		  
		   try 
		   {
			
			 Statement stmt = connessione.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT MAX(ristampe.Data) as maxdata,pubblicazione.* FROM pubblicazione INNER JOIN ristampe ON pubblicazione.ISBN=ristampe.ISBN GROUP BY ristampe.ISBN");
			 
		   while (rs.next ())
		   {
			  
				 //recupero valori dal db e creo istanza pubblicazione
		       	   long ISBN=rs.getLong("ISBN");
			       String titolo = rs.getString ("Titolo");
			       String editore = rs.getString ("Editore");
			       Date datapubblicazione=rs.getDate("DataPubblicazione");
			       String autori = rs.getString ("Autori");
			       boolean download=rs.getBoolean("Dowload");
			       Date ultimaristampa = rs.getDate("maxdata");
			       
			       //creazione pubblicazione
			       Pubblicazione libro=new Pubblicazione(ISBN,titolo,editore,datapubblicazione,download,autori,ultimaristampa);
				   catalogo.add(libro);				   
		   }
		   rs.close();
		   stmt.close ();
		   conn.closeConnection();
		   mdp.setCatalogo(catalogo);
			
		   }
		   catch(SQLException e)
		   {
			   System.err.println ("Error message: " + e.getMessage ());
		       System.err.println ("Error number: " + e.getErrorCode ());
		   }
			
		}
		
		else
		{
			mdp.setCatalogo(ControllerMenu.pubblicazionebase());
		}
		

	    vwpubb.getScrollPane().remove(vwpubb.getTable());
		CreateTable (mdp.getCatalogo());
	}
	
	private void Query18() 
	{
		List<Pubblicazione> catalogo= new ArrayList();
		
		String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Download"};
		vwpubb.setColumnname(columnname);
		
		JTable table = vwpubb.getTable();
		int row = table.getSelectedRow();
		
		if (row>=0)
		{
			String autori = (String) table.getModel().getValueAt(row, 4);
			
			String[] listaautori= autori.split(",");
					
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		  // PreparedStatement s;
			
		   String Query="Select * from pubblicazione where Autori LIKE '%"+listaautori[0]+"%'";
		   
		   for(int i=1;i<listaautori.length;i++)
		   {
			   Query+=" OR Autori LIKE '%"+listaautori[i]+"%' ";
		   }
		   
		   System.out.println(Query);
		   try 
		   {   
			   Statement stmt = connessione.createStatement();
			  // s = connessione.prepareStatement ("Select * from recensioni where ISBN=? and Pubblicata=1");
			   //s.setLong(1, ISBN);
			   ResultSet rs = stmt.executeQuery(Query);
			   
			   while(rs.next())
				   
			   {
				  //recupero i dati dal db e creo istanza pubblicazioni
				   long ISBN=rs.getLong("ISBN");
			       String titolo = rs.getString ("Titolo");
			       String editore = rs.getString ("Editore");
			       Date datapubblicazione=rs.getDate("DataPubblicazione");
			       boolean download=rs.getBoolean("Dowload");
			       
			     //creazione pubblicazione
			       Pubblicazione libro=new Pubblicazione(ISBN,titolo,editore,datapubblicazione,download,autori);
				   catalogo.add(libro);	
			       
		
			   }
			   
			   stmt.close ();
			   conn.closeConnection();
			   
			   if (catalogo.isEmpty())
			   {
				   JOptionPane.showMessageDialog(null, "Nessuna pubblicazione trovata con questi autori", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
			   }
			   else
			   {
				   ModelPubblicazioni mdp = new ModelPubblicazioni(catalogo);
				   ViewPubblicazioni vwp = new ViewPubblicazioni();
				   ControllerPubblicazioni cp = new ControllerPubblicazioni(mdp,vwp);
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
	
	private void Query2()
	{ // ultime 10 pubblicazioni
		
		String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Numero like","Download"};
		
		vwpubb.setColumnname(columnname);
		
		if(vwpubb.getChckbxQuery2().isSelected())
		{
			List<Pubblicazione> catalogo= new ArrayList();
			
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		  
		   try 
		   {
			
			 Statement stmt = connessione.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT pubblicazione.ISBN, Titolo, Editore, DataPubblicazione,Autori,Dowload,NumeroLike FROM pubblicazione LEFT JOIN (SELECT ISBN,count('ID_Utente') as NumeroLike FROM mipiace GROUP BY ISBN) as piace ON pubblicazione.ISBN=piace.ISBN order by DataPubblicazione desc limit 0,10");
			 
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
		   mdp.setCatalogo(catalogo);
			
		   }
		   catch(SQLException e)
		   {
			   System.err.println ("Error message: " + e.getMessage ());
		       System.err.println ("Error number: " + e.getErrorCode ());
		   }
			
		}
		
		else
		{
			mdp.setCatalogo(ControllerMenu.pubblicazionebase());
		}
		

	   vwpubb.getScrollPane().remove(vwpubb.getTable());
		CreateTable (mdp.getCatalogo());
		
	}
	
	private void Query3()
	{ // elenco pubblicazione ultimi 30 giorni
		
		String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Download"};
		
		vwpubb.setColumnname(columnname);
		
		if(vwpubb.getChckbxQuery3().isSelected())
		{
			List<Pubblicazione> catalogo= new ArrayList();
			
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		  
		   try 
		   {
			
			 Statement stmt = connessione.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT DISTINCT Titolo,pubblicazione.*,storico.DataOra,DATEDIFF(CURRENT_DATE,storico.DataOra)AS 'differenzadata' FROM pubblicazione INNER JOIN storico ON storico.ISBN=pubblicazione.ISBN WHERE storico.operazione='Modifica' AND 'differenzadata'<=30");
			 
		   while (rs.next ())
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
		   rs.close();
		   stmt.close ();
		   conn.closeConnection();
		   mdp.setCatalogo(catalogo);
			
		   }
		   catch(SQLException e)
		   {
			   System.err.println ("Error message: " + e.getMessage ());
		       System.err.println ("Error number: " + e.getErrorCode ());
		   }
			
		}
		
		else
		{
			mdp.setCatalogo(ControllerMenu.pubblicazionebase());
		}
		

	   vwpubb.getScrollPane().remove(vwpubb.getTable());
		CreateTable (mdp.getCatalogo());
		
		
	}
	
	private void Query6()
	{// ordinamento per titolo
		
		String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Numero like","Download"};
		
		vwpubb.setColumnname(columnname);
		
		if(vwpubb.getChckbxQuery6().isSelected())
		{
			Collections.sort(mdp.getCatalogo());	
		}
		else
		{
			mdp.setCatalogo(ControllerMenu.pubblicazionebase());
		}
		
		vwpubb.getScrollPane().remove(vwpubb.getTable());
		CreateTable (mdp.getCatalogo());
		
	}
	


}

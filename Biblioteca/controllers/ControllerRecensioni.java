package controllers;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classi.Recensione;
import database.Connect;
import models.ModelRecensioni;
import views.ViewInserisciRecensione;
import views.ViewRecensioni;

public class ControllerRecensioni extends Controller{
	
	private ModelRecensioni mdrecensioni;
	private ViewRecensioni vwrecensioni;
	
	public ControllerRecensioni(ModelRecensioni mdr, ViewRecensioni vwr) {
		super(vwr);
		this.mdrecensioni=mdr;
		this.vwrecensioni=vwr;
	}
	
	@Override
	public void InitController() {
		super.InitController();
		vwrecensioni.getBtnInserisciRecensione().addActionListener(l -> InserisciRecensione());
		vwrecensioni.getBtnPubblica().addActionListener(l -> Pubblica());
		vwrecensioni.getCheckNonPubblicate().addActionListener(l -> FiltraNonPubblicate());
		
		CreateTable(mdrecensioni.getListar());
	}
	
	private void CreateTable(List<Recensione> listar)
	{
		DefaultTableModel dtm= new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		dtm.setColumnIdentifiers(vwrecensioni.getColumnname());
		
		ListIterator<Recensione> i= listar.listIterator();
		
		while(i.hasNext()) {
			Recensione obj= i.next();
			
			String pubb="Non pubblicata";
			if(obj.isPubblicata())
			{pubb="Pubblicata";}
			
			Object[] row= {obj.getID(),obj.getID_Utente(),obj.getTesto(),obj.getISBN(),obj.getDataOra(),pubb};
			dtm.addRow(row);
		}
		
		JTable table = new JTable(dtm);
		
		vwrecensioni.setTable(table);
		
		vwrecensioni.getPanel().add(vwrecensioni.getTable(), BorderLayout.CENTER);
		vwrecensioni.getPanel().add(vwrecensioni.getTable().getTableHeader(), BorderLayout.NORTH);
		
	   
		vwrecensioni.getFrm().getContentPane().add(vwrecensioni.getPanel());
		vwrecensioni.getPanel().revalidate();
		vwrecensioni.getPanel().repaint();
		
	}
	
	public void InserisciRecensione()
	{
		ViewInserisciRecensione vwinsrec = new ViewInserisciRecensione();
		ControllerInserisciRecensione cinsrec = new ControllerInserisciRecensione(vwinsrec);
		cinsrec.InitController();
	}
	
	public void FiltraNonPubblicate()
	{
		List<Recensione> listarecensioni = new ArrayList();		
		
		if(vwrecensioni.getCheckNonPubblicate().isSelected())
		{
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		
		   try 
		   {
			   Statement stmt = connessione.createStatement();
			   ResultSet rs = stmt.executeQuery("select ID,ID_Utente,Testo,ISBN,DataOra, Pubblicata from recensioni where Pubblicata=0");
			   
			   while (rs.next ())
			   {	  
					 //recupero valori dal db e creo istanza recensioni
				   	   int id=rs.getInt("ID");
				       int idutente = rs.getInt("ID_Utente");
				       String testo = rs.getString ("Testo");
				       long ISBN=rs.getLong("ISBN");
				       Date dataora=rs.getDate("DataOra");
				       boolean pubblicata=rs.getBoolean("Pubblicata");
				       //creazione recensione
					   Recensione recriga=new Recensione(id,testo, pubblicata,dataora,ISBN,idutente);
					   listarecensioni.add(recriga);				   
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
		   
		   mdrecensioni.setListar(listarecensioni);
		  
		}
		else
		{
			 mdrecensioni.setListar(ControllerMenu.recensionibase());
	
		}
		 vwrecensioni.getPanel().remove(vwrecensioni.getTable());
		CreateTable(mdrecensioni.getListar());
	}
	
	public void Pubblica()
	{		
		JTable table = vwrecensioni.getTable();
		int row = table.getSelectedRow();
	
		if (row>=0)
		{
			String pubb = (String) table.getModel().getValueAt(row, 5);
			int idrecensione= (int) table.getModel().getValueAt(row,0);
		
			if (pubb.equals("Pubblicata"))
			{ 
				JOptionPane.showMessageDialog(null, "La recensione è già stata pubblicata", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE); 
			}
			else 
			{
			
				pubb="Pubblicata";
				Connect conn= new Connect();	
				Connection connessione=conn.getConnection();
			
				PreparedStatement s;
				try 
				{
		   
				    s = connessione.prepareStatement ("update recensioni set Pubblicata = 1 where ID =  ?");
				    s.setInt (1, idrecensione);
				    int rs = s.executeUpdate();
				   
				    s.close ();
				    conn.closeConnection();
		   
				    if (rs>0)
				    {
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						model.setValueAt(pubb, row, 5);
				    }
				    else
				    {
					   JOptionPane.showMessageDialog(null, "Pubblicazione fallita", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
				    }
				}
			   catch(SQLException e)
			   {
				   System.err.println ("Error message: " + e.getMessage ());
			       System.err.println ("Error number: " + e.getErrorCode ());
			   }
			}
		 
		} 
		else
		{
			JOptionPane.showMessageDialog(null, "Devi selezionare prima una riga", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

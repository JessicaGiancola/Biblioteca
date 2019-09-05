package controllers;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import models.ModelStorico;
import views.ViewStorico;
import classi.Storico;

import database.Connect;

public class ControllerStorico extends Controller{
	
	private ModelStorico mds;
	private ViewStorico vwstorico;

	public ControllerStorico(ModelStorico mds,ViewStorico vwstorico) {
		super(vwstorico);
		this.mds=mds;
		this.vwstorico=vwstorico;
	}
	
	public void InitController() 
	{
		super.InitController();
		vwstorico.getBtnVisualizzaModifiche().addActionListener(l -> Logmodifiche());
		vwstorico.getBtnStorico().addActionListener(l -> Storico());
		
		CreateTable(mds.getStorici());
	}
	
	private void CreateTable(List<Storico> listastorico)
	{
		DefaultTableModel dtm= new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		dtm.setColumnIdentifiers(vwstorico.getColumnname());
		
		ListIterator<Storico> i= listastorico.listIterator();
		
		while(i.hasNext()) {
			Storico obj= i.next();
			
			Object[] row= {obj.getISBN(), obj.StoricoToString()};
			dtm.addRow(row);
		}
		
		JTable table = new JTable(dtm);
		
		vwstorico.setTable(table);
		
		vwstorico.getPanel().add(vwstorico.getTable(), BorderLayout.CENTER);
		vwstorico.getPanel().add(vwstorico.getTable().getTableHeader(), BorderLayout.NORTH);
		
	   
		vwstorico.getFrm().getContentPane().add(vwstorico.getPanel());
		vwstorico.getPanel().revalidate();
		vwstorico.getPanel().repaint();
		
		
	}
	
	private void Logmodifiche()
	{
		List<Storico> listastorico = new ArrayList();
		
		JTable table = vwstorico.getTable();
		int row = table.getSelectedRow();
		
		if(row>=0)
		{
			long ISBN = (long) table.getModel().getValueAt(row, 0);
			Connect conn= new Connect();	
			Connection connessione=conn.getConnection();
			
		   PreparedStatement s;
		   try 
		   {
			//SELECT DataOra,Operazione,ID_Utente FROM storico WHERE Operazione='Modifica' and storico.ISBN=1234567891234
			   s = connessione.prepareStatement ("SELECT Operazione,ID_Utente FROM storico WHERE Operazione='Modifica' and storico.ISBN=?");
			   s.setLong(1, ISBN);
			   ResultSet rs = s.executeQuery();
			   
			// Statement stmt = connessione.createStatement();
			// ResultSet rs = stmt.executeQuery("select Operazione,ID_Utente,ISBN from storico");
			 
		   while (rs.next ())
		   {	  
				 //recupero valori dal db e creo istanza storico
			       int idutente = rs.getInt("ID_Utente");
			       String operazione = rs.getString ("Operazione");
			       
			       //creazione storico
				   Storico striga=new Storico(ISBN,idutente, operazione);
				   listastorico.add(striga);				   
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
		   
		   mds.setStorici(listastorico);
			vwstorico.getPanel().remove(vwstorico.getTable());
			CreateTable(mds.getStorici());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Devi selezionare prima una riga", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	
	private void Storico()
	{
		List<Storico> listastorico = ControllerMenu.storicobase();
		vwstorico.getPanel().remove(vwstorico.getTable());
		mds.setStorici(listastorico);
		CreateTable(mds.getStorici());
		
	}

}

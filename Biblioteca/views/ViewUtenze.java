package views;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ViewUtenze extends Views{
	
	private JTable table;
	private JPanel panel = new JPanel(new BorderLayout());
	private JButton btnQuery4 = new JButton("Estrai l'elenco degli utenti pi\u00F9 attivi");
	private JButton btnUtenti = new JButton("Utenti");
	private JButton btnModifica = new JButton("Modifica il tipo di utente");
	private JButton btnElenco = new JButton("Elenco delle pubblicazioni");
	private String[] columnname = {"ID","Nome","Cognome","Tipo"};

	public ViewUtenze() {
		super();
		initialize();
	}

	private void initialize() {
		JFrame frmUtenze = super.getFrm();	
		frmUtenze.setBounds(100, 100, 700, 400);
		frmUtenze.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmUtenze.getContentPane().setLayout(null);
		
		panel.setBounds(10, 73, 661, 277);
		
		

		btnQuery4.setBounds(10, 11, 250, 23);
		frmUtenze.getContentPane().add(btnQuery4);
		
		btnModifica.setBounds(280, 11, 170, 23);
		frmUtenze.getContentPane().add(btnModifica);
		
		btnElenco.setBounds(470, 11, 200, 23);
		frmUtenze.getContentPane().add(btnElenco);
		
		btnUtenti.setBounds(470, 41, 200, 23);
		frmUtenze.getContentPane().add(btnUtenti);
		
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public String[] getColumnname() {
		return columnname;
	}

	public void setColumnname(String[] columnname) {
		this.columnname = columnname;
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnQuery4() {
		return btnQuery4;
	}

	public void setBtnQuery4(JButton btnQuery4) {
		this.btnQuery4 = btnQuery4;
	}

	public JButton getBtnModifica() {
		return btnModifica;
	}

	public void setBtnModifica(JButton btnModifica) {
		this.btnModifica = btnModifica;
	}

	public JButton getBtnElenco() {
		return btnElenco;
	}

	public void setBtnElenco(JButton btnElenco) {
		this.btnElenco = btnElenco;
	}

	public JButton getBtnUtenti() {
		return btnUtenti;
	}

	public void setBtnUtenti(JButton btnUtenti) {
		this.btnUtenti = btnUtenti;
	}
	
	
	
	
}

package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;

public class ViewStorico extends Views{

	private JTable table;
	private JPanel panel = new JPanel(new BorderLayout());
	private JButton btnVisualizzaModifiche = new JButton("Visualizza log modifiche");
	private JButton btnStorico = new JButton("Storico");
	private String [] columnname = {"ISBN","Storico"};

	public ViewStorico() {
		super();
		initialize();
	}


	private void initialize() {
		JFrame frame = super.getFrm();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(10, 62, 760, 480);
		
		btnVisualizzaModifiche.setBounds(10, 11, 191, 31);
		frame.getContentPane().add(btnVisualizzaModifiche);
		
		btnStorico.setBounds(211, 11, 191, 31);
		frame.getContentPane().add(btnStorico);

	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


	public JButton getBtnVisualizzaModifiche() {
		return btnVisualizzaModifiche;
	}


	public void setBtnVisualizzaModifiche(JButton btnVisualizzaModifiche) {
		this.btnVisualizzaModifiche = btnVisualizzaModifiche;
	}


	public JButton getBtnStorico() {
		return btnStorico;
	}


	public void setBtnStorico(JButton btnStorico) {
		this.btnStorico = btnStorico;
	}


	public String[] getColumnname() {
		return columnname;
	}


	public void setColumnname(String[] columnname) {
		this.columnname = columnname;
	}
	
	
	
}

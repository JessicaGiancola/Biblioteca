package views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class ViewRecensioni extends Views{

	private JTable table;
	private JPanel panel = new JPanel(new BorderLayout());
	private JButton btnInserisciRecensione = new JButton("Inserisci una nuova recensione");
	private JButton btnPubblica = new JButton("Approva e pubblica una recensione");
	private String [] columnname = {"ID","ID_Utente","Testo","ISBN","Data","Pubblicata"};
	private JCheckBox checkNonPubblicate = new JCheckBox("Filtra per non pubblicate");

	/**
	 * Create the application.
	 */
	public ViewRecensioni() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = super.getFrm();
		frame.setBounds(100, 100, 712, 588);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel.setBounds(10,50,600,400);
		
		btnInserisciRecensione.setBounds(10, 11, 201, 29);
		frame.getContentPane().add(btnInserisciRecensione);
		
		btnPubblica.setBounds(241, 11, 227, 29);
		frame.getContentPane().add(btnPubblica);
		
		
		checkNonPubblicate.setBounds(493, 14, 141, 23);
		frame.getContentPane().add(checkNonPubblicate);
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

	public JButton getBtnInserisciRecensione() {
		return btnInserisciRecensione;
	}

	public void setBtnInserisciRecensione(JButton btnInserisciRecensione) {
		this.btnInserisciRecensione = btnInserisciRecensione;
	}

	public JButton getBtnPubblica() {
		return btnPubblica;
	}

	public void setBtnPubblica(JButton btnPubblica) {
		this.btnPubblica = btnPubblica;
	}

	public String[] getColumnname() {
		return columnname;
	}

	public void setColumnname(String[] columnname) {
		this.columnname = columnname;
	}

	public JCheckBox getCheckNonPubblicate() {
		return checkNonPubblicate;
	}

	public void setCheckNonPubblicate(JCheckBox checkNonPubblicate) {
		this.checkNonPubblicate = checkNonPubblicate;
	}
}

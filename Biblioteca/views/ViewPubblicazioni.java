package views;


import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.JScrollPane;

public class ViewPubblicazioni extends Views{

	private JTable table;
	private String [] columnname = {"ISBN","Titolo","Editore","Data Pubblicazione","Autori","Numero like","Download"};
	private JTextField textField = new JTextField();
	private JCheckBox chckbxQuery6 = new JCheckBox("Ordina per titolo");
	private JCheckBox chckbxQuery16 = new JCheckBox("Download disponibile");
	private JCheckBox chckbxQuery2 = new JCheckBox("Ultime 10 pubblicazioni");
	private JCheckBox chckbxQuery3 = new JCheckBox("Aggiornate ultimi 30 gg");
	private JCheckBox chckbxQuery17 = new JCheckBox("Includi ultima ristampa");
	private JButton btnDettaglioPubblicazione = new JButton("Dettaglio pubblicazione");
	private JButton btnElencoRecensioni = new JButton("Elenco Recensioni");
	private JButton btnMiPiace = new JButton("Mi piace");
	private JButton btnQuery18=new JButton("Ricerca pubblicazioni con stesso autore");
	private JScrollPane scrollPane = new JScrollPane();

	public ViewPubblicazioni() {
		super();
		initialize();
	}


	private void initialize() {
		JFrame frame = super.getFrm();
		frame.setBounds(100, 100, 756, 496);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		chckbxQuery6.setBounds(6, 7, 126, 23);
		frame.getContentPane().add(chckbxQuery6);
		

		chckbxQuery16.setBounds(134, 7, 138, 23);
		frame.getContentPane().add(chckbxQuery16);
		

		chckbxQuery2.setBounds(274, 7, 142, 23);
		frame.getContentPane().add(chckbxQuery2);
		

		chckbxQuery3.setBounds(418, 7, 151, 23);
		frame.getContentPane().add(chckbxQuery3);
		

		chckbxQuery17.setBounds(571, 7, 138, 23);
		frame.getContentPane().add(chckbxQuery17);
		

		btnDettaglioPubblicazione.setBounds(10, 48, 155, 23);
		frame.getContentPane().add(btnDettaglioPubblicazione);
		

		btnElencoRecensioni.setBounds(175, 48, 130, 23);
		frame.getContentPane().add(btnElencoRecensioni);
		
		btnQuery18.setBounds(400, 48, 132, 23);
		frame.getContentPane().add(btnQuery18);
		

		btnMiPiace.setBounds(315, 48, 75, 23);
		frame.getContentPane().add(btnMiPiace);
		

		scrollPane.setBounds(6, 92, 724, 354);
		frame.getContentPane().add(scrollPane);
		
		//scrollPane.setViewportView(table);
		
		textField.setToolTipText("Cerca");
		textField.setBounds(540, 49, 150, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public JTextField getTextField() {
		return textField;
	}


	public void setTextField(JTextField textField) {
		this.textField = textField;
	}


	public JCheckBox getChckbxQuery6() {
		return chckbxQuery6;
	}


	public void setChckbxQuery6(JCheckBox chckbxQuery6) {
		this.chckbxQuery6 = chckbxQuery6;
	}


	public JCheckBox getChckbxQuery16() {
		return chckbxQuery16;
	}


	public void setChckbxQuery16(JCheckBox chckbxQuery16) {
		this.chckbxQuery16 = chckbxQuery16;
	}


	public JCheckBox getChckbxQuery2() {
		return chckbxQuery2;
	}


	public void setChckbxQuery2(JCheckBox chckbxQuery2) {
		this.chckbxQuery2 = chckbxQuery2;
	}


	public JCheckBox getChckbxQuery3() {
		return chckbxQuery3;
	}


	public void setChckbxQuery3(JCheckBox chckbxQuery3) {
		this.chckbxQuery3 = chckbxQuery3;
	}


	public JCheckBox getChckbxQuery17() {
		return chckbxQuery17;
	}


	public void setChckbxQuery17(JCheckBox chckbxQuery17) {
		this.chckbxQuery17 = chckbxQuery17;
	}


	public JButton getBtnDettaglioPubblicazione() {
		return btnDettaglioPubblicazione;
	}


	public void setBtnDettaglioPubblicazione(JButton btnDettaglioPubblicazione) {
		this.btnDettaglioPubblicazione = btnDettaglioPubblicazione;
	}


	public JButton getBtnElencoRecensioni() {
		return btnElencoRecensioni;
	}


	public void setBtnElencoRecensioni(JButton btnElencoRecensioni) {
		this.btnElencoRecensioni = btnElencoRecensioni;
	}


	public JButton getBtnMiPiace() {
		return btnMiPiace;
	}


	public void setBtnMiPiace(JButton btnMiPiace) {
		this.btnMiPiace = btnMiPiace;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public String[] getColumnname() {
		return columnname;
	}


	public void setColumnname(String[] columnname) {
		this.columnname = columnname;
	}


	public JButton getBtnQuery18() {
		return btnQuery18;
	}


	public void setBtnQuery18(JButton btnQuery18) {
		this.btnQuery18 = btnQuery18;
	}

		

}

package views;



import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ViewInserisciRecensione extends Views{
	
	private JComboBox comboBox = new JComboBox();
	private JButton btnInserisci = new JButton("Inserisci Recensione");
	private JTextArea textArea = new JTextArea();


	public ViewInserisciRecensione() {
		super();
		initialize();
	}

	private void initialize() {
		JFrame frame = super.getFrm();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox.setBounds(125, 12, 160, 20);
		frame.getContentPane().add(comboBox);
		
		btnInserisci.setBounds(295, 11, 129, 23);
		frame.getContentPane().add(btnInserisci);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 414, 200);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(10, 17, 86, 14);
		frame.getContentPane().add(lblNewLabel);
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getBtnInserisci() {
		return btnInserisci;
	}

	public void setBtnInserisci(JButton btnInserisci) {
		this.btnInserisci = btnInserisci;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}

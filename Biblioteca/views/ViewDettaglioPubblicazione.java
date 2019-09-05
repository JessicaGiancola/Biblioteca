package views;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewDettaglioPubblicazione extends Views{

	private JLabel lblNewLabel = new JLabel("");
	private JLabel lblNewLabel_1 = new JLabel("");
	private JLabel lblNewLabel_2 = new JLabel("");
	private JLabel lblNewLabel_3 = new JLabel("");
	private JLabel lblNewLabel_4 = new JLabel("");
	private JLabel lblNewLabel_5 = new JLabel("");
	private JLabel label = new JLabel("");
	private JLabel label_2 = new JLabel("");
	private JLabel label_3 = new JLabel("");
	private JLabel label_4 = new JLabel("");
	private JLabel label_5 = new JLabel("");

	public ViewDettaglioPubblicazione() {
		super();
		initialize();
	}

	private void initialize() {
		JFrame frame = super.getFrm();
		frame.setBounds(100, 100, 535, 329);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel.setBounds(10, 11, 499, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1.setBounds(10, 36, 499, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2.setBounds(10, 61, 499, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3.setBounds(10, 86, 499, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4.setBounds(10, 111, 499, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5.setBounds(10, 136, 499, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		label.setBounds(10, 261, 499, 14);
		frame.getContentPane().add(label);
		
		label_2.setBounds(10, 236, 499, 14);
		frame.getContentPane().add(label_2);
		
		label_3.setBounds(10, 211, 499, 14);
		frame.getContentPane().add(label_3);
		
		label_4.setBounds(10, 186, 499, 14);
		frame.getContentPane().add(label_4);
		
		label_5.setBounds(10, 161, 499, 14);
		frame.getContentPane().add(label_5);
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}

	public void setLblNewLabel_2(JLabel lblNewLabel_2) {
		this.lblNewLabel_2 = lblNewLabel_2;
	}

	public void setLblNewLabel_3(JLabel lblNewLabel_3) {
		this.lblNewLabel_3 = lblNewLabel_3;
	}

	public void setLblNewLabel_4(JLabel lblNewLabel_4) {
		this.lblNewLabel_4 = lblNewLabel_4;
	}

	public void setLblNewLabel_5(JLabel lblNewLabel_5) {
		this.lblNewLabel_5 = lblNewLabel_5;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void setLabel_2(JLabel label_2) {
		this.label_2 = label_2;
	}

	public void setLabel_3(JLabel label_3) {
		this.label_3 = label_3;
	}

	public void setLabel_4(JLabel label_4) {
		this.label_4 = label_4;
	}

	public void setLabel_5(JLabel label_5) {
		this.label_5 = label_5;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}

	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}

	public JLabel getLblNewLabel_4() {
		return lblNewLabel_4;
	}

	public JLabel getLblNewLabel_5() {
		return lblNewLabel_5;
	}

	public JLabel getLabel() {
		return label;
	}

	public JLabel getLabel_2() {
		return label_2;
	}

	public JLabel getLabel_3() {
		return label_3;
	}

	public JLabel getLabel_4() {
		return label_4;
	}

	public JLabel getLabel_5() {
		return label_5;
	}
}

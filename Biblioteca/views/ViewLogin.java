package views;

import javax.swing.JFrame;


import javax.swing.JTextField;

import javax.swing.SpringLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.JPasswordField;


public class ViewLogin  extends Views{

	private JTextField nomeutente;
	private JPasswordField password;
	private JButton btnLogin = new JButton("Login");
	
	
	public ViewLogin() {
		super();
		initialize();
	}


	private void initialize() {
		JFrame frmLogin = super.getFrm();	
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 311, 207);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 95, SpringLayout.WEST, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, -13, SpringLayout.SOUTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -114, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().setLayout(springLayout);
		
		nomeutente = new JTextField();
		nomeutente.setText("lisa.rossi@gmail.com");
		springLayout.putConstraint(SpringLayout.NORTH, nomeutente, 30, SpringLayout.NORTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, nomeutente, 72, SpringLayout.WEST, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, nomeutente, -66, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(nomeutente);
		nomeutente.setColumns(10);
		
		password = new JPasswordField();
		password.setText("abcd");
		springLayout.putConstraint(SpringLayout.NORTH, password, 86, SpringLayout.NORTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 6, SpringLayout.SOUTH, password);
		springLayout.putConstraint(SpringLayout.EAST, password, 0, SpringLayout.EAST, nomeutente);
		springLayout.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, nomeutente);
		frmLogin.getContentPane().add(password);
		password.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, nomeutente);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, nomeutente);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, nomeutente);
		springLayout.putConstraint(SpringLayout.SOUTH, lblPassword, -6, SpringLayout.NORTH, password);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -137, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(lblPassword);
		frmLogin.getContentPane().add(btnLogin);		
		
		
	}


	public JTextField getNomeutente() {
		return nomeutente;
	}

	public void setNomeutente(JTextField nomeutente) {
		this.nomeutente = nomeutente;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}
}

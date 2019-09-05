package views;


import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;


public class ViewMenu extends Views {
	
	 private JButton btnUtenti = new JButton("Utenti");
	 private JButton btnPubblicazioni = new JButton("Pubblicazioni");
	 private JButton btnStorico = new JButton("Storico");
	 private JButton btnRecensioni = new JButton("Recensioni");
	
	public ViewMenu() {
		super();
		initialize();
	}


	public JButton getBtnUtenti() {
		return btnUtenti;
	}


	public JButton getBtnPubblicazioni() {
		return btnPubblicazioni;
	}


	public JButton getBtnStorico() {
		return btnStorico;
	}


	public JButton getBtnRecensioni() {
		return btnRecensioni;
	}


	private void initialize() {
		JFrame frmMenu = super.getFrm();
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.setTitle("Menu");
		frmMenu.setBounds(100, 100, 311, 174);
		SpringLayout springLayout = new SpringLayout();
		frmMenu.getContentPane().setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUtenti, 26, SpringLayout.NORTH, frmMenu.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnUtenti, 27, SpringLayout.WEST, frmMenu.getContentPane());
		frmMenu.getContentPane().add(btnUtenti);
		
		
		frmMenu.getContentPane().add(btnPubblicazioni);
		
		
		
		springLayout.putConstraint(SpringLayout.NORTH, btnStorico, 26, SpringLayout.NORTH, frmMenu.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnStorico, 32, SpringLayout.EAST, btnUtenti);
		springLayout.putConstraint(SpringLayout.EAST, btnStorico, 0, SpringLayout.EAST, btnPubblicazioni);
		frmMenu.getContentPane().add(btnStorico);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, btnRecensioni, 32, SpringLayout.SOUTH, btnUtenti);
		springLayout.putConstraint(SpringLayout.WEST, btnRecensioni, 27, SpringLayout.WEST, frmMenu.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnUtenti, 0, SpringLayout.EAST, btnRecensioni);
		springLayout.putConstraint(SpringLayout.WEST, btnPubblicazioni, 32, SpringLayout.EAST, btnRecensioni);
		springLayout.putConstraint(SpringLayout.NORTH, btnPubblicazioni, 0, SpringLayout.NORTH, btnRecensioni);
		frmMenu.getContentPane().add(btnRecensioni);
		
	}

}

package views;

import javax.swing.JFrame;

public class Views {
	private JFrame frm;
	
	public Views() {
		frm = new JFrame();
	}

	public JFrame getFrm() {
		return frm;
	}

	public void setFrm(JFrame frm) {
		this.frm = frm;
	}

}

package controllers;

import views.Views;

public class Controller {
	
	protected Object vw;
	
	public Controller(Object vw) {
		this.vw=vw;
	}
	
	public void InitController()
	{
		this.InitView();
	}
	
	private void InitView()
	{
		((Views) vw).getFrm().setVisible(true);
	}
	
	public Views getVw() {
		return (Views) vw;
	}

	public void setVw(Views vw) {
		this.vw = vw;
	}
	
}

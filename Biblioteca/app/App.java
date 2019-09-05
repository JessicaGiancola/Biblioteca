package app;

import models.*;
import controllers.*;
import views.*;

public class App{
	
	public static void main(String[] args) {
		ModelLogin m = new ModelLogin("","");
		ViewLogin v = new ViewLogin();
		ControllerLogin c= new ControllerLogin(m,v);
		c.InitController();
	}
}
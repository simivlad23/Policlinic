package MainTest;

import Controller.LoginController;
import GUI.EconomicGUI;
import GUI.LoginGUI;
import GUI.ResurseUmaneGUI;

public class Main {
	public static void main(String[] args) {
		LoginGUI loginFrame = new LoginGUI();
		LoginController login = new LoginController(loginFrame);
	}
}

package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import GUI.*;
import SQL.SQLConnection;

public class LoginController {
	private LoginGUI login;
	
	public LoginController(LoginGUI loginFrame) {
		this.login = loginFrame;
		login.addLoginListener(new LoginBtn());
	}
	
	public boolean get(String user, String pass) throws Exception {
		try {
			SQLConnection sql = new SQLConnection();
			Connection con =  sql.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT username, parola, Functie_idFunctie FROM utilizator_angajat;");

			ResultSet result = statement.executeQuery();
			while(result.next()) {
				if(result.getString("username").equals(user) && result.getString("parola").equals(pass)) {
					int nFunctie = result.getInt("Functie_idFunctie");
					if(nFunctie == 1) {
						ResurseUmaneGUI resurseUmane = new ResurseUmaneGUI();
						ResurseUmaneController resurseUmaneController = new ResurseUmaneController(resurseUmane);
					}
					else if(nFunctie == 2) {
						EconomicGUI economic = new EconomicGUI();
						EconomicController economicController =new EconomicController(economic);
					}
					else if(nFunctie == 3) {
						ReceptionerGUI receptioner = new ReceptionerGUI();
						ReceptionerController recepController = new ReceptionerController(receptioner);
					}
					else if(nFunctie == 4) {
						AsistentMedicalGUI asistent = new AsistentMedicalGUI();
						AsistentController asisController = new AsistentController(asistent);
					}
					else if(nFunctie == 5) {
						MedicGUI medic = new MedicGUI();
						MedicController medController = new MedicController(medic, user, pass);
					}
					else  if(nFunctie == 6){
						AdminGUI adminGUI = new AdminGUI();
						AdminController  adminController = new AdminController(adminGUI);
					}
					con.close();
					return true;
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	class LoginBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if (get(login.getUsername(), login.getPassword()) == true) {
					login.exit();
					System.out.println("User si parola corecte");
				}
				else {
					System.out.println("Date de logare incorecte");
					login.afiseazaMsg("Datele de logare sunt incorecte.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

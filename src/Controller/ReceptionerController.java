package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import GUI.ReceptionerGUI;
import SQL.SQLConnection;

public class ReceptionerController {
	private ReceptionerGUI recep;
	private String bonFiscal;
	public ReceptionerController (ReceptionerGUI receptioner) throws Exception {
		this.recep = receptioner;
		recep.addInregistrareListener(new InregistrareBtn());
		recep.addMedicListener(new MedicCombo());
		recep.addProgramareListener(new ProgramareBtn());
		recep.addLogOutListener(new LogOut());
		recep.addBonFiscal(new BonFiscal());
		cautaMedici();
		recep.deleteAllItemsServiciu();
		cautaServicii(recep.getThisItemMedic());
		cautaCNP();
		bonFiscal = "";
	}
	
	public void inregistrarePacient(String nume, String prenume, String cnp) throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		
		PreparedStatement statement = con.prepareStatement("SELECT CNP FROM pacient;");
		ResultSet result = statement.executeQuery();
		
		boolean exista = false;
		while(result.next()) {
			if(result.getString("CNP").equals(cnp)) {
				exista = true;
			}
		}
		
		if(exista == false)
			recep.afiseazaMsg("Pacientul a fost inregistrat cu succes.", exista);
		else {
			recep.afiseazaMsg("Pacientul introdus exista deja in baza de date", exista);
			con.close();
			return;
		}	
		CallableStatement cStmt = con.prepareCall("{call ADAUGARE_PACIENT(?, ?, ?)}");
		cStmt.setString(1, cnp);
		cStmt.setString(2, nume);
		cStmt.setString(3, prenume);
		cStmt.execute();
		con.close();
	}
	
	public void programarePacient() throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		PreparedStatement statement = con.prepareStatement("SELECT id_Serviciu_Medical, Nume_serviciu, pret FROM serviciu_medical;");
		ResultSet result = statement.executeQuery();
		int nServiciu = 0;
		int pret = 0;
		while(result.next()) {
			if(result.getString("Nume_serviciu").equals(recep.getThisItemServiciu())) {
				nServiciu = result.getInt("id_Serviciu_Medical");
				pret = result.getInt("pret");
				break;
			}
		}
		
		statement = con.prepareStatement("SELECT CNP, Nume, Prenume FROM utilizator_angajat;");
		
		result = statement.executeQuery();
		String cnp = new String();
		
		while(result.next()) {
			String strTmp = result.getString("Nume") + " " + result.getString("Prenume");
			if(strTmp.equals(recep.getThisItemMedic())) {
				cnp = result.getString("CNP");
			}
		}
		
		CallableStatement cStmt = con.prepareCall("{call ADAUGARE_PROGRAMARE(?, ?, ?, ?)}");
		cStmt.setString(1, recep.getThisItemCNP());
		cStmt.setString(2, cnp);
		cStmt.setInt(3, nServiciu);
		cStmt.setString(4, recep.getData());
		
		bonFiscal = "<html>CNP Pacient: " + recep.getThisItemCNP() + "<br>CNP Medic: " + cnp + "<br>Numar serviciu: " + Integer.toString(nServiciu) + "<br>Data programare: " + recep.getData() + "<br>Pret: " + Integer.toString(pret) + "</html>";
		
		cStmt.execute();
		recep.afiseazaMsg("Programarea s-a realizat cu succes.", false);
		con.close();
	}
	
	public void cautaMedici() throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		PreparedStatement statement = con.prepareStatement("SELECT Nume, Prenume, Functie_idFunctie FROM utilizator_angajat;");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			if(result.getInt("Functie_idFunctie") == 5)
				recep.addItemMedic(result.getString("Nume") + " " + result.getString("Prenume"));
		}
		con.close();
	}
	
	public void cautaCNP() throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		PreparedStatement statement = con.prepareStatement("SELECT CNP FROM pacient;");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			recep.addItemCNP(result.getString("CNP"));
		}
		con.close();
	}
	
	public void cautaServicii(String medic) throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		PreparedStatement statement = con.prepareStatement("SELECT Nume, Prenume, CNP FROM utilizator_angajat;");
		
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			String strTmp = result.getString("Nume") + " " + result.getString("Prenume");
			if(strTmp.equals(medic)) {
				PreparedStatement statementSec = con.prepareStatement("SELECT utilizator_angajat_CNP, Specializare_idSpecializare FROM medic;");
				ResultSet resultSec = statementSec.executeQuery();
				while(resultSec.next()) {
					if(resultSec.getString("utilizator_angajat_CNP").equals(result.getString("CNP"))) {
						PreparedStatement statementThr = con.prepareStatement("SELECT specializare_idSpecializare, Nume_serviciu FROM serviciu_medical;");
						ResultSet resultThr = statementThr.executeQuery();
						while(resultThr.next()) {
							if(resultThr.getString("specializare_idSpecializare").equals(resultSec.getString("Specializare_idSpecializare"))) {
								recep.addItemServiciu(resultThr.getString("Nume_serviciu"));
							}
						}
					}
				}
			}
		}
		con.close();
	}
	
	class MedicCombo implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			recep.deleteAllItemsServiciu();
			if(arg0.getStateChange() == ItemEvent.SELECTED) {
				try {
					cautaServicii(recep.getThisItemMedic());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class InregistrareBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(recep.getNumeInregistrare().isEmpty() == false && recep.getPrenumeInregistrare().isEmpty() == false && recep.getCNPIntegistrare().isEmpty() == false) {
				try {
					if(recep.getCNPIntegistrare().length() <= 13 && recep.getNumeInregistrare().length() <= 45 && recep.getPrenumeInregistrare().length() <= 45)
					{
						inregistrarePacient(recep.getNumeInregistrare(), recep.getPrenumeInregistrare(), recep.getCNPIntegistrare());
						System.out.print("Inregistrarea a avut loc cu succes.");
						recep.setDefaultText();
					}
					recep.deleteAllItemsCNP();
					cautaCNP();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class ProgramareBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if(recep.verificaData(recep.getData()) == true)
					programarePacient();
				else
					recep.afiseazaMsg("Formatul de timp introdus este incorect.", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class LogOut implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			recep.exit();
		}
	}
	
	class BonFiscal implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(bonFiscal.isEmpty() == false)
				recep.afiseazaMsg(bonFiscal, false);
			else
				recep.afiseazaMsg("Nu ati efectual nicio programare.", true);
		}
		
	}
}

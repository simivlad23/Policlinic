package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import GUI.AsistentMedicalGUI;
import SQL.SQLConnection;

public class AsistentController {
	private AsistentMedicalGUI asis;
	private int idProgramare;
	
	public AsistentController(AsistentMedicalGUI asistent) throws Exception {
		this.asis = asistent;
		incarcaPacienti();
		asis.addSelectList(new SelectRow());
		asis.addActionListCompletare(new CompletareRaport());
		asis.addActionExit(new LogOutAsis());
	}
	
	public void incarcaPacienti() throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		
		PreparedStatement statement = con.prepareStatement("SELECT CNP, Nume, Prenume FROM pacient;");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			PreparedStatement statementScd = con.prepareStatement("SELECT Pacient_CNP, id_Programare, id_serviciu FROM programare;");
			
			ResultSet resultScd = statementScd.executeQuery();
		
			boolean exista = false;
			
			while(resultScd.next()) {
				
				if(resultScd.getString("Pacient_CNP").equals(result.getString("CNP")))
				{
					exista = true;
					PreparedStatement statementTrd = con.prepareStatement("SELECT Nume_serviciu, id_Serviciu_Medical FROM serviciu_medical;");
					
					ResultSet resultTrd = statementTrd.executeQuery();
					
					while(resultTrd.next()) {
						if(resultTrd.getInt("id_Serviciu_Medical") == resultScd.getInt("id_serviciu")) {
							
							PreparedStatement statementFor = con.prepareStatement("SELECT programare_id_Programare from consultatie;");
							ResultSet resultFor = statementFor.executeQuery();
							
							boolean existaSec = false;
							
							while(resultFor.next()) {
								if(resultFor.getInt("programare_id_Programare") == resultScd.getInt("id_Programare")) {
									existaSec = true;
									break;
								}
							}
							if(existaSec == false)
								asis.insertRowPac(result.getString("CNP"), result.getString("Nume"), result.getString("Prenume"), resultTrd.getString("Nume_serviciu"), resultScd.getInt("id_Programare"), "NU");
							else
								asis.insertRowPac(result.getString("CNP"), result.getString("Nume"), result.getString("Prenume"), resultTrd.getString("Nume_serviciu"), resultScd.getInt("id_Programare"), "DA");
							
							exista = true;
						}
					}
				}
			}
			if(exista == false)
				asis.insertRowPac(result.getString("CNP"), result.getString("Nume"), result.getString("Prenume"), "", -1, "Invalid");
		}
		con.close();
	}
	
	public void adaugaConsultatie(String simptome, String investigatii, String diagnostic, String tratament) throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		
		CallableStatement cStmt = con.prepareCall("{call ADAUGARE_CONSULTATIE(?, ?, ?, ?, ?)}");
		PreparedStatement statement = con.prepareStatement("SELECT programare_id_Programare FROM consultatie;");
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			if(result.getInt("programare_id_Programare") == idProgramare) {
				con.close();
				asis.afiseazaMsg("Pacientul are deja consultatia efectuata.", true);
				return;
			}
		}
		cStmt.setInt(1, idProgramare);
		cStmt.setString(2, simptome);
		cStmt.setString(3, investigatii);
		cStmt.setString(4, diagnostic);
		cStmt.setString(5, tratament);
		
		asis.afiseazaMsg("Raportul a fost realizat cu succes.", false);
		cStmt.execute();
		con.close();
	}
	class SelectRow implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			idProgramare = asis.getIdProgramare(asis.getLinieSel());
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	class CompletareRaport implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(asis.getSimptome().isEmpty() == false && asis.getInvestigatii().isEmpty() == false && asis.getDiagnostic().isEmpty() == false && asis.getTratament().isEmpty() == false) {
				try {
					adaugaConsultatie(asis.getSimptome(), asis.getInvestigatii(), asis.getDiagnostic(), asis.getTratament());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}	
	}
	class LogOutAsis implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			asis.exit();
		}
	}
}

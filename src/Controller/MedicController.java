package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import GUI.MedicGUI;
import SQL.SQLConnection;

public class MedicController {
	private MedicGUI medic;
	private String user;
	private String pass;
	
	public MedicController(MedicGUI med, String username, String password) throws Exception {
		this.medic = med;
		this.user = username;
		this.pass = password;
		incarcaPacienti();
		medic.addMousePacientiTable(new SelectPacientRow());
		medic.addLogOutListener(new LogOutMedic());
	}
	
	public void incarcaPacienti() throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String data = ft.format(dNow);
		
		
		PreparedStatement statementTrd = con.prepareStatement("SELECT CNP, username, parola FROM utilizator_angajat;");
		
		ResultSet resultTrd = statementTrd.executeQuery();
		
		while(resultTrd.next()) {
			if(resultTrd.getString("username").equals(user) && resultTrd.getString("parola").equals(pass)) {
				
				PreparedStatement statement = con.prepareStatement("SELECT CNP, Nume, Prenume FROM pacient;");
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					PreparedStatement statementScd = con.prepareStatement("SELECT Pacient_CNP, data_programare, medic_utilizator_angajat_CNP FROM programare;");
					ResultSet resultScd = statementScd.executeQuery();
					
					while(resultScd.next()) {
						if(resultScd.getString("Pacient_CNP").equals(result.getString("CNP")) && resultTrd.getString("CNP").equals(resultScd.getString("medic_utilizator_angajat_CNP"))) {
							String dataTable = resultScd.getString("data_programare").substring(0, 10);
							if(data.equals(dataTable)) {		
								medic.insertRowPac(result.getString("CNP"), result.getString("Nume"), result.getString("Prenume"));
							}
						}
					}
				}
			}
		}		
		con.close();
	}
	
	public void incarcaIstoric(String cnp) throws Exception {
		SQLConnection sql = new SQLConnection();
		Connection con =  sql.getConnection();
		PreparedStatement statement = con.prepareStatement("SELECT id_Programare, Pacient_CNP FROM programare;");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			if(result.getString("Pacient_CNP").equals(cnp)) {
				PreparedStatement statementScd = con.prepareStatement("SELECT programare_id_Programare, Simtome, Investigatii, Diagnostic, Tratament FROM consultatie;");
				ResultSet resultScd = statementScd.executeQuery();
				
				while(resultScd.next()) {
					if(resultScd.getInt("programare_id_Programare") == result.getInt("id_Programare")) {
						medic.insertRowIsto(resultScd.getString("Simtome"), resultScd.getString("Investigatii"), resultScd.getString("Diagnostic"), resultScd.getString("Tratament"));
					}
				}
			}
		}
	}
	
	class SelectPacientRow implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			medic.deleteAllItemsIsto();
			try {
				incarcaIstoric(medic.getCNPPacient(medic.getSelectedRowPac()));
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	
	class LogOutMedic implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			medic.exit();
		}
	}
}

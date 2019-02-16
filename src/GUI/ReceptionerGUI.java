package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import Controller.LoginController;

public class ReceptionerGUI {
	JFrame receptionerFrame = new JFrame("Receptioner");
	JPanel receptionerPanel = new JPanel();
	JLabel inregistrareLabel = new JLabel("Inregistrare pacient:", SwingConstants.CENTER);
	JLabel numeLabel = new JLabel("Nume: ", SwingConstants.RIGHT);
	JLabel prenumeLabel = new JLabel("Prenume: ", SwingConstants.RIGHT);
	JLabel cnpLabel = new JLabel("CNP: ", SwingConstants.RIGHT);
	JTextField numeText = new JTextField();
	JTextField prenumeText = new JTextField();
	JTextField cnpText = new JTextField();
	JButton inregistrareButton = new JButton("Inregistrare");
	JLabel programarePacient = new JLabel("Programare pacient:", SwingConstants.CENTER);
	JLabel cnpProgramare = new JLabel("CNP: ", SwingConstants.RIGHT);
	JLabel medicLabel = new JLabel("Medic: ", SwingConstants.RIGHT);
	JLabel serviciuLabel = new JLabel("Serviciu: ", SwingConstants.RIGHT);
	JLabel dataLabel = new JLabel("Data/Ora: ", SwingConstants.RIGHT);
	JComboBox<String> cnpCombo = new JComboBox<String>();
	JComboBox<String> medicCombo = new JComboBox<String>();
	JComboBox<String> serviciuCombo = new JComboBox<String>();
	JTextField dataText = new JTextField();
	JButton programareButton = new JButton("Programare");
	JLabel emitereBonLabel = new JLabel("Emitere bon fiscal:", SwingConstants.CENTER);
	JButton emitereBonButton = new JButton("Emitere bon fiscal");
	JButton logOutButton = new JButton("LogOut");
	
	public ReceptionerGUI() {
		
		
		receptionerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		receptionerFrame.setSize(330, 490); 
		receptionerFrame.setLocationRelativeTo(null);
		
		receptionerPanel.setLayout(null);
		
		inregistrareLabel.setSize(120, 20);
		inregistrareLabel.setLocation(105, 20);
		
		numeLabel.setSize(60, 20);
		numeLabel.setLocation(30, 50);
		
		prenumeLabel.setSize(60, 20);
		prenumeLabel.setLocation(30, 80);
		
		cnpLabel.setSize(60, 20);
		cnpLabel.setLocation(30, 110);
		
		numeText.setSize(150, 20);
		numeText.setLocation(100, 50);
		
		prenumeText.setSize(150, 20);
		prenumeText.setLocation(100, 80);
		
		cnpText.setSize(150, 20);
		cnpText.setLocation(100, 110);
		
		inregistrareButton.setSize(120, 20);
		inregistrareButton.setLocation(105, 140);
		
		programarePacient.setSize(120, 20);
		programarePacient.setLocation(105, 170);
		
		cnpProgramare.setSize(60, 20);
		cnpProgramare.setLocation(30, 200);
		
		medicLabel.setSize(60, 20);
		medicLabel.setLocation(30, 230);
		
		serviciuLabel.setSize(60, 20);
		serviciuLabel.setLocation(30, 260);
		
		dataLabel.setSize(60, 20);
		dataLabel.setLocation(30, 290);
		
		cnpCombo.setSize(150, 20);
		cnpCombo.setLocation(100, 200);
		
		medicCombo.setSize(150, 20);
		medicCombo.setLocation(100, 230);
		
		serviciuCombo.setSize(150, 20);
		serviciuCombo.setLocation(100, 260);
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		dataText.setSize(150, 20);
		dataText.setLocation(100, 290);
		dataText.setText(ft.format(dNow));
	
		programareButton.setSize(120, 20);
		programareButton.setLocation(105, 320);
		
		emitereBonLabel.setSize(120, 20);
		emitereBonLabel.setLocation(105, 350);
		
		emitereBonButton.setSize(140, 20);
		emitereBonButton.setLocation(95, 380);
		
		logOutButton.setSize(80, 20);
		logOutButton.setLocation(220, 420);
		
		receptionerPanel.add(inregistrareLabel);
		receptionerPanel.add(numeLabel);
		receptionerPanel.add(prenumeLabel);
		receptionerPanel.add(cnpLabel);
		receptionerPanel.add(numeText);
		receptionerPanel.add(prenumeText);
		receptionerPanel.add(cnpText);
		receptionerPanel.add(inregistrareButton);
		receptionerPanel.add(programarePacient);
		receptionerPanel.add(cnpProgramare);
		receptionerPanel.add(medicLabel);
		receptionerPanel.add(serviciuLabel);
		receptionerPanel.add(dataLabel);
		receptionerPanel.add(cnpCombo);
		receptionerPanel.add(medicCombo);
		receptionerPanel.add(serviciuCombo);
		receptionerPanel.add(dataText);
		receptionerPanel.add(programareButton);
		receptionerPanel.add(emitereBonLabel);
		receptionerPanel.add(emitereBonButton);
		receptionerPanel.add(logOutButton);
		
		receptionerFrame.setContentPane(receptionerPanel);
		receptionerFrame.setVisible(true);
	}
	
	public boolean verificaData(String data) {
		if(data.charAt(4) != '-' || data.charAt(7) != '-')
			return false;
		if(data.charAt(10) != ' ')
			return false;
		if(data.charAt(13) != ':' || data.charAt(16) != ':')
			return false;
		if(Character.isDigit(data.charAt(0)) == false || Character.isDigit(data.charAt(1)) == false || Character.isDigit(data.charAt(2)) == false || Character.isDigit(data.charAt(3)) == false)
			return false;
		if(Character.isDigit(data.charAt(5)) == false || Character.isDigit(data.charAt(6)) == false)
			return false;
		if(Character.isDigit(data.charAt(8)) == false || Character.isDigit(data.charAt(9)) == false)
			return false;
		if(Character.isDigit(data.charAt(11)) == false || Character.isDigit(data.charAt(12)) == false)
			return false;
		if(Character.isDigit(data.charAt(14)) == false || Character.isDigit(data.charAt(15)) == false)
			return false;
		if(Character.isDigit(data.charAt(17)) == false || Character.isDigit(data.charAt(18)) == false)
			return false;
		return true;
	}
	
	public String getNumeInregistrare() {
		return numeText.getText();
	}
	
	public String getPrenumeInregistrare() {
		return prenumeText.getText();
	}
	
	public String getCNPIntegistrare() {
		return cnpText.getText();
	}
	
	public String getThisItemMedic() {
		return (String) medicCombo.getSelectedItem();
	}
	
	public String getThisItemCNP() {
		return (String) cnpCombo.getSelectedItem();
	}
	
	public String getThisItemServiciu() {
		return (String) serviciuCombo.getSelectedItem();
	}
	
	public String getData() {
		return dataText.getText();
	}
	
	public void setDefaultText() {
		numeText.setText("");
		prenumeText.setText("");
		cnpText.setText("");
	}
	
	public void addInregistrareListener(ActionListener listener) {
		inregistrareButton.addActionListener(listener);
	}
	
	public void addBonFiscal(ActionListener listener) {
		emitereBonButton.addActionListener(listener);
	}
	
	public void addProgramareListener(ActionListener listener) {
		programareButton.addActionListener(listener);
	}
	
	public void addMedicListener(ItemListener listener) {
		medicCombo.addItemListener(listener);
	}
	
	public void addLogOutListener(ActionListener listener) {
		logOutButton.addActionListener(listener);
	}
	
	public void addItemMedic(String numeMedic) {
		medicCombo.addItem(numeMedic);
	}
	
	public void addItemServiciu(String serviciu) {
		serviciuCombo.addItem(serviciu);
	}
	
	public void addItemCNP(String cnp) {
		cnpCombo.addItem(cnp);
	}
	
	public void deleteAllItemsServiciu() {
		serviciuCombo.removeAllItems();
	}
	
	public void deleteAllItemsCNP() {
		cnpCombo.removeAllItems();
	}
	
	public void exit() {
		receptionerFrame.dispose();
		LoginGUI loginFrame = new LoginGUI();
		LoginController login = new LoginController(loginFrame);
	}
	
	public void afiseazaMsg(String msg, boolean ok) {
		if(ok == true)
			JOptionPane.showMessageDialog(receptionerFrame, msg, "Warning", JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(receptionerFrame, msg, "Succes", JOptionPane.PLAIN_MESSAGE);
	}
}

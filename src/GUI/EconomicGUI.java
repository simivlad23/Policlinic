package GUI;

import javax.swing.*;

import Controller.LoginController;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class EconomicGUI {

    JFrame economicFrame = new JFrame("Economic");
    JPanel economicPanel = new JPanel();
    JLabel rezultatLabel = new JLabel("Rezultat:", SwingConstants.RIGHT);
    JLabel angajatLabel = new JLabel("Angajat CNP:", SwingConstants.RIGHT);
    JLabel LunaLabel = new JLabel("Luna:", SwingConstants.RIGHT);
    JLabel MedicLabel = new JLabel("Medic:", SwingConstants.RIGHT);
    JTextField rezultatTextField = new JTextField();
    JTextField angajatTextField = new JTextField();
    JComboBox<String> lunaCombo = new JComboBox<String>();
    JComboBox<String> medicCombo = new JComboBox<String>();

    JButton raportLunaButton = new JButton("Profit/Luna Institutie");
    JButton raportLunaMedicButton = new JButton("Profit/Luna Medic");
    JButton salariuButton = new JButton("Salar angajat");
    JButton logOutButton = new JButton("LogOut");
    
    public EconomicGUI() {

        economicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        economicFrame.setSize(310, 310);
        economicFrame.setLocationRelativeTo(null);

        economicPanel.setLayout(null);

        rezultatLabel.setSize(80, 20);
        rezultatLabel.setLocation(10, 20);

        rezultatTextField.setSize(150, 20);
        rezultatTextField.setLocation(100, 20);
        rezultatTextField.setEditable(false); 

        angajatLabel.setSize(80, 20);
        angajatLabel.setLocation(10, 50);

        angajatTextField.setSize(150, 20);
        angajatTextField.setLocation(100, 50);

        LunaLabel.setSize(80, 20);
        LunaLabel.setLocation(10, 80);

        lunaCombo.setSize(150, 20);
        lunaCombo.setLocation(100, 80);

        MedicLabel.setSize(80, 20);
        MedicLabel.setLocation(10, 110);

        medicCombo.setSize(150, 20);
        medicCombo.setLocation(100, 110);

        raportLunaButton.setSize(150, 20);
        raportLunaButton.setLocation(100, 140);

        raportLunaMedicButton.setSize(150, 20);
        raportLunaMedicButton.setLocation(100, 170);

        salariuButton.setSize(150, 20);
        salariuButton.setLocation(100, 200);

        logOutButton.setSize(80, 20);
        logOutButton.setLocation(200, 240);

        economicPanel.add(rezultatLabel);
        economicPanel.add(rezultatTextField);
        economicPanel.add(angajatLabel);
        economicPanel.add(angajatTextField);
        economicPanel.add(LunaLabel);
        economicPanel.add(lunaCombo);
        economicPanel.add(MedicLabel);
        economicPanel.add(medicCombo);
        economicPanel.add(raportLunaButton);
        economicPanel.add(raportLunaMedicButton);
        economicPanel.add(salariuButton);
        economicPanel.add(logOutButton);

        economicFrame.add(economicPanel);
        economicFrame.setVisible(true);
    }

    public void setRezultatTextField(String t) {
        rezultatTextField.setText(t);
    }


    public String getCnp() {
        return angajatTextField.getText();
    }

    public int getLuna() {
        return lunaCombo.getSelectedIndex() + 1;
    }

    public String getMedic() {
        return (String) medicCombo.getSelectedItem();
    }

    public void addRaportLunaListener(ActionListener listener) {
        raportLunaButton.addActionListener(listener);
    }

    public void addRaportLunaMedicListener(ActionListener listener) {
        raportLunaMedicButton.addActionListener(listener);
    }

    public void addSalariuListener(ActionListener listener) {
        salariuButton.addActionListener(listener);
    }

    public void addItemLuna(String luna) {
        lunaCombo.addItem(luna);
    }

    public void addItemMedic(String medic) {
        medicCombo.addItem(medic);
    }
    
    public void addLogOutListener(ActionListener listener) {
		logOutButton.addActionListener(listener);
	}
    
    public void exit() {
    	economicFrame.dispose();
		LoginGUI loginFrame = new LoginGUI();
		LoginController login = new LoginController(loginFrame);
	}


}

package GUI;

import javax.swing.*;

import Controller.LoginController;

import java.awt.*;
import java.awt.event.ActionListener;

public class ResurseUmaneGUI {

    JFrame resurseUmaneFrame = new JFrame("Resurse umane");
    JPanel resurseUmanePanel = new JPanel();
    JLabel cautareLabel = new JLabel("Cautare utilizator:", SwingConstants.RIGHT);
    JTextField cautareTextField = new JTextField();
    JButton cautareButton =  new JButton("Cautare");
    JButton listareButton = new JButton("Listare angajati");
    JButton logOutButton = new JButton("LogOut");
    
    public  ResurseUmaneGUI(){

        resurseUmaneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resurseUmaneFrame.setSize(605, 130);
        resurseUmaneFrame.setLocationRelativeTo(null);
      
        resurseUmanePanel.setLayout(null);

        cautareLabel.setSize(100, 20);
        cautareLabel.setLocation(20, 20);

        cautareTextField.setSize(160, 20);
        cautareTextField.setLocation(130, 20);

        cautareButton.setSize(130, 20);
        cautareButton.setLocation(300, 20);

        listareButton.setSize(130,20);
        listareButton.setLocation(440, 20);
        
        logOutButton.setSize(80,20);
        logOutButton.setLocation(490, 60);

        resurseUmanePanel.add(cautareLabel);
        resurseUmanePanel.add(cautareTextField);
        resurseUmanePanel.add(cautareButton);
        resurseUmanePanel.add(listareButton);
        resurseUmanePanel.add(logOutButton);

        resurseUmaneFrame.setContentPane(resurseUmanePanel);
        resurseUmaneFrame.setVisible(true);
    }

    public String getCampCautare(){

        return cautareTextField.getText();

    }

    public void addListatareListener(ActionListener listener) {
        listareButton.addActionListener(listener);
    }
    public void addCautareListener(ActionListener listener) {
        cautareButton.addActionListener(listener);
    }
    
    public void addLogOutListener(ActionListener listener) {
		logOutButton.addActionListener(listener);
	}
    
    public void exit() {
    	resurseUmaneFrame.dispose();
		LoginGUI loginFrame = new LoginGUI();
		LoginController login = new LoginController(loginFrame);
	}

}

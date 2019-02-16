package GUI;

import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginGUI {
	JFrame loginFrame = new JFrame("Login");
	JPanel loginPanel = new JPanel();
	JLabel usernameLabel = new JLabel("Username: ");
	JLabel passwordLabel = new JLabel("Password: ");
	JTextField usernameTextField = new JTextField();
	JPasswordField passwordTextField = new JPasswordField();
	//JButton registerButton = new JButton("Register");
	JButton loginButton = new JButton("Login");

	public LoginGUI() {
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(400, 400);
		loginFrame.setLocationRelativeTo(null);
		
		loginPanel.setLayout(null);
		
		usernameLabel.setSize(65, 20);
		usernameLabel.setLocation(50, 100);
		
		passwordLabel.setSize(65, 20);
		passwordLabel.setLocation(50, 140);
		
		usernameTextField.setSize(205, 20);
		usernameTextField.setLocation(125, 100);
		
		passwordTextField.setSize(205, 20);
		passwordTextField.setLocation(125, 140);
		
		loginButton.setSize(90, 20);
		loginButton.setLocation(125, 180);
		
		//registerButton.setSize(90, 20);
		//registerButton.setLocation(240, 180);
		
		loginPanel.add(usernameLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(usernameTextField);
		loginPanel.add(passwordTextField);
		loginPanel.add(loginButton);
		//loginPanel.add(registerButton);
		loginFrame.setContentPane(loginPanel);
		loginFrame.setVisible(true);
	}

	public String getUsername() {
		return usernameTextField.getText();
	}
	
	public String getPassword() {
		String password = String.valueOf(passwordTextField.getPassword());
		return password;
	}
	
	public void addLoginListener(ActionListener listener) {
		loginButton.addActionListener(listener);
	}
	
	public void exit() {
		loginFrame.dispose();
	}
	
	public void afiseazaMsg(String msg) {
		JOptionPane.showMessageDialog(loginFrame, msg, "Warning", JOptionPane.WARNING_MESSAGE);
	}
}

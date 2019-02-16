package GUI;

import Controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class AdminGUI {

    // Label Utilizator angajat

    JFrame adminFrame = new JFrame("Administrator");
    JPanel adminPanel = new JPanel();
    JLabel cnpL = new JLabel("CNP");
    JLabel unitateL = new JLabel("UNITATE");
    JLabel usernameL = new JLabel("USERNAME");
    JLabel passwordL = new JLabel("PASSWORD");
    JLabel numeL = new JLabel("Nume");
    JLabel prenumeL = new JLabel("Prenume");
    JLabel adresaL = new JLabel("Adresa");
    JLabel telefonL = new JLabel("Telefon");
    JLabel emailL = new JLabel("Email");
    JLabel ibanL = new JLabel("IBAN");
    JLabel SalariuL = new JLabel("Salariu");
    JLabel numerOreL = new JLabel("Numar Ore");


    // Label Medic

    JLabel specializareL = new JLabel("Specializare");
    JLabel gradL = new JLabel("Grad");
    JLabel procentL = new JLabel("Procent Servicii");
    JLabel titluL = new JLabel("Titlu");
    JLabel postDidacticL = new JLabel("Post Didactic");


    // Label Asistent
    JLabel tipAsistentL = new JLabel("Tip Asistent");
    JLabel gradAsistentL = new JLabel("Grad Asistent");


    JTextField cnpTF = new JTextField();
    JTextField idUnitateTF = new JTextField();
    JTextField usernameTf = new JTextField();
    JTextField passTF = new JTextField();
    JTextField numeTF = new JTextField();
    JTextField prenumeTF = new JTextField();



    JTextField adresaTF = new JTextField();
    JTextField telefonTF = new JTextField();
    JTextField emailTF = new JTextField();
    JTextField ibanTF = new JTextField();
    JTextField salariuTF = new JTextField();
    JTextField nrOreTF = new JTextField();


    JTextField specializareTF = new JTextField();
    JTextField gradMedTF = new JTextField();
    JTextField procentTF = new JTextField();
    JTextField titluTF = new JTextField();
    JTextField postDidacticTF = new JTextField();


    JTextField tipAsistentTF = new JTextField();
    JTextField gradAsistentTF = new JTextField();

    JButton regMedicBtn = new JButton("Inregistrare Medic");
    JButton regAsistentBtn = new JButton("Inregistrare Asistent");
    JButton logOutButton = new JButton("Log Out");


    public AdminGUI() {


        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(500, 380);
        adminFrame.setLocationRelativeTo(null);
        adminPanel.setLayout(null);

        //
        cnpL.setSize(80,20);
        cnpL.setLocation(10,20);

        unitateL.setSize(80,20);
        unitateL.setLocation(10, 40);

        usernameL.setSize(80, 20);
        usernameL.setLocation(10, 60);

        passwordL.setSize(80,20);
        passwordL.setLocation(10,80);

        numeL.setSize(80,20);
        numeL.setLocation(10,100);

        prenumeL.setSize(80,20);
        prenumeL.setLocation(10,120);

        adresaL.setSize(80,20);
        adresaL.setLocation(10,140);

        telefonL.setSize(80,20);
        telefonL.setLocation(10,160);

        emailL.setSize(80,20);
        emailL.setLocation(10,180);

        ibanL.setSize(80,20);
        ibanL.setLocation(10,200);

        SalariuL.setSize(80,20);
        SalariuL.setLocation(10,220);

        numerOreL.setSize(80,20);
        numerOreL.setLocation(10,240);

        cnpTF.setSize(100,20);
        cnpTF.setLocation(110,20);

        idUnitateTF.setSize(100,20);
        idUnitateTF.setLocation(110,40);

        usernameTf.setSize(100,20);
        usernameTf.setLocation(110,60);

        passTF.setSize(100,20);
        passTF.setLocation(110,80);

        numeTF.setSize(100,20);
        numeTF.setLocation(110,100);

        prenumeTF.setSize(100,20);
        prenumeTF.setLocation(110,120);

        adresaTF.setSize(100,20);
        adresaTF.setLocation(110,140);

        telefonTF.setSize(100,20);
        telefonTF.setLocation(110,160);

        emailTF.setSize(100,20);
        emailTF.setLocation(110,180);

        ibanTF.setSize(100,20);
        ibanTF.setLocation(110,200);

        salariuTF.setSize(100,20);
        salariuTF.setLocation(110,220);

        nrOreTF.setSize(100,20);
        nrOreTF.setLocation(110,240);

        specializareL.setSize(80,20);
        specializareL.setLocation(250,20);

        gradL.setSize(80,20);
        gradL.setLocation(250,40);

        procentL.setSize(80,20);
        procentL.setLocation(250,60);

        titluL.setSize(80,20);
        titluL.setLocation(250,80);

        postDidacticL.setSize(80,20);
        postDidacticL.setLocation(250,100);

        tipAsistentL.setSize(80,20);
        tipAsistentL.setLocation(250,160);

        gradAsistentL.setSize(80,20);
        gradAsistentL.setLocation(250,180);

        specializareTF.setSize(100,20);
        specializareTF.setLocation(360,20);

        gradMedTF.setSize(100,20);
        gradMedTF.setLocation(360,40);

        procentTF.setSize(100,20);
        procentTF.setLocation(360,60);

        titluTF.setSize(100,20);
        titluTF.setLocation(360,80);

        postDidacticTF.setSize(100,20);
        postDidacticTF.setLocation(360,100);

        tipAsistentTF.setSize(100,20);
        tipAsistentTF.setLocation(360,160);

        gradAsistentTF.setSize(100,20);
        gradAsistentTF.setLocation(360,180);

        regMedicBtn.setSize(150,30);
        regMedicBtn.setLocation(10,280);

        regAsistentBtn.setSize(200,30);
        regAsistentBtn.setLocation(170,280);

        logOutButton.setLocation(380,290);
        logOutButton.setSize(100,20);

        //





        adminPanel.add(cnpL);
        adminPanel.add(unitateL);
        adminPanel.add(usernameL);
        adminPanel.add(passwordL);
        adminPanel.add(numeL);
        adminPanel.add(prenumeL);
        adminPanel.add(adresaL);
        adminPanel.add(telefonL);
        adminPanel.add(emailL);
        adminPanel.add(ibanL);
        adminPanel.add(SalariuL);
        adminPanel.add(numerOreL);
        adminPanel.add(specializareL);
        adminPanel.add(gradL);
        adminPanel.add(procentL);
        adminPanel.add(titluL);
        adminPanel.add(postDidacticL);
        adminPanel.add(tipAsistentL);
        adminPanel.add(gradAsistentL);
        adminPanel.add(cnpTF);
        adminPanel.add(usernameTf);
        adminPanel.add(idUnitateTF);
        adminPanel.add(passTF);
        adminPanel.add(numeTF);
        adminPanel.add(prenumeTF);
        adminPanel.add(adresaTF);
        adminPanel.add(telefonTF);
        adminPanel.add(emailTF);
        adminPanel.add(ibanTF);
        adminPanel.add(salariuTF);
        adminPanel.add(nrOreTF);
        adminPanel.add(salariuTF);
        adminPanel.add(gradMedTF);
        adminPanel.add(procentTF);
        adminPanel.add(specializareTF);
        adminPanel.add(titluTF);
        adminPanel.add(postDidacticTF);
        adminPanel.add(tipAsistentTF);
        adminPanel.add(gradAsistentTF);
        adminPanel.add(regAsistentBtn);
        adminPanel.add(logOutButton);
        adminPanel.add(regMedicBtn);

        adminFrame.add(adminPanel);
        adminFrame.setVisible(true);



        // setam pozitia in panel si adugam in frame toate componentele

    }

    public void addMedicListener(ActionListener listener) {
        regMedicBtn.addActionListener(listener);
    }

    public void addAsistentListener(ActionListener listener) {
        regAsistentBtn.addActionListener(listener);


    }
    public void addLogOutListener(ActionListener listener) {
        logOutButton.addActionListener(listener);
    }
    public void afiseazaMsg(String msg, boolean ok) {
        if(ok == true)
            JOptionPane.showMessageDialog(adminFrame, msg, "Warning", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(adminFrame, msg, "Succes", JOptionPane.PLAIN_MESSAGE);
    }
    public void exit() {
        adminFrame.dispose();
        LoginGUI loginFrame = new LoginGUI();
        LoginController login = new LoginController(loginFrame);
    }
    public void setNullText() {
        cnpTF.setText("");
        idUnitateTF.setText("");
        usernameTf.setText("");
        passTF.setText("");
        numeTF.setText("");
        prenumeTF.setText("");
        adresaTF.setText("");
        telefonTF.setText("");
        emailTF.setText("");
        ibanTF.setText("");
        salariuTF.setText("");
        nrOreTF.setText("");
        specializareTF.setText("");
        gradMedTF.setText("");
        procentTF.setText("");
        titluTF.setText("");
        postDidacticTF.setText("");
        tipAsistentTF.setText("");
        gradAsistentTF.setText("");



    }

    public String getCnpTF() {
        return cnpTF.getText();
    }

    public String getIdUnitateTF() {
        return idUnitateTF.getText();
    }

    public String getUsernameTf() {
        return usernameTf.getText();
    }

    public String  getPassTF() {
        return passTF.getText();
    }

    public String getNumeTF() {
        return numeTF.getText();
    }

    public String  getPrenumeTF() {
        return prenumeTF.getText();
    }

    public String getAdresaTF() {
        return adresaTF.getText();
    }

    public String getTelefonTF() {
        return telefonTF.getText();
    }

    public String getEmailTF() {
        return emailTF.getText();
    }

    public String  getIbanTF() {
        return ibanTF.getText();
    }

    public String  getSalariuTF() {
        return salariuTF.getText();
    }

    public String getNrOreTF() {
        return nrOreTF.getText();
    }

    public String getSpecializare() {
        return specializareTF.getText();
    }

    public String getGradMedTF() {
        return gradMedTF.getText();
    }

    public String  getProcentTF() {
        return procentTF.getText();
    }

    public String  getTitluTF() {
        return titluTF.getText();
    }

    public String  getPostDidacticTF() {
        return postDidacticTF.getText();
    }

    public String  getTipAsistentTF() {
        return tipAsistentTF.getText();
    }

    public String  getGradAsistentTF() {
        return gradAsistentTF.getText();
    }

}
package Controller;

import GUI.CautareTableFrame;
import GUI.EconomicGUI;
import GUI.ResurseUmaneGUI;
import SQL.SQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Vector;

public class EconomicController {

    private EconomicGUI economic;


    public EconomicController(EconomicGUI economic) throws Exception {

        this.economic = economic;
        economic.addRaportLunaListener(new RaportLunaBtn());
        economic.addRaportLunaMedicListener(new RaportLunaMedicBtn());
        economic.addSalariuListener(new SalariuAngajatBtn());
        economic.addLogOutListener(new LogOut());
        cautaMedici();
        adaugaLuniCombo();

    }

    public void raportLuna() throws Exception{
        SQLConnection sql = new SQLConnection();
        Connection con =  sql.getConnection();

        CallableStatement cStmt = con.prepareCall("{call RAPORT_PROFIT_LUNA(?,?)}");
        cStmt.setInt(1, economic.getLuna());
        System.out.println(economic.getLuna());
        cStmt.registerOutParameter(2,Types.INTEGER);

        cStmt.execute();


        String rezultat = String.valueOf(cStmt.getInt(2));
        System.out.println(cStmt.getInt(2));
        economic.setRezultatTextField(rezultat);
        con.close();


    }
    public void raportLunaMedic() throws Exception {

        SQLConnection sql = new SQLConnection();
        Connection con =  sql.getConnection();

        String numeMedic = economic.getMedic();
        String cnpMedic = new String();

        String Query ="SELECT CNP FROM utilizator_angajat WHERE utilizator_angajat.Nume=" +"'"+ numeMedic+"';";
        PreparedStatement stm = con.prepareStatement(Query);
        ResultSet resultSet = stm.executeQuery();

        while (resultSet.next()){

            cnpMedic=resultSet.getString(1);
            System.out.println(cnpMedic);
        }


        CallableStatement statement = con.prepareCall("{call RAPORT_PROFIT_LUNA_MEDIC(?,?,?)}");
        statement.setInt(1, economic.getLuna());
        statement.setString(2,cnpMedic);
        statement.registerOutParameter(3,Types.INTEGER);



        statement.execute();
        String rezultat = String.valueOf(statement.getInt(3));
        System.out.println(statement.getInt(3));
        economic.setRezultatTextField(rezultat);

        con.close();

    }
    public void salariuAngajat() throws Exception{

        SQLConnection sql = new SQLConnection();
        Connection con =  sql.getConnection();

        String rezultat = new String();

       // PreparedStatement statement = con.prepareStatement("SELECT utilizator_angajat.salariu_negociat FROM utilizator_angajat WHERE Nume=" + economic.getCnp() +" OR Prenume="+ economic.getCnp()+ " OR CNP="+economic.getCnp()+";" );

        String Query ="SELECT utilizator_angajat.salariu_negociat FROM utilizator_angajat WHERE Nume=" +"'"+ economic.getCnp()+"'" +" OR Prenume="+"'"+ economic.getCnp()+"'"+ " OR CNP='"+economic.getCnp()+"';";
        PreparedStatement stm = con.prepareStatement(Query);
        ResultSet resultSet = stm.executeQuery();

        while (resultSet.next()){

            rezultat=resultSet.getString(1);
            System.out.println(rezultat);
        }

        economic.setRezultatTextField(rezultat);


    }
    public void adaugaLuniCombo(){

        economic.addItemLuna("Ianuarie");
        economic.addItemLuna("Februarie");
        economic.addItemLuna("Martie");
        economic.addItemLuna("April");
        economic.addItemLuna("Mai");
        economic.addItemLuna("Iunie");
        economic.addItemLuna("Iulie");
        economic.addItemLuna("August");
        economic.addItemLuna("Septembrie");
        economic.addItemLuna("Octombrie");
        economic.addItemLuna("Noiembrie");
        economic.addItemLuna("Decembrie");
    }
    public void cautaMedici() throws Exception {
        SQLConnection sql = new SQLConnection();
        Connection con =  sql.getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT Nume, Functie_idFunctie FROM utilizator_angajat;");

        ResultSet result = statement.executeQuery();

        while(result.next()) {
            if(result.getInt("Functie_idFunctie") == 5)
                economic.addItemMedic(result.getString("Nume") );
        }
        con.close();
    }



    class RaportLunaBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                raportLuna();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    class RaportLunaMedicBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                raportLunaMedic();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    class SalariuAngajatBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                salariuAngajat();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    class LogOut implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			economic.exit();
		}
	}


}

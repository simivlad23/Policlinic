package Controller;

import GUI.AdminGUI;
import SQL.SQLConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdminController {

    private AdminGUI admin;

    public AdminController(AdminGUI admin) {
        this.admin = admin;
        admin.addMedicListener(new AddMedicBtn());
        admin.addAsistentListener(new AddAsistentBtn());
        admin.addLogOutListener(new LogOut());


    }

    public void addMedic() throws Exception {
        SQLConnection sql = new SQLConnection();
        Connection con = sql.getConnection();

        CallableStatement cStmt = con.prepareCall("{call ADAUGARE_Utilizator_Angajat(?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)}");
        cStmt.setString(1, admin.getCnpTF());
        cStmt.setInt(2, Integer.parseInt(admin.getIdUnitateTF()));
        cStmt.setInt(3, 5);
        cStmt.setString(4, admin.getUsernameTf());
        cStmt.setString(5, admin.getPassTF());
        cStmt.setString(6, admin.getNumeTF());
        cStmt.setString(7, admin.getPrenumeTF());
        cStmt.setString(8, admin.getAdresaTF());
        cStmt.setString(9, admin.getTelefonTF());
        cStmt.setString(10, admin.getEmailTF());
        cStmt.setString(11, admin.getIbanTF());
        cStmt.setDate(12, Date.valueOf(LocalDate.now()));
        cStmt.setInt(13, Integer.parseInt(admin.getSalariuTF()));
        cStmt.setInt(14, Integer.parseInt(admin.getNrOreTF()));

        cStmt.execute();

        CallableStatement cStmt2 = con.prepareCall("{call ADAUGARE_MEDIC(?, ?, ?, ?,?,?)}");
        cStmt2.setString(1, admin.getCnpTF());
        cStmt2.setInt(2, Integer.parseInt(admin.getSpecializare()));

        cStmt2.setString(3, admin.getGradMedTF());
        cStmt2.setInt(4, Integer.parseInt(admin.getProcentTF()));
        cStmt2.setString(5, admin.getTitluTF());
        cStmt2.setString(6, admin.getPostDidacticTF());
        cStmt2.execute();

        admin.afiseazaMsg("Medic adaugat cu succes", false);


        admin.setNullText();

        con.close();


    }

    public void addAsistent() throws Exception {
        SQLConnection sql = new SQLConnection();
        Connection con = sql.getConnection();

        CallableStatement cStmt = con.prepareCall("{call ADAUGARE_Utilizator_Angajat(?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)}");
        cStmt.setString(1, admin.getCnpTF());
        cStmt.setInt(2, Integer.parseInt(admin.getIdUnitateTF()));
        cStmt.setInt(3, 5);
        cStmt.setString(4, admin.getUsernameTf());
        cStmt.setString(5, admin.getPassTF());
        cStmt.setString(6, admin.getNumeTF());
        cStmt.setString(7, admin.getPrenumeTF());
        cStmt.setString(8, admin.getAdresaTF());
        cStmt.setString(9, admin.getTelefonTF());
        cStmt.setString(10, admin.getEmailTF());
        cStmt.setString(11, admin.getIbanTF());
        cStmt.setDate(12, Date.valueOf(LocalDate.now()));
        cStmt.setInt(13, Integer.parseInt(admin.getSalariuTF()));
        cStmt.setInt(14, Integer.parseInt(admin.getNrOreTF()));

        cStmt.execute();

        CallableStatement cStmt2 = con.prepareCall("{call ADAUGARE_ASISTENT(?, ?, ?)}");
        cStmt2.setString(1, admin.getCnpTF());
        cStmt2.setString(2, admin.getTipAsistentTF());
        cStmt2.setString(3, admin.getGradAsistentTF());
        cStmt2.execute();

        admin.afiseazaMsg("Asistent adaugat cu succes", false);

        admin.setNullText();

        con.close();


    }

    class AddMedicBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                addMedic();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    class AddAsistentBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                addAsistent();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    class LogOut implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            admin.exit();
        }
    }
}

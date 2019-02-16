package GUI;

import SQL.SQLConnection;

import java.sql.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.JTable;
import java.awt.*;


public class CautareTableFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private boolean status;

    public CautareTableFrame(String title) throws Exception {
        // Creating Window using JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(title);
        frame.setSize(800, 300);
        frame.setLocationRelativeTo(null);

        // Adding Table View
        frame.add(getTablePanel());
        
        frame.setVisible(true);
    }

    private JPanel getTablePanel() throws Exception {

        JPanel tableJPanel = new JPanel();

        tableJPanel.setLayout(new BorderLayout());

        // Column Header
        String[] columns = {"Nume", "Prenume", "Denumire", "Functie" };

        // Getting Data for Table from Database
        Object[][] data = getEmployeeDetails();

        // Creating JTable object passing data and header
        JTable employeeTable = new JTable(data, columns);


        tableJPanel.add(employeeTable.getTableHeader(), BorderLayout.NORTH);
        tableJPanel.add(employeeTable, BorderLayout.CENTER);

        return tableJPanel;
    }

    private Object[][] getEmployeeDetails() throws Exception {

        Object[][] data = null;

        final String QUERY = "SELECT utilizator_angajat.Nume,utilizator_angajat.Prenume,unitate_medicala.denumire,functie.Nume_functie " +
                "FROM utilizator_angajat,unitate_medicala,functie" +
                " WHERE utilizator_angajat.unitate_medicala_idUnitate_Medicala = unitate_medicala.idUnitate_Medicala" +
                " AND functie.idFunctie=utilizator_angajat.Functie_idFunctie";

        SQLConnection sql = new SQLConnection();
        Connection connection =  sql.getConnection();


        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(QUERY);
            int rowCount = getRowCount(result); // Row Count
            int columnCount = getColumnCount(result); // Column Count

            data = new Object[rowCount][columnCount];

            // Starting from First Row for Iteration
            result.beforeFirst();
            int i = 0;
            while (result.next()) {
                int j = 0;
                data[i][j++] = result.getString("Nume");
                data[i][j++] = result.getString("Prenume");
                data[i][j++] = result.getString("Denumire");
                data[i][j++] = result.getString("Nume_Functie");
                i++;
            }
            status = true;
            // Closing the Resources;
            statement.close();
            connection.close();
        return data;
    }

    private int getRowCount(ResultSet rs) {

        try {
            if(rs != null) {
                rs.last();
                return rs.getRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getColumnCount(ResultSet rs) {

        try {
            if(rs != null)
                return rs.getMetaData().getColumnCount();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
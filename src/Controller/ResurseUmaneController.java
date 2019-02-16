package Controller;

import GUI.CautareTableFrame;
import GUI.ResurseUmaneGUI;
import SQL.SQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ResurseUmaneController {

    private ResurseUmaneGUI resurseUmane;


    public ResurseUmaneController(ResurseUmaneGUI resurseUmane) throws Exception {

        this.resurseUmane = resurseUmane;
        resurseUmane.addCautareListener(new CautareBtn());
        resurseUmane.addListatareListener(new ListareBtn());
        resurseUmane.addLogOutListener(new LogOut());
    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    public void cautareUtilizator() throws Exception{

        SQLConnection sql = new SQLConnection();
        Connection con =  sql.getConnection();

        CallableStatement cStmt = con.prepareCall("{call CAUTARE_ANGAJAT(?)}");
        cStmt.setString(1, resurseUmane.getCampCautare());
        //cStmt.registerOutParameter(1, Types.VARCHAR,resurseUmane.getCampCautare());

        ResultSet resultSet = cStmt.executeQuery();

        //resultSet.
        JTable table = new JTable(buildTableModel(resultSet));


        // Closes the Connection
        con.close();

        JOptionPane.showMessageDialog(null, new JScrollPane(table));


    }
    public void listareAngajati() throws Exception{

        CautareTableFrame cautareTableFrame =new CautareTableFrame("Cautare");

    }

    class ListareBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                listareAngajati();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    class CautareBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                cautareUtilizator();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    class LogOut implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			resurseUmane.exit();
		}
	}

}

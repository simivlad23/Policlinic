package GUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.LoginController;

public class MedicGUI {
	JFrame medicFrame = new JFrame("Medic");
	JPanel medicPanel = new JPanel();
	JLabel pacientiLabel = new JLabel("Pacienti programati azi:", SwingConstants.CENTER);
	JLabel istoricLabel = new JLabel("Istoric consultatii pacient:", SwingConstants.CENTER);
	JButton logOutButton = new JButton("LogOut");
	DefaultTableModel modelPac = new DefaultTableModel();
	JTable pacientiTable = new JTable(modelPac) {
		public boolean isCellEditable(int row, int column){  
	        return false;  
	      } 
	};
	DefaultTableModel modelIsto = new DefaultTableModel();
	JTable istoricTable = new JTable(modelIsto) {
		public boolean isCellEditable(int row, int column){  
	        return false;  
	      } 
	};
	JScrollPane scrollPanePac = new JScrollPane(pacientiTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JScrollPane scrollPaneIsto = new JScrollPane(istoricTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	public MedicGUI() {
		medicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		medicFrame.setSize(405, 620);
		medicFrame.setLocationRelativeTo(null);
		
		medicPanel.setLayout(null);
		
		pacientiLabel.setSize(200, 20);
		pacientiLabel.setLocation(100, 20);
		
		pacientiTable.setSize(330, 220);
		pacientiTable.setLocation(30, 50);
		pacientiTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		modelPac.addColumn("CNP");
		modelPac.addColumn("Nume");
		modelPac.addColumn("Prenume");
		setTableHeaderWidth(pacientiTable);
		scrollPanePac.setSize(330, 220);
		scrollPanePac.setLocation(30, 50);
		
		istoricLabel.setSize(200, 20);
		istoricLabel.setLocation(100, 280);
		
		istoricTable.setSize(330, 220);
		istoricTable.setLocation(30, 310);
		istoricTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		modelIsto.addColumn("Simptome");
		modelIsto.addColumn("Investigatii");
		modelIsto.addColumn("Diagnostic");
		modelIsto.addColumn("Tratament");
		setTableHeaderWidthIsto(istoricTable);
		scrollPaneIsto.setSize(330, 220);
		scrollPaneIsto.setLocation(30, 310);
		
		logOutButton.setSize(80, 20);
		logOutButton.setLocation(300, 550);
		
		medicPanel.add(pacientiLabel);
		medicPanel.add(scrollPanePac);
		medicPanel.add(istoricLabel);
		medicPanel.add(scrollPaneIsto);
		medicPanel.add(logOutButton);
		
		medicFrame.setContentPane(medicPanel);
		medicFrame.setVisible(true);
	}
	
	public void insertRowPac(String cnp, String nume, String prenume) {
		DefaultTableModel model = (DefaultTableModel) pacientiTable.getModel();
		model.addRow(new Object[]{cnp, nume, prenume});
		pacientiTable.setModel(model);
	}
	
	public void insertRowIsto(String simptome, String investigatii, String diagnostic, String tratament) {
		DefaultTableModel model = (DefaultTableModel) istoricTable.getModel();
		model.addRow(new Object[]{simptome, investigatii, diagnostic, tratament});
		istoricTable.setModel(model);
	}
	
	public void setTableHeaderWidth(JTable myTable) {
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
		    column = myTable.getColumnModel().getColumn(i);
		    column.setPreferredWidth(104);
		}
	}
	
	public void setTableHeaderWidthIsto(JTable myTable) {
		TableColumn column = null;
		for (int i = 0; i < 4; i++) {
		    column = myTable.getColumnModel().getColumn(i);
		    column.setPreferredWidth(82);
		}
	}
	
	public void deleteAllItemsIsto() {
		DefaultTableModel dtm = (DefaultTableModel) istoricTable.getModel();
		dtm.setRowCount(0);
		istoricTable.setModel(dtm);
	}
	
	public int getSelectedRowPac() {
		return pacientiTable.getSelectedRow();
	}
	
	public String getCNPPacient(int linie) {
		return (String) pacientiTable.getValueAt(linie, 0);
	}
	
	public void addMousePacientiTable(MouseListener listener) {
		pacientiTable.addMouseListener(listener);
	}
	
	public void addLogOutListener(ActionListener listener) {
		logOutButton.addActionListener(listener);
	}
	
	public void exit() {
		medicFrame.dispose();
		LoginGUI loginFrame = new LoginGUI();
		LoginController login = new LoginController(loginFrame);
	}
}

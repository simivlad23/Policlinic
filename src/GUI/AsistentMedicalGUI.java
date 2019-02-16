package GUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.LoginController;

public class AsistentMedicalGUI {
	JFrame asistentMedicalFrame = new JFrame("Asistent Medical");
	JPanel asistentMedicalPanel = new JPanel();
	JLabel vizualizarePacientiLabel = new JLabel("Vizualizare pacienti:", SwingConstants.CENTER);
	DefaultTableModel model = new DefaultTableModel();
	JTable pacTable = new JTable(model) {
		public boolean isCellEditable(int row, int column){  
	        return false;  
	      } 
	};
	JLabel consultatieLabel = new JLabel("Consultatie:", SwingConstants.CENTER);
	JLabel simptomeLabel = new JLabel("Simptome:");
	JLabel investigatiiLabel = new JLabel("Investigatii:");
	JLabel diagnosticLabel = new JLabel("Diagnostic:");
	JLabel tratamentLabel = new JLabel ("Tratament:");
	JTextField tratamentText = new JTextField();
	JTextField simptomeText = new JTextField();
	JTextField investigatiiText = new JTextField();
	JTextField diagnosticText = new JTextField();
	JButton completareRaportButton = new JButton("Completare Raport/Consultatie");
	JButton logOutButton = new JButton("LogOut");
	JScrollPane scrollPane = new JScrollPane(pacTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	public AsistentMedicalGUI() {
		asistentMedicalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		asistentMedicalFrame.setSize(405, 550);
		asistentMedicalFrame.setLocationRelativeTo(null);
		
		asistentMedicalPanel.setLayout(null);
		
		vizualizarePacientiLabel.setSize(120, 20);
		vizualizarePacientiLabel.setLocation(140, 20);
	
		pacTable.setSize(330, 220);
		pacTable.setLocation(30, 50);
		pacTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		model.addColumn("CNP");
		model.addColumn("Nume");
		model.addColumn("Prenume");
		model.addColumn("Serviciu");
		model.addColumn("Nr.Programare");
		model.addColumn("Consultatie");
		setTableHeaderWidth(pacTable);
		
		scrollPane.setSize(330, 220);
		scrollPane.setLocation(30, 50);
		
		consultatieLabel.setSize(120, 20);
		consultatieLabel.setLocation(140, 280);
		
		simptomeLabel.setSize(120, 20);
		simptomeLabel.setLocation(30, 310);
		
		investigatiiLabel.setSize(120, 20);
		investigatiiLabel.setLocation(30, 340);
		
		diagnosticLabel.setSize(120, 20);
		diagnosticLabel.setLocation(30, 370);
		
		tratamentLabel.setSize(120, 20);
		tratamentLabel.setLocation(30, 400);
		
		simptomeText.setSize(252, 20);
		simptomeText.setLocation(110, 310);
		
		investigatiiText.setSize(252, 20);
		investigatiiText.setLocation(110, 340);
		
		diagnosticText.setSize(252, 20);
		diagnosticText.setLocation(110, 370);
		
		tratamentText.setSize(252, 20);
		tratamentText.setLocation(110, 400);
		
		completareRaportButton.setSize(210, 20);
		completareRaportButton.setLocation(102, 430);
		
		logOutButton.setSize(80, 20);
		logOutButton.setLocation(300, 480);
		
		asistentMedicalPanel.add(vizualizarePacientiLabel);
		asistentMedicalPanel.add(scrollPane);
		asistentMedicalPanel.add(consultatieLabel);
		asistentMedicalPanel.add(simptomeLabel);
		asistentMedicalPanel.add(investigatiiLabel);
		asistentMedicalPanel.add(diagnosticLabel);
		asistentMedicalPanel.add(tratamentLabel);
		asistentMedicalPanel.add(simptomeText);
		asistentMedicalPanel.add(investigatiiText);
		asistentMedicalPanel.add(diagnosticText);
		asistentMedicalPanel.add(tratamentText);
		asistentMedicalPanel.add(completareRaportButton);
		asistentMedicalPanel.add(logOutButton);
		
		asistentMedicalFrame.setContentPane(asistentMedicalPanel);
		asistentMedicalFrame.setVisible(true);
	}
	
	public void insertRowPac(String cnp, String nume, String prenume, String serviciu, int nrProg, String consultatie) {
		DefaultTableModel model = (DefaultTableModel) pacTable.getModel();
		model.addRow(new Object[]{cnp, nume, prenume, serviciu, nrProg, consultatie});
		pacTable.setModel(model);
	}
	
	public void setTableHeaderWidth(JTable myTable) {
		TableColumn column = null;
		for (int i = 0; i < 6; i++) {
		    column = myTable.getColumnModel().getColumn(i);
		    if(i == 3) {
		    	column.setPreferredWidth(150);
		    }
		    else if (i == 4 || i == 0) {
		        column.setPreferredWidth(100);
		    } 
		    else {
		        column.setPreferredWidth(70);
		    }
		}
	}
	
	public void addSelectList(MouseListener listener) {
		pacTable.addMouseListener(listener);
	}
	
	public void addActionListCompletare(ActionListener listener) {
		completareRaportButton.addActionListener(listener);
	}
	
	public void addActionExit(ActionListener listener) {
		logOutButton.addActionListener(listener);
	}
	
	public int getLinieSel() {
		return pacTable.getSelectedRow();
	}
	
	public int getIdProgramare(int linie) {
		return (int) pacTable.getValueAt(linie, 4);
	}
	
	public String getSimptome() {
		return simptomeText.getText();
	}
	
	public String getInvestigatii() {
		return investigatiiText.getText();
	}
	
	public String getDiagnostic() {
		return diagnosticText.getText();
	}
	
	public String getTratament() {
		return tratamentText.getText();
	}
	
	public void exit() {
		asistentMedicalFrame.dispose();
		LoginGUI loginFrame = new LoginGUI();
		LoginController login = new LoginController(loginFrame);
	}
	
	public void afiseazaMsg(String msg, boolean ok) {
		if(ok == true)
			JOptionPane.showMessageDialog(asistentMedicalFrame, msg, "Warning", JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(asistentMedicalFrame, msg, "Succes", JOptionPane.PLAIN_MESSAGE);
	}
	
}

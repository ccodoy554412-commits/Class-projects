package codoy;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class untaPasa extends JFrame {
	static DefaultTableModel dtm;
	static JTextField nametxt,deptxt,yltxt,walletxt,statustxt;

	untaPasa(){
		setVisible(true);
		setSize(800,550);
		setTitle("School Canteen Wallet");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//name
		JLabel name = new JLabel("Full Name");
		nametxt = new JTextField();
		add(name).setBounds(30,210,100,10);
		add(nametxt).setBounds(30,230,200,20);
		//couse dept
		JLabel dept = new JLabel("Course/Dept");
		deptxt = new JTextField();
		add(dept).setBounds(30,260,100,10);
		add(deptxt).setBounds(30,280,200,20);
		
		//year level
		JLabel yl = new JLabel("Year Level");
		yltxt = new JTextField();
		add(yl).setBounds(30,310,100,10);
		add(yltxt).setBounds(30,330,200,20);
		
		//wellet balance
		JLabel wallet = new JLabel("Wallet Balance");
		walletxt = new JTextField();
		add(wallet).setBounds(30,360,100,10);
		add(walletxt).setBounds(30,380,200,20);
		
		//status 
		JLabel status = new JLabel("Status (Active or Suspended)");
		statustxt = new JTextField();
		add(status).setBounds(30,410,180,10);
		add(statustxt).setBounds(30,430,200,20);
		
		//button
		JButton add = new JButton("Add");
		JButton updt = new JButton("Update");
		JButton del = new JButton("Delete");
		JButton clear = new JButton("Clear");
		add(add).setBounds(300,470,100,20);
		add(updt).setBounds(410,470,100,20);
		add(del).setBounds(520,470,100,20);
		add(clear).setBounds(630,470,100,20);

		
		//table
		String[] colms = {"Full Name","Course/Dept","Year Level","Wallet Balance","Status"};
		dtm = new DefaultTableModel(colms,0);
		JTable table = new JTable(dtm);
		JScrollPane jsp = new JScrollPane(table);
		add(jsp).setBounds(250,30,510,420);

		table.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked() {
				int row = table.getSelectedRow();
				if(row == -1) {
					nametxt.setText(dtm.getValueAt(row, 0).toString());
					deptxt.setText(dtm.getValueAt(row, 1).toString());
					yltxt.setText(dtm.getValueAt(row, 2).toString());
					walletxt.setText(dtm.getValueAt(row, 3).toString());
					statustxt.setText(dtm.getValueAt(row, 4).toString());
				}
			}
		});
		
		add.addActionListener(e->{
			if(nametxt.getText().isEmpty()||
				deptxt.getText().isEmpty()||
				yltxt.getText().isEmpty()||
				walletxt.getText().isEmpty()||
				statustxt.getText().isEmpty()
				) {
				JOptionPane.showMessageDialog(null,"Please fill in the remaining");
			}else {
                dtm.addRow(new Object[]{nametxt.getText(), 
                deptxt.getText(), 
                yltxt.getText(), 
                walletxt.getText(), statustxt.getText()});
            }
			clear();
		});
		  updt.addActionListener(e -> {
	            int i = table.getSelectedRow();
	            if (i >= 0) {
	                dtm.setValueAt(nametxt.getText(), i, 0);
	                dtm.setValueAt(deptxt.getText(), i, 1);
	                dtm.setValueAt(yltxt.getText(), i, 2);
	                dtm.setValueAt(walletxt.getText(), i, 3);
	                dtm.setValueAt(statustxt.getText(), i, 4);
	              
	            } else {
	                JOptionPane.showMessageDialog(null, "Please select a row to update.");
	            }
	        });
		  del.addActionListener(e -> {
	            int i = table.getSelectedRow();
	            if (i >= 0) {
	                dtm.removeRow(i);
	              
	            } else {
	                JOptionPane.showMessageDialog(null, "Please select a row to delete.");
	            }
	        });
		  clear.addActionListener(e -> clear());
	}
	public void clear() {
		nametxt.setText("");
		deptxt.setText("");
		yltxt.setText("");
		walletxt.setText("");
		statustxt.setText("");
	}
	public void file() {
		dtm.setRowCount(0);
		File f = new File("CanteenWallet.txt");
		if(!f.exists())return;
		try(BufferedReader br = new BufferedReader(new FileReader("CanteenWallet.txt"))){
			String line;
			while((line=br.readLine())!=null) {
				String row[] = line.split("#");
				dtm.addRow(row);
			}
		}catch (Exception e) {
		
	}

	}	public static void main(String[] args) {
		new untaPasa();
	}

}

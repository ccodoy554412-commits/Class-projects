package cce103;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class employemanage extends JFrame {
    private final String FILE_NAME = "employees.txt";
    public static void main(String[]args){
        new employemanage();
    }
    employemanage(){
        setVisible(true);
        setSize(950,600);
        setTitle("Employee Management System");
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    //jlabel
        JLabel company = new JLabel("EMS Inc.");
       add(company).setBounds(20,40,100,20);

       JLabel emploid = new JLabel("Employee ID"); 
       add(emploid).setBounds(20,80,100,20);

       JLabel fulln = new JLabel("Fullname"); 
       add(fulln).setBounds(20,120,100,20);
       
       JLabel dof = new JLabel("Date of Birth"); 
       add(dof).setBounds(20,160,100,20);


       JLabel age = new JLabel("Age"); 
       add(age).setBounds(250,80,100,20);

       JLabel cs = new JLabel("Civil Status"); 
       add(cs).setBounds(250,120,100,20);
    //dropdown
       String[] civs = {"Single","Maried","Widowed","Separated","Divorced"};
       JComboBox<String> sivsdropd = new JComboBox<>(civs);
       add(sivsdropd).setBounds(250,140,200,20);


       JLabel nation = new JLabel("Nationality"); 
       add(nation).setBounds(250,160,100,20);
       

       //jtxf
         JTextField employeid = new JTextField();
         add(employeid).setBounds(20,100,200,20);

         JTextField fullnt = new JTextField();
         add(fullnt).setBounds(20,140,200,20);

         JTextField doftxt = new JTextField();
         add(doftxt).setBounds(20,180,200,20);

         JTextField agetxt = new JTextField();
         add(agetxt).setBounds(250,100,200,20);

         JTextField nationtxt = new JTextField();
         add(nationtxt).setBounds(250, 180, 200, 20);

        //3rd row
        JLabel sex = new JLabel("Gender");
        add(sex).setBounds(480,80,100,20);
        //radio buttons
        JRadioButton m = new JRadioButton("Male");
        JRadioButton f = new JRadioButton("Female");

        ButtonGroup gen = new ButtonGroup();
        gen.add(m);
        gen.add(f);

        add(m).setBounds(480,100,60,20);
        add(f).setBounds(536,100,70,20);

        JLabel cn = new JLabel("Contact Number");
        add(cn).setBounds(480,120,100,20);
        JTextField cntxt = new JTextField();
        add(cntxt).setBounds(480,140,200,20);

        JLabel contact = new JLabel("Email");
        add(contact).setBounds(480,160,100,20);
        JTextField contactxt = new JTextField();
        add(contactxt).setBounds(480,180,200,20);

        //4th row
        JLabel dept = new JLabel("Department");
        add(dept).setBounds(705,120,100,20);
        JTextField deptxt = new JTextField();
        add(deptxt).setBounds(705,140,200,20);

        JLabel job = new JLabel("Job Title/Position");
        add(job).setBounds(705,160,100,20);
        JTextField jobtxt = new JTextField();
        add(jobtxt).setBounds(705,180,200,20);

        //add button
        JButton add = new JButton("Add Employee");
        add(add).setBounds(500,220,150,20);

        //add a delete button
        JButton del = new JButton("Delete");
        add(del).setBounds(730,220,150,20);



        //Jtable
        String[] colms = {"Employee","Full Name","Birth","Age","Civil Status","Nationality","Gender","Contact","Email","Department","Job Title/Position"};

        DefaultTableModel dmodel = new DefaultTableModel(colms,0);

        JTable table = new JTable(dmodel);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,260,885,280);
        add(sp);

        //mouse listener
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();
                if(row != -1){
                   employeid.setText(dmodel.getValueAt(row, 0).toString());
                    fullnt.setText(dmodel.getValueAt(row, 1).toString());
                    doftxt.setText(dmodel.getValueAt(row, 2).toString());
                    agetxt.setText(dmodel.getValueAt(row, 3).toString());
                    sivsdropd.setSelectedItem(dmodel.getValueAt(row, 4).toString());
                    nationtxt.setText(dmodel.getValueAt(row, 5).toString());
                    
                    String gender = dmodel.getValueAt(row, 6).toString();
                    if(gender.equals("Male")) m.setSelected(true); else f.setSelected(true);
                    
                    cntxt.setText(dmodel.getValueAt(row, 7).toString());
                    contactxt.setText(dmodel.getValueAt(row, 8).toString());
                    deptxt.setText(dmodel.getValueAt(row, 9).toString());
                    jobtxt.setText(dmodel.getValueAt(row, 10).toString()); 
                }
            }
        });

        //to load from start up
        loadFile(dmodel);

        //action listener
      add.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object[] row = {
            employeid.getText(),  
            fullnt.getText(),     
            doftxt.getText(),
            agetxt.getText(),
            sivsdropd.getSelectedItem(),
            nationtxt.getText(),
            m.isSelected() ? "Male" : "Female",
            cntxt.getText(),
            contactxt.getText(),   
            deptxt.getText(),    
            jobtxt.getText()     
        };

        // Add the row to the table model
        dmodel.addRow(row);

        saveFile(dmodel);

        employeid.setText(""); 
        fullnt.setText("");
        doftxt.setText("");
        agetxt.setText("");
        nationtxt.setText("");
        cntxt.setText("");
        contactxt.setText("");
        deptxt.setText("");
        jobtxt.setText("");
        }
    }); 

    //trying to add delete button
    del.addActionListener(e->{
       int RowSelect = table.getSelectedRow();
            if (RowSelect == -1) {
                JOptionPane.showMessageDialog(null, "Select record to delete");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            dmodel.removeRow(RowSelect); 
            saveFile(dmodel);           
            JOptionPane.showMessageDialog(null, "Record Deleted"); 
    });

    }
    //save when closing
    private void saveFile(DefaultTableModel model) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sb.append(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) sb.append("~"); 
                }
                writer.println(sb.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }
    //load after closing
    private void loadFile(DefaultTableModel model) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("~");
                model.addRow(data);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }
}

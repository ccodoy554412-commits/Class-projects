package cce103;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


public class studentInfo extends JFrame {
    static JTextField nametxt,cortxt,sectxt;
    static DefaultTableModel dtm;

    studentInfo(){
        setVisible(true);
        setSize(460,420);
        setTitle("Student Information Form");
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //table
        String[] colms = {"Neme","Course","Section"};
        dtm = new DefaultTableModel(colms,0);
        JTable jt = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(jt);
        add(jsp).setBounds(10,20,410,256);

        //Name
        JLabel name = new JLabel("Name");
        nametxt = new JTextField();
        add(name).setBounds(20,280,100,20);
        add(nametxt).setBounds(20,300,140,20);
        //course
        JLabel cours = new JLabel("Course");
        cortxt = new JTextField();
        add(cours).setBounds(170,280,100,20);
        add(cortxt).setBounds(170,300,140,20);
        //Section
        JLabel sect = new JLabel("Section");
        sectxt = new JTextField();
        add(sect).setBounds(320,280,100,20);
        add(sectxt).setBounds(320,300,85,20);
        //buttons
        JButton add = new JButton("Add");
        JButton updt = new JButton("Update");
        JButton del = new JButton("Delete");
        JButton clr = new JButton("Clear");
        add(add).setBounds(20,330,90,20);
        add(updt).setBounds(120,330,90,20);
        add(del).setBounds(220,330,90,20);
        add(clr).setBounds(320,330,90,20);
        
       jt.addMouseListener(new MouseInputAdapter() {
        public void mouseClicked(MouseEvent e){
           int row = jt.getSelectedRow(); 
           if(row != -1){
            nametxt.setText(dtm.getValueAt(row, 0).toString());
            cortxt.setText(dtm.getValueAt(row, 1).toString());
            sectxt.setText(dtm.getValueAt(row, 2).toString());
           }
        }
       }); 

       read();

       add.addActionListener(e->{
        try (FileWriter fw = new FileWriter("StudentInfo.txt", true)) {
        
        Object[] rowData = {
            nametxt.getText(),
            cortxt.getText(),
            sectxt.getText()
        };

      
        fw.write(rowData[0] + "*" + rowData[1] + "*" + rowData[2] + "\n");
        fw.close();

        JOptionPane.showMessageDialog(null, "Successfully Added");
        read();  
        clear(); 
    } catch (Exception z) {
        JOptionPane.showMessageDialog(null, "Error: " + z.getMessage());
    }
       });

       updt.addActionListener(e->{
        
       });

       del.addActionListener(e->{
        int selectedRow = jt.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Select a row to Delete");
                return;
            }
            if (JOptionPane.showConfirmDialog(null, "Are you sure?") != JOptionPane.YES_OPTION) return;

            ArrayList<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("StudentInfo.txt"))) {
                String line;
                int rowdex = 0;
                while ((line = br.readLine()) != null) {
                    if (rowdex != selectedRow) lines.add(line);
                    rowdex++;
                }
            } catch (Exception z) { z.printStackTrace(); }

            saveToFile(lines);
            read();
            clear();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
       });

       clr.addActionListener(e -> clear());
    }
    private void saveToFile(ArrayList<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("StudentInfo.txt"))) {
            for (String l : lines) {
                bw.write(l + "\n");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void clear(){
        nametxt.setText("");
        cortxt.setText("");
        sectxt.setText("");
    }

    public void read(){
        dtm.setRowCount(0);
        File f = new File("StudentInfo.txt");
        if(!f.exists()) return;
        try(BufferedReader br = new BufferedReader(new FileReader("StudentInfo.txt"))) {
            String line;
            while((line=br.readLine())!=null){
                String row[] = line.split("\\*");
                dtm.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new studentInfo();
    }
}

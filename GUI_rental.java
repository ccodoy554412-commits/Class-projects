package cce103;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import javax.swing.*;

public class GUI_rental extends JFrame implements ActionListener{
    JTextField cus;
    JTextField cons;
    JTextField bil;

    public static void main(String[]args){
        new GUI_rental();
    }
    GUI_rental(){
        //text

        JLabel cusT = new JLabel("Customer Type");
        add(cusT).setBounds(50,50,150,25);

        JLabel consump = new JLabel("Consumption");
        add(consump).setBounds(50,85,150,25);

        JLabel bill = new JLabel("Bill");
        add(bill).setBounds(50,120,150,25);
        
        //text field
        cus = new JTextField();
        add(cus).setBounds(150,50,180,25); 

        cons = new JTextField();
        add(cons).setBounds(150,85,180,25);

        bil = new JTextField();
        add(bil).setBounds(150,120,180,25);

        //button

        JButton add = new JButton("add");
        
        add(add).setBounds(50,150,90,25);

        JButton del = new JButton("Delete");
        add(del).setBounds(145,150,90,25);

        JButton upd = new JButton("Update");
        add(upd).setBounds(240,150,90,25);

        add.addActionListener(this);
        del.addActionListener(this);
        upd.addActionListener(this);

        //frame
        setSize(400,235);
		setTitle("Codoy Rental");
		setLayout(null);
		setUndecorated(false);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true); 
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();
            if(button.getText().equals("add")){
                String type = cus.getText();
                String consumption = cons.getText();
                String billText = bil.getText();
                try {
                    savetoFile(type, consumption, billText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                cus.setText("");
                cons.setText("");
                bil.setText("");
            }
        }
    }

    public static void savetoFile(String type, String consumption, String bill) throws Exception {
        FileWriter fw = new FileWriter("RentalData.txt", true);
        fw.write(type + "#" + consumption + "#" + bill + "\n");
        fw.close();
    }

}

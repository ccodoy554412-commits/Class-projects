package cCe103;

import javax.swing.*;

public class GUI_rental extends JFrame{
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
        JTextField cus = new JTextField();
        add(cus).setBounds(150,50,180,25); 

        JTextField cons = new JTextField();
        add(cons).setBounds(150,85,180,25);

        JTextField bil = new JTextField();
        add(bil).setBounds(150,120,180,25);

        //button

        JButton add = new JButton("add");
        add(add).setBounds(50,150,90,25);

        JButton del = new JButton("Delete");
        add(del).setBounds(145,150,90,25);

        JButton upd = new JButton("Update");
        add(upd).setBounds(240,150,90,25);

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

}

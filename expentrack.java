import java.io.*;
import javax.swing.*;

public class expentrack extends JFrame{
    public static void main(String[] args) {
     new expentrack();   
    }
    expentrack(){
        setSize(390,355);
        setTitle("Expense Tracker");
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //text
        JLabel rn = new JLabel("Receipt Number:");
        JLabel sn = new JLabel("Store Number:");
        JLabel tc = new JLabel("Total cost:");
        JLabel tax = new JLabel("Tax(12%):");
        JLabel fin = new JLabel("Final Amount:");

        //inputs
        JTextField rnt = new JTextField();
        JTextField snt = new JTextField();
        JTextField tct = new JTextField();
        JTextField taxt = new JTextField();
        JTextField fint = new JTextField();


        //buttons
        JButton rec = new JButton("Record");
        JButton cle = new JButton("Clear");

        //visibility

        //txt
        add(rn).setBounds(20,50,100,20);
        add(sn).setBounds(20,80,100,20);
        add(tc).setBounds(20,110,100,20);
        add(tax).setBounds(20,160,100,20);
        add(fin).setBounds(20,190,100,20);
        //tf
        add(rnt).setBounds(150,50,150,20);
        add(snt).setBounds(150,80,150,20);
        add(tct).setBounds(150,110,150,20);
        add(taxt).setBounds(150,160,150,20);
        add(fint).setBounds(150,190,150,20);
        //buttons
        add(rec).setBounds(75,230,80,20);
        add(cle).setBounds(195,230,80,20);
        
        //listener
        rec.addActionListener(e -> {
            try {
                //Calculations
                double cost = Double.parseDouble(tct.getText());
                double taxCalc = cost * 0.12;
                double total = cost + taxCalc;

                taxt.setText(String.format("%.2f", taxCalc));
                fint.setText(String.format("%.2f", total));

                //Save to File 
                try (FileWriter fw = new FileWriter("data.txt", true);
                     PrintWriter pw = new PrintWriter(fw)) {
                    
                    pw.println("Receipt: " + rnt.getText());
                    pw.println("Store: " + snt.getText());
                    pw.println("Cost: " + cost);
                    pw.println("Tax: " + String.format("%.2f", taxCalc));
                    pw.println("Total: " + String.format("%.2f", total));
                   
                } catch (IOException ioEx) {
                    System.out.println("Error");
                }

            } catch (NumberFormatException ex) {
                System.out.println("Error");
            }
        });

   
        cle.addActionListener(e -> {
            rnt.setText("");
            snt.setText("");
            tct.setText("");
            taxt.setText("");
            fint.setText("");
        });

    }
}

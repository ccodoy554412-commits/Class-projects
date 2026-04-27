package cce103;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class defaultTable extends JFrame {
    
    static DefaultTableModel model;
    static JTable table;
    static JTextField txtFullname, txtUnitPrice, txtQuantity;
    static JLabel lblGrandTotal;
    
    String[] columns = {"Fullname", "Unit Price", "Quantity", "Total"};
    
    public defaultTable() {
        // Frame settings
        setTitle("Expense Tracker System");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        
       
        JLabel title = new JLabel("EXPENSE TRACKER SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(300, 10, 300, 40);
        add(title);
        
       //input 
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(20, 60, 170, 280);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Details"));
        inputPanel.setBackground(new Color(173, 216, 230));
        add(inputPanel);
        
       
        JLabel lblFullname = new JLabel("Fullname:");
        lblFullname.setBounds(10, 30, 80, 25);
        inputPanel.add(lblFullname);
        
        txtFullname = new JTextField();
        txtFullname.setBounds(10, 55, 140, 25);
        inputPanel.add(txtFullname);
        
        
        JLabel lblUnitPrice = new JLabel("Unit Price:");
        lblUnitPrice.setBounds(10, 95, 80, 25);
        inputPanel.add(lblUnitPrice);
        
        txtUnitPrice = new JTextField();
        txtUnitPrice.setBounds(10, 120, 140, 25);
        inputPanel.add(txtUnitPrice);
        
        
        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(10, 160, 80, 25);
        inputPanel.add(lblQuantity);
        
        txtQuantity = new JTextField();
        txtQuantity.setBounds(10, 185, 140, 25);
        inputPanel.add(txtQuantity);
        
       
        JButton btnAdd = new JButton("Add Expense");
        btnAdd.setBounds(10, 230, 140, 30);
        btnAdd.setBackground(new Color(50, 205, 50));
        btnAdd.setForeground(Color.WHITE);
        inputPanel.add(btnAdd);
        
        
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane).setBounds(200, 60, 650, 280);
        
      
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null);
        totalPanel.setBounds(200, 350, 650, 50);
        totalPanel.setBackground(new Color(255, 255, 200));
        totalPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(totalPanel);
        
        JLabel lblTotalText = new JLabel("GRAND TOTAL:");
        lblTotalText.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotalText.setBounds(20, 10, 150, 30);
        totalPanel.add(lblTotalText);
        
        lblGrandTotal = new JLabel("₱ 0.00");
        lblGrandTotal.setFont(new Font("Arial", Font.BOLD, 20));
        lblGrandTotal.setForeground(new Color(255, 0, 0));
        lblGrandTotal.setBounds(180, 10, 200, 30);
        totalPanel.add(lblGrandTotal);
        
        // Button 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(200, 410, 650, 40);
        add(buttonPanel);
        
        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setBounds(20, 5, 150, 30);
        btnDelete.setBackground(new Color(255, 69, 0));
        btnDelete.setForeground(Color.WHITE);
        buttonPanel.add(btnDelete);
        
        JButton btnSave = new JButton("Save to File");
        btnSave.setBounds(190, 5, 150, 30);
        btnSave.setBackground(new Color(0, 0, 205));
        btnSave.setForeground(Color.WHITE);
        buttonPanel.add(btnSave);
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(360, 5, 150, 30);
        btnRefresh.setBackground(new Color(0, 128, 128));
        btnRefresh.setForeground(Color.WHITE);
        buttonPanel.add(btnRefresh);
        
        JButton btnClear = new JButton("Clear All");
        btnClear.setBounds(530, 5, 150, 30);
        btnClear.setBackground(new Color(128, 128, 128));
        btnClear.setForeground(Color.WHITE);
        buttonPanel.add(btnClear);
        
     
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteExpense();
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                read();
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
        
       
        read();
        
        setVisible(true);
    }
    
    
    public void addExpense() {
        String fullname = txtFullname.getText().trim();
        String unitPriceStr = txtUnitPrice.getText().trim();
        String quantityStr = txtQuantity.getText().trim();
        
        if(fullname.isEmpty() || unitPriceStr.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }
        
        try {
            double unitPrice = Double.parseDouble(unitPriceStr);
            int quantity = Integer.parseInt(quantityStr);
            double total = unitPrice * quantity;
            
      
            Object[] row = {fullname, unitPrice, quantity, total};
            model.addRow(row);
            
         
            txtFullname.setText("");
            txtUnitPrice.setText("");
            txtQuantity.setText("");
            
         
            updateGrandTotal();
            
            JOptionPane.showMessageDialog(this, "Expense added successfully!");
            
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format!");
        }
    }
    
    // Method to delete selected row
    public void deleteExpense() {
        int selectedRow = table.getSelectedRow();
        
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Delete this record?", "Confirm", 
            JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION) {
            model.removeRow(selectedRow);
            updateGrandTotal();
            JOptionPane.showMessageDialog(this, "Record deleted!");
        }
    }
    
 
    public void updateGrandTotal() {
        double grandTotal = 0;
        
        for(int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, 3);
            if(value instanceof Number) {
                grandTotal += ((Number) value).doubleValue();
            } else if(value != null) {
                try {
                    grandTotal += Double.parseDouble(value.toString());
                } catch(NumberFormatException ex) {
                    // ignore invalid values
                }
            }
        }
        
        lblGrandTotal.setText(String.format("₱ %.2f", grandTotal));
    }
    
    //save to file
    public void saveToFile() {
        if(model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No data to save!");
            return;
        }
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Billing.txt"))) {
            for(int i = 0; i < model.getRowCount(); i++) {
                String fullname = (String) model.getValueAt(i, 0);
                double unitPrice = ((Number) model.getValueAt(i, 1)).doubleValue();
                int quantity = ((Number) model.getValueAt(i, 2)).intValue();
                double total = ((Number) model.getValueAt(i, 3)).doubleValue();
                
                String line = fullname + "#" + unitPrice + "#" + quantity + "#" + total;
                bw.write(line);
                bw.newLine();
            }
            
            JOptionPane.showMessageDialog(this, "Saved to Billing.txt!");
            
        } catch(IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    
    public static void read() {
        model.setRowCount(0);
        
        try(BufferedReader br = new BufferedReader(new FileReader("Billing.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] row = line.split("#");
                if(row.length == 4) {
                    try {
                        Object[] parsedRow = {
                            row[0],
                            Double.parseDouble(row[1]),
                            Integer.parseInt(row[2]),
                            Double.parseDouble(row[3])
                        };
                        model.addRow(parsedRow);
                    } catch(NumberFormatException ex) {
                        // skip invalid rows
                    }
                }
            }
            
            double grandTotal = 0;
            for(int i = 0; i < model.getRowCount(); i++) {
                Object value = model.getValueAt(i, 3);
                if(value instanceof Number) {
                    grandTotal += ((Number) value).doubleValue();
                } else if(value != null) {
                    try {
                        grandTotal += Double.parseDouble(value.toString());
                    } catch(NumberFormatException ex) {
                        // ignore invalid values
                    }
                }
            }
            if(lblGrandTotal != null) {
                lblGrandTotal.setText(String.format("₱ %.2f", grandTotal));
            }
            
        } catch(FileNotFoundException e) {
            // File doesn't exist yet - normal for first run
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Clear all rows
    public void clearAll() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Clear all records?", "Confirm", 
            JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION) {
            model.setRowCount(0);
            updateGrandTotal();
            JOptionPane.showMessageDialog(this, "All records cleared!");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new defaultTable();
            }
        });
    }
}
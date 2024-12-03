package CheckBalanceGUI;

import javax.swing.*;
import GUI.Home;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CheckBalance {

    private JFrame frame;

    public CheckBalance() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(230, 255, 254));
        frame.setTitle("Check Balance");
        frame.setBounds(100, 100, 852, 533);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnCheckBalance = new JButton("Check Balance");
        btnCheckBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCheckBalance.setForeground(new Color(0, 78, 168));
        btnCheckBalance.setBackground(new Color(252, 183, 21));
        btnCheckBalance.setBounds(161, 194, 185, 61);
        frame.getContentPane().add(btnCheckBalance);

        JButton btnAccountStatement = new JButton("Account Statement");
        btnAccountStatement.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAccountStatement.setForeground(new Color(0, 78, 168));
        btnAccountStatement.setBackground(new Color(252, 183, 21));
        btnAccountStatement.setBounds(161, 297, 185, 61);
        frame.getContentPane().add(btnAccountStatement);

        JButton btnHome = new JButton("Home");
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnHome.setForeground(new Color(0, 78, 168));
        btnHome.setBackground(new Color(252, 183, 21));
        btnHome.setBounds(10, 10, 100, 30);
        frame.getContentPane().add(btnHome);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 78, 168));
        panel.setBounds(479, 0, 363, 501);
        frame.getContentPane().add(panel);
        
        JLabel lblTitle = new JLabel("<html>What would you<br>like to do?</html>");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
        lblTitle.setBounds(33, 11, 298, 107);
        panel.add(lblTitle);
        
        JLabel lblDescription = new JLabel("<html>Choose an action from the options on the right to manage your account:<br><br>Check Balance: Check the Current Balance.<br><br>Account Statement: Check the Transaction History<br>");
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescription.setForeground(Color.WHITE);
        lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDescription.setBounds(57, 103, 256, 306);
        panel.add(lblDescription);
        
        JLabel lblCheckBalance = new JLabel("Check Balance");
        lblCheckBalance.setForeground(new Color(0, 78, 168));
        lblCheckBalance.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblCheckBalance.setBounds(61, 61, 368, 80);
        frame.getContentPane().add(lblCheckBalance);

        // Action listener for "Check Balance" button
        btnCheckBalance.addActionListener(e -> {
            try {
                double balance = fetchBalance();
                CurrentBalance balanceWindow = new CurrentBalance(balance);
                balanceWindow.show();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error fetching balance: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for "Account Statement" button
        btnAccountStatement.addActionListener(e -> {
            try {
                Account_Statement accountStatementWindow = new Account_Statement();
                accountStatementWindow.show();
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error loading account statement: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for "Home" button
        btnHome.addActionListener(e -> {
            Home homeWindow = new Home(0); // Use UserSession to get the PIN
            homeWindow.setVisible(true);
            frame.dispose();
        });
    }

    private double fetchBalance() {
        // Create an instance of Account_Statement
        Account_Statement accountStatement = new Account_Statement();

        // Calculate and return the current balance
        return accountStatement.calculateCurrentBalance();
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        CheckBalance checkBalance = new CheckBalance();
        checkBalance.show();
    }
}
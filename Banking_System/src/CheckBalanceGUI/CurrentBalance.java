package CheckBalanceGUI;

import javax.swing.*;
import java.awt.*;

public class CurrentBalance {
    private JFrame frame;
    private double balance;

    public CurrentBalance(double balance) {
        this.balance = balance;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(229, 245, 255));
        frame.setTitle("Current Balance");
        frame.setBounds(100, 100, 914, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnClose = new JButton("Close");
        btnClose.setBackground(Color.WHITE);
        btnClose.setBounds(90, 366, 100, 30);
        btnClose.addActionListener(e -> frame.dispose());
        frame.getContentPane().add(btnClose);

        // Add button to open Account Statement
        JButton btnAccountStatement = new JButton("View Account Statement");
        btnAccountStatement.setBackground(Color.WHITE);
        btnAccountStatement.setBounds(261, 366, 200, 30);
        btnAccountStatement.addActionListener(e -> {
            Account_Statement accountStatement = new Account_Statement();
            accountStatement.show();
            frame.dispose();
        });
        frame.getContentPane().add(btnAccountStatement);
        
        JLabel lblCurrentBalance = new JLabel("Current Balance");
        lblCurrentBalance.setForeground(new Color(0, 78, 168));
        lblCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblCurrentBalance.setBounds(76, 52, 404, 80);
        frame.getContentPane().add(lblCurrentBalance);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(0, 78, 168));
        panel_1.setBounds(535, 0, 363, 491);
        frame.getContentPane().add(panel_1);
        
        JLabel lbltoCompleteYour = new JLabel("<html>To complete your deposit, simply enter the amount you wish to deposit and confirm. Once submitted, you will receive a confirmation message.</html>");
        lbltoCompleteYour.setHorizontalAlignment(SwingConstants.CENTER);
        lbltoCompleteYour.setForeground(Color.WHITE);
        lbltoCompleteYour.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbltoCompleteYour.setBounds(64, 88, 271, 306);
        panel_1.add(lbltoCompleteYour);
        
        JLabel lblNewLabel_1 = new JLabel("Welcome to PotsBank");
        lblNewLabel_1.setForeground(new Color(230, 245, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setBounds(18, 33, 335, 64);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Your Trust, Our Commitment");
        lblNewLabel_1_1.setForeground(new Color(252, 183, 21));
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
        lblNewLabel_1_1.setBounds(92, 90, 219, 35);
        panel_1.add(lblNewLabel_1_1);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(66, 167, 394, 120);
        frame.getContentPane().add(panel);
                panel.setLayout(null);
        
                JLabel lblBalance = new JLabel("Your Current Balance:");
                lblBalance.setBounds(105, 11, 180, 20);
                panel.add(lblBalance);
                lblBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
                
                        JLabel lblAmount = new JLabel(String.format("$%.2f", balance));
                        lblAmount.setBounds(24, 42, 337, 63);
                        panel.add(lblAmount);
                        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
                        lblAmount.setForeground(new Color(0, 128, 0));
                        
                        JButton btnBack = new JButton("Back");
                        btnBack.setForeground(new Color(0, 78, 168));
                        btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
                        btnBack.setBackground(new Color(252, 183, 21));
                        btnBack.setBounds(10, 11, 100, 30);
                        frame.getContentPane().add(btnBack);
                        btnBack.addActionListener(e -> {
                            CheckBalance checkBalanceWindow = new CheckBalance(); // Create an instance of CheckBalance
                            checkBalanceWindow.show(); // Display the CheckBalance window
                            frame.dispose(); // Close the current frame (CurrentBalance)
                        });
                        frame.getContentPane().add(btnBack);
    }

    public void show() {
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {
        // Fetch real balance from Account_Statement
        Account_Statement accountStatement = new Account_Statement();

        // Ensure that transactions are loaded before calculating balance
        accountStatement.loadTransactionsFromDatabase();

        // Calculate the real-time balance
        double realBalance = accountStatement.calculateCurrentBalance();

        // Show the current balance in the GUI
        CurrentBalance balanceGUI = new CurrentBalance(realBalance);
        balanceGUI.show();
    }
}
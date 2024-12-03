package ChangePinGui;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import Signup.UserSession;
import GUI.Home; // Import your Home class

public class Change_Pin {

    private JFrame frame;
    private JTextField textFieldNewPin;
    private JTextField textFieldConfirmPin;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Change_Pin window = new Change_Pin();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Change_Pin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(229, 245, 255));
        frame.setBounds(100, 100, 830, 445);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewPin = new JLabel("New PIN:");
        lblNewPin.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewPin.setBounds(43, 150, 85, 25);
        frame.getContentPane().add(lblNewPin);

        textFieldNewPin = new JTextField();
        textFieldNewPin.setBounds(129, 152, 200, 25);
        frame.getContentPane().add(textFieldNewPin);
        textFieldNewPin.setColumns(10);

        JLabel lblConfirmPin = new JLabel("Confirm PIN:");
        lblConfirmPin.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblConfirmPin.setBounds(30, 220, 100, 25);
        frame.getContentPane().add(lblConfirmPin);

        textFieldConfirmPin = new JTextField();
        textFieldConfirmPin.setBounds(129, 222, 200, 25);
        frame.getContentPane().add(textFieldConfirmPin);
        textFieldConfirmPin.setColumns(10);

        JButton btnUpdate = new JButton("Update PIN");
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBounds(173, 288, 120, 30);
        frame.getContentPane().add(btnUpdate);

        JButton btnHome_1 = new JButton("Home");
        btnHome_1.setBackground(new Color(252, 183, 21));
        btnHome_1.setBounds(10, 20, 100, 30);
        frame.getContentPane().add(btnHome_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(0, 78, 168));
        panel_1.setBounds(456, 0, 363, 501);
        frame.getContentPane().add(panel_1);
        
        JLabel lblinputANew = new JLabel("<html>Input a new Pin that will serve as a New Pin to access the Features.</html>");
        lblinputANew.setHorizontalAlignment(SwingConstants.CENTER);
        lblinputANew.setForeground(Color.WHITE);
        lblinputANew.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblinputANew.setBounds(70, 68, 271, 306);
        panel_1.add(lblinputANew);
        
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
        
        JLabel lblChangePin = new JLabel("Change Pin");
        lblChangePin.setForeground(new Color(0, 78, 168));
        lblChangePin.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblChangePin.setBounds(134, 59, 170, 37);
        frame.getContentPane().add(lblChangePin);

        btnHome_1.addActionListener(e -> {
            // Redirect to Home GUI
            Home homeWindow = new Home(UserSession.getUserId()); // Pass user ID if needed
            homeWindow.setVisible(true);
            frame.dispose(); // Close current window
        });

        btnUpdate.addActionListener(e -> {
            String newPin = textFieldNewPin.getText();
            String confirmPin = textFieldConfirmPin.getText();

            if (newPin.isEmpty() || confirmPin.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Both fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(frame, "Pins do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPin.matches("\\d{5}")) {
                JOptionPane.showMessageDialog(frame, "PIN must be exactly 5 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Store the temporary PIN in UserSession (without updating the database)
            UserSession.setUserPin(Integer.parseInt(newPin));
            JOptionPane.showMessageDialog(frame, "Temporary PIN updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        });
    }
}

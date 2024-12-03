package CheckBalanceGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import GUI.Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class Account_Statement {

    private JFrame frmAccountStatement;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboBox;
    private JTextField textField;
    private TableRowSorter<DefaultTableModel> tableRowSorter; // RowSorter for filtering

    public double calculateCurrentBalance() {
        double currentBalance = 0.0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Double deposit = (Double) tableModel.getValueAt(i, 1); // Deposit column
            Double withdrawal = (Double) tableModel.getValueAt(i, 2); // Withdrawal column

            currentBalance += (deposit != null ? deposit : 0);
            currentBalance -= (withdrawal != null ? withdrawal : 0);
        }

        return currentBalance;
    }

    public Account_Statement() {
        initialize();
        loadTransactionsFromDatabase(); // Load transactions dynamically on initialization
    }

    private void initialize() {
        frmAccountStatement = new JFrame();
        frmAccountStatement.getContentPane().setBackground(new Color(229, 245, 255));
        frmAccountStatement.setTitle("Account Statement");
        frmAccountStatement.setBounds(100, 100, 1200, 600);
        frmAccountStatement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAccountStatement.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 130, 720, 350);
        frmAccountStatement.getContentPane().add(scrollPane);

        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Balance", "Deposit", "Withdrawal", "Date"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent editing of cells
            }
        };
        table.setModel(tableModel);
        tableRowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableRowSorter);
        scrollPane.setViewportView(table);

        comboBox = new JComboBox<>(new String[]{"Sort by Date", "Sort by Deposit Amount", "Sort by Withdrawal Amount"});
        comboBox.setBounds(120, 500, 200, 30);
        frmAccountStatement.getContentPane().add(comboBox);

        JButton btnGenerateStatement = new JButton("Generate");
        btnGenerateStatement.setBackground(Color.WHITE);
        btnGenerateStatement.setBounds(350, 500, 120, 30);
        btnGenerateStatement.addActionListener(e -> handleGenerateStatement());
        frmAccountStatement.getContentPane().add(btnGenerateStatement);

        textField = new JTextField();
        textField.setBounds(50, 90, 200, 30);
        frmAccountStatement.getContentPane().add(textField);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(270, 90, 100, 30);
        btnSearch.addActionListener(e -> {
            String searchText = textField.getText();
            if (searchText.isEmpty()) {
                tableRowSorter.setRowFilter(null); // Clear filter
            } else {
                tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive search
            }
        });
        frmAccountStatement.getContentPane().add(btnSearch);

        JButton btnHome = new JButton("Back");
        btnHome.setBackground(new Color(252, 183, 21));
        btnHome.setBounds(10, 10, 100, 30);
        btnHome.addActionListener(e -> {
            CheckBalance checkBalanceWindow = new CheckBalance(); // Create an instance of CheckBalance
            checkBalanceWindow.show(); // Call the show() method to display the window
            frmAccountStatement.dispose(); // Close the current Account_Statement frame
        });
        frmAccountStatement.getContentPane().add( btnHome);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(0, 78, 168));
        panel_1.setBounds(821, 0, 363, 561);
        frmAccountStatement.getContentPane().add(panel_1);
        
        JLabel lblhereInThe = new JLabel("<html>Here in the Account Statement is where you can view the Transaction History </html>");
        lblhereInThe.setHorizontalAlignment(SwingConstants.CENTER);
        lblhereInThe.setForeground(Color.WHITE);
        lblhereInThe.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblhereInThe.setBounds(64, 88, 271, 306);
        panel_1.add(lblhereInThe);
        
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
        
        JLabel lblAccountStatement = new JLabel("Account Statement");
        lblAccountStatement.setForeground(new Color(0, 78, 168));
        lblAccountStatement.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblAccountStatement.setBounds(207, 10, 486, 80);
        frmAccountStatement.getContentPane().add(lblAccountStatement);
    }

    public void loadTransactionsFromDatabase() {
        List<Transaction> transactions = Account_Statement_Database.fetchAllTransactions();

        for (Transaction transaction : transactions) {
            tableModel.addRow(new Object[]{
                transaction.getBalance(),
                transaction.getDeposit(),
                transaction.getWithdrawal(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getDate())
            });
        }
    }

    private void handleGenerateStatement() {
        String selectedOption = (String) comboBox.getSelectedItem();

        if ("Sort by Date".equals(selectedOption)) {
            tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING))); // Sort by Date
        } else if ("Sort by Deposit Amount".equals(selectedOption)) {
            tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.DESCENDING))); // Sort by Deposit
        } else if ("Sort by Withdrawal Amount".equals(selectedOption)) {
            tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(2, SortOrder.DESCENDING))); // Sort by Withdrawal
        }
    }

    public void show() {
        frmAccountStatement.setVisible(true);
    }

    public static void main(String[] args) {
        Account_Statement accountStatement = new Account_Statement();
        accountStatement.show();
    }
}
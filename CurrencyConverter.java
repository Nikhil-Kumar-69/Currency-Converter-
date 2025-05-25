import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField, resultField;
    private JButton convertButton, clearButton;

    // Exchange rates (fictional values for demo)
    private final HashMap<String, Double> rates = new HashMap<>();

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Populate exchange rates (relative to USD)
        rates.put("USD", 1.0);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.78);
        rates.put("JPY", 136.5);
        rates.put("CNY", 7.05);
        rates.put("INR", 82.7);  // Indian Rupee included!
        rates.put("CAD", 1.35);
        rates.put("AUD", 1.48);
        rates.put("CHF", 0.89);
        rates.put("KRW", 1320.0);
        rates.put("ZAR", 18.5);  // South African Rand (replacing BRL)

        String[] currencies = rates.keySet().toArray(new String[0]);

        // UI Components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);

        fromCurrency = new JComboBox<>(currencies);
        toCurrency = new JComboBox<>(currencies);
        amountField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setBackground(Color.LIGHT_GRAY);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("From:"), gbc);
        gbc.gridx = 1; add(fromCurrency, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("To:"), gbc);
        gbc.gridx = 1; add(toCurrency, gbc);
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1; add(amountField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Result:"), gbc);
        gbc.gridx = 1; add(resultField, gbc);

        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        convertButton.setBackground(new Color(72, 209, 204));
        clearButton.setBackground(new Color(255, 105, 97));
        convertButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 0; gbc.gridy = 5; add(convertButton, gbc);
        gbc.gridx = 1; add(clearButton, gbc);

        // Action Listeners
        convertButton.addActionListener(e -> convertCurrency());
        clearButton.addActionListener(e -> clearFields());

        // Aesthetic touch: rounded corners (optional)
        getContentPane().setBackground(new Color(240, 248, 255));
        convertButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);

        setVisible(true);
    }

    private void convertCurrency() {
        String from = (String) fromCurrency.getSelectedItem();
        String to = (String) toCurrency.getSelectedItem();
        try {
            double amount = Double.parseDouble(amountField.getText());
            double usdAmount = amount / rates.get(from);
            double convertedAmount = usdAmount * rates.get(to);
            resultField.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        amountField.setText("");
        resultField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserManagementWindow extends JFrame {

    private static final String[] COLUMN_NAMES = {"Usuario", "Contraseña"};
    private static final String PASSWORD_PLACEHOLDER = "••••••";

    private JTable userTable;
    private DefaultTableModel tableModel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public UserManagementWindow() {
        setTitle("Gestión de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createUserTablePanel(), BorderLayout.CENTER);
        mainPanel.add(createFormPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JScrollPane createUserTablePanel() {
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
        userTable = new JTable(tableModel);
        return new JScrollPane(userTable);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Usuario"));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton saveButton = new JButton("Guardar usuario");
        saveButton.addActionListener(this::handleSaveUser);

        JButton clearButton = new JButton("Limpiar formulario");
        clearButton.addActionListener(e -> clearForm());

        formPanel.add(new JLabel("Usuario:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Contraseña:"));
        formPanel.add(passwordField);
        formPanel.add(saveButton);
        formPanel.add(clearButton);

        return formPanel;
    }

    private void handleSaveUser(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (!isValidInput(username, password)) {
            JOptionPane.showMessageDialog(this, "error, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{username, PASSWORD_PLACEHOLDER});
        JOptionPane.showMessageDialog(this, "guardado exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
    }

    private boolean isValidInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserManagementWindow().setVisible(true));
    }
}

package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bussines.model.Credential;

import java.awt.*;
import java.awt.event.ActionEvent;

public class UserManagementWindow extends JFrame {

    private static final String TITLE = "Gestión de Usuarios";

    private JTextField usernameField;
    private JPasswordField passwordField;
    private DefaultTableModel tableModel;

    public UserManagementWindow() {
        setTitle(TITLE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(createUserTablePanel(), BorderLayout.CENTER);
        add(createFormPanel(), BorderLayout.SOUTH);
    }

    private JScrollPane createUserTablePanel() {
        tableModel = new DefaultTableModel(new Object[]{"Usuario", "Contraseña"}, 0);
        JTable userTable = new JTable(tableModel);
        userTable.setFillsViewportHeight(true);
        return new JScrollPane(userTable);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Usuario"));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre de usuario:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JButton saveButton = new JButton("Guardar usuario");
        saveButton.addActionListener(this::saveUser);
        formPanel.add(saveButton, gbc);

        JButton clearButton = new JButton("Limpiar formulario");
        clearButton.addActionListener(e -> clearForm());
        formPanel.add(clearButton, gbc);

        return formPanel;
    }
    private void saveUser(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{username, password});
        JOptionPane.showMessageDialog(this, "✅ Usuario guardado exitosamente.");
        clearForm();
    }
    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserManagementWindow window = new UserManagementWindow();
            window.setVisible(true);
        });
    }

}

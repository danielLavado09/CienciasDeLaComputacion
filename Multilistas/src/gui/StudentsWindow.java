package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentsWindow extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnDelete;

    public StudentsWindow(String courseName) {
        setTitle("Gestión Académica - Estudiantes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Table.
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        String[] columnas = {"Nombre", "Código"};
        tableModel = new DefaultTableModel(columnas, 0);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPane.setViewportView(table);

        // Options Panel.
        JPanel optionsPanel = new JPanel(new FlowLayout());
        contentPane.add(optionsPanel, BorderLayout.SOUTH);

        // Buttons.

        // --Buttons: Add.
        btnAdd = new JButton("Agregar estudiante");
        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAdd.setBackground(new Color(255, 255, 255));
        btnAdd.setForeground(Color.black);
        btnAdd.setFocusable(false);
        optionsPanel.add(btnAdd);

        // --Buttons: Delete.
        btnDelete = new JButton("Eliminar estudiante");
        btnDelete.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnDelete.setBackground(new Color(255, 255, 255));
        btnDelete.setForeground(Color.black);
        btnDelete.setFocusable(false);
        optionsPanel.add(btnDelete);

        // Info Text.
        JLabel lblInfo = new JLabel("Lista de estudiantes: [Profesor: "+ courseName+"]");
        lblInfo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblInfo.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblInfo, BorderLayout.NORTH);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }
}
package components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Table extends JPanel {
    public DefaultTableModel model;
    public JTable table;

    public Table() {
        String[] columns = new String[]{"Lanche", "Valor"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        setBackground(Color.decode("#8B0000"));
        table.setForeground(Color.BLACK);

        // trava a tabela
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setResizingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane);
    }

    public void carregarDados(List<Object[]> dados) {
        model.setRowCount(0);

        // novos dados a tabela
        for (Object[] linha : dados) {
            model.addRow(linha);
        }
    }

    public Object getValueAt(int row, int col) {
        return model.getValueAt(row, col);
    }

    public void removerLinhaSelecionada() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
        }
    }

    // obter a linha selecionada
    public int getSelectedRow() {
        return table.getSelectedRow();
    }
}

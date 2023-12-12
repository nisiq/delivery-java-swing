package components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ComboBox extends JPanel {
    private JComboBox<String> comboBox;
    private JLabel label;
    private ActionListener confirmButtonListener;

    public ComboBox(List<String> items, ActionListener listener) {
        this.confirmButtonListener = listener;

        comboBox = new JComboBox<>(items.toArray(new String[0]));
        label = new JLabel("Selecione um restaurante:");
        Botao confirmButton = new Botao("ok");

        // selecionar e confirmar a escolha no comboBox
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();
                label.setText("voce selecionou: " + selected);

                if (confirmButtonListener != null) {
                    confirmButtonListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, selected));
                }
            }
        });

        add(label);
        add(comboBox);
        add(confirmButton);
    }

    public Object getSelectedItem() {
        return comboBox.getSelectedItem();
    }
}

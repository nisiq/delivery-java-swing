package interfaces;

import components.Botao;
import components.ComboBox;
import components.Janela;
import entities.Restaurantesx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Restaurantes extends JFrame {
    private int idRestauranteEscolhido;  // armazena o ID do restaurante
    private ComboBox comboBox;

    // Interface Java Swing - Visualizar e escolher restaurante (CLIENTE)
    public Restaurantes() {
        Janela janela = new Janela("Restaurantes");
        janela.setLayout(new BorderLayout());
        janela.setBackground(Color.WHITE);


        // pega os nomes dos restaurantes no db
        List<String> nomesRestaurantes = Restaurantesx.obterNomesRestaurantes();

        comboBox = new ComboBox(nomesRestaurantes, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String restauranteEscolhido = (String) comboBox.getSelectedItem();
                // ID do restaurante e fazer outras ações necessrias
                idRestauranteEscolhido = Restaurantesx.obterIdRestaurante(restauranteEscolhido);
            }
        });
        comboBox.setBounds(100, 100, 100, 100);


        Botao btnConfirmar = new Botao("Confirmar minha escolha");
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                janela.dispose();

                // ir para o cardapio do rest selecionado
                CardapioUsuario cardapio = new CardapioUsuario(idRestauranteEscolhido);
                cardapio.setVisible(true);
            }
        });


        String imagePath = "src/Imagens/love-eat-log.png";
        ImageIcon icon = new ImageIcon(imagePath);

        JLabel label = new JLabel(icon);
        label.setBounds(150, 120, icon.getIconWidth(), icon.getIconHeight());


        janela.add(label);
        janela.add(comboBox);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnConfirmar);
        janela.add(buttonPanel, BorderLayout.SOUTH);

        janela.setVisible(true);
    }
}

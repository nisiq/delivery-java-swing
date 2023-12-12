package interfaces;

import components.Botao;
import components.Janela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome {

    // Interface Java Swing - Inicial (AMBOS)

    public Welcome() {

        Janela janela = new Janela("Welcome");

        Botao btnRestaurante = new Botao("Cad. Restaurante");
        btnRestaurante.setBounds(20, 20, 150, 30);

        String imagePath = "src/Imagens/love-eat-log.png";
        ImageIcon icon = new ImageIcon(imagePath);

        JLabel label = new JLabel(icon);
        label.setBounds(150, 120, icon.getIconWidth(), icon.getIconHeight());

        // cadastro (RESTAURANTE)
        btnRestaurante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                CadastroRestaurante cadastroRestaurante = new CadastroRestaurante();
                cadastroRestaurante.setVisible(true);
            }
        });

        // login (AMBOS)
        Botao btnLogin = new Botao("Login");
        btnLogin.setBounds(190, 20, 200, 30);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });

        // cadastro (CLIENTE)
        Botao btnCliente = new Botao("Cad. Cliente");
        btnCliente.setBounds(410, 20, 150, 30);
        btnCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                CadastroCliente cadastroCliente = new CadastroCliente();
                cadastroCliente.setVisible(true);
            }
        });


        Botao btnSaibaMais = new Botao("Saiba Mais");
        btnSaibaMais.setBounds(240, 500, 100, 30);
        btnSaibaMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Seja bem-vindo a LOVEAT!! Caso queira registrar seu restaurante, basta fazer o cadastro! " +
                        "Agora, se você for consumidor, realize o seu como cliente e aproveite nossos restaurantes! " +
                        "E se já tem seu login, MELHOR AINDA! Entre e aproveite!");
            }
        });



        janela.add(btnRestaurante);
        janela.add(btnLogin);
        janela.add(btnCliente);
        janela.add(label);
        janela.add(btnSaibaMais);

        janela.setVisible(true);
    }

    private static void dispose() {
    }

    public void setVisible(boolean b) {
    }

    public void pack() {
    }
}

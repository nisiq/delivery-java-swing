package interfaces;

import components.*;
import entities.LoginIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login {

    // Interface Java Swing - Login (RESTAURANTE E USUARIO)
    public Login() {
        // Componentes
        Janela janela = new Janela("Login");
        janela.setSize(300, 400);


        Botao btnVoltar = new Botao("Voltar");
        btnVoltar.setBounds(20, 20, 150, 30);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();

                Welcome welcome = new Welcome();
                welcome.setVisible(true);
            }
        });

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(100, 70, 100, 30);

        CampoTexto user = new CampoTexto();
        user.setBounds(100, 100, 100, 30);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(100, 170, 100, 30);

        CampoSenha senha = new CampoSenha();
        senha.setBounds(100, 200, 100, 30);

        Botao btnLogin = new Botao("Login");
        btnLogin.setBounds(100, 300, 100, 30);

        janela.add(labelUsuario);
        janela.add(user);
        janela.add(labelSenha);
        janela.add(senha);
        janela.add(btnLogin);
        janela.add(btnVoltar);

        janela.setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = user.getText();
                String senhaDigitada = new String(senha.getPassword());

                int idUsuario = LoginIn.validarLogin(usuario, senhaDigitada);
                if (idUsuario != -1) {
                    // redireciona para a tela de acordo com o tipo de usuário
                    janela.dispose();
                    if (idUsuario > 0) {
                        int idRestauranteLogado = idUsuario;
                        System.out.println("ID do Restaurante Logado: " + idRestauranteLogado);
                        ProdutosRest produtosRest = new ProdutosRest(idRestauranteLogado);
                        produtosRest.setVisible(true);
                    } else {
                        // ID negativo indica um cliente
                        Restaurantes restaurantes = new Restaurantes();
                        restaurantes.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(janela, "Usuário ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void setVisible(boolean b) {
    }
}

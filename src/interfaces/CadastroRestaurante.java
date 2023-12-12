package interfaces;

import components.Botao;
import components.CampoSenha;
import components.CampoTexto;
import components.Janela;
import entities.CriarConta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroRestaurante extends JFrame {

    // Interface Java Swing - Cadastrar um Restaurante
    public CadastroRestaurante() {

        Janela janela = new Janela("Cadastro de Restaurante");

        Botao btnVoltar = new Botao("Voltar");
        btnVoltar.setBounds(20, 20, 150, 30);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                Welcome welcome = new Welcome();
                welcome.setVisible(true);
            }
        });

        JLabel labelUsuario = new JLabel("Nome Restaurante");
        labelUsuario.setBounds(130, 100, 150, 30);

        CampoTexto user = new CampoTexto();
        user.setBounds(250, 100, 100, 30);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(150, 200, 100, 30);

        CampoSenha senha = new CampoSenha();
        senha.setBounds(250, 200, 100, 30);

        JLabel labelCEP = new JLabel("CEP:");
        labelCEP.setBounds(150, 300, 100, 30);

        CampoTexto cep = new CampoTexto();
        cep.setBounds(250, 300, 100, 30);

        JLabel labelCNPJ = new JLabel("CNPJ:");
        labelCNPJ.setBounds(150, 400, 100, 30);

        CampoTexto cnpj = new CampoTexto();
        cnpj.setBounds(250, 400, 100, 30);

        Botao btnLogin = new Botao("Criar Conta");
        btnLogin.setBounds(250, 500, 100, 30);

        String imagePath = "src/Imagens/love-eat-log.png";
        ImageIcon icon = new ImageIcon(imagePath);

        Image imagemRedimensionada = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imagemRedimensionada);

        JLabel labelImagem = new JLabel(iconRedimensionado);
        labelImagem.setBounds(janela.getWidth() - iconRedimensionado.getIconWidth() - 50, 20, iconRedimensionado.getIconWidth(), iconRedimensionado.getIconHeight());

        janela.add(labelUsuario);
        janela.add(user);

        janela.add(labelSenha);
        janela.add(senha);

        janela.add(labelCEP);
        janela.add(cep);

        janela.add(labelCNPJ);
        janela.add(cnpj);

        janela.add(btnLogin);
        janela.add(btnVoltar);
        janela.add(labelImagem);

        janela.setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = user.getText();
                String senhaDigitada = new String(senha.getPassword());
                String cepDigitado = cep.getText();
                String cnpjDigitado = cnpj.getText();

                //  validação no db
                if (CriarConta.CriarLoginRest(usuario, senhaDigitada, cepDigitado, cnpjDigitado)) {
                    JOptionPane.showMessageDialog(janela, "Cadastro concluído! Agora realize seu login...!");
                    janela.dispose();
                    Login login = new Login();
                    login.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroRestaurante();
        });
    }
}

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

public class CadastroCliente {

    // Interface Java Swing - Cadastrar um cliente
    public CadastroCliente() {

        Janela janela = new Janela("Cadastro de Cliente");

        // volta para tela inicial (pontos UX!!!!!!)
        Botao btnVoltar = new Botao("Voltar");
        btnVoltar.setBounds(20, 20, 150, 30);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                Welcome welcome = new Welcome();
                welcome.setVisible(true);
            }
        });

        String imagePath = "src/Imagens/love-eat-log.png";
        ImageIcon icon = new ImageIcon(imagePath);


        Image imagemRedimensionada = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imagemRedimensionada);

        JLabel labelImagem = new JLabel(iconRedimensionado);
        labelImagem.setBounds(janela.getWidth() - iconRedimensionado.getIconWidth() - 50, 20, iconRedimensionado.getIconWidth(), iconRedimensionado.getIconHeight());

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(150, 100, 100, 30);

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

        JLabel labelCPF = new JLabel("CPF:");
        labelCPF.setBounds(150, 400, 100, 30);

        CampoTexto cpf = new CampoTexto();
        cpf.setBounds(250, 400, 100, 30);

        Botao btnLogin = new Botao("Criar Conta");
        btnLogin.setBounds(250, 500, 100, 30);

        janela.add(labelUsuario);
        janela.add(user);

        janela.add(labelSenha);
        janela.add(senha);

        janela.add(labelCEP);
        janela.add(cep);

        janela.add(labelCPF);
        janela.add(cpf);

        janela.add(btnLogin);
        janela.add(btnVoltar);
        janela.add(labelImagem);

        janela.setVisible(true);

        // validacao de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pega os textos inseridos
                String usuario = user.getText();
                String senhaDigitada = new String(senha.getPassword());
                String cepDigitado = cep.getText();
                String cpfDigitado = cpf.getText();

                //  validação no db
                if (CriarConta.CriarLoginCliente(usuario, senhaDigitada, cepDigitado, cpfDigitado)) {
                    JOptionPane.showMessageDialog(janela, "Cadastro concluído! Agora realize seu login...!");
                    janela.dispose();
                    Login login = new Login();
                    login.setVisible(true);
                }
            }
        });
    }

    public void setVisible(boolean b) {
    }
}

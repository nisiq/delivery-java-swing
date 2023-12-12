package interfaces;

import components.*;
import entities.Cardapiox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdutosCad {

    // Interface Java Swing - Cadastro de Produtos (RESTAURANTE)
    private int idRestauranteLogado;


    public ProdutosCad(int idRestaurante) {
        this.idRestauranteLogado = idRestaurante;


        Janela janela = new Janela("Cadastro de Produtos");
        janela.setLayout(new BorderLayout());
        Botao btnVoltar = new Botao("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                ProdutosRest produtosRest = new ProdutosRest(idRestauranteLogado);
                produtosRest.setVisible(true);
            }
        });

        CampoTexto nomeProduto = new CampoTexto();
        CampoTexto valorProduto = new CampoTexto();

        Botao cadProduto = new Botao("Cadastrar");
        cadProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // valores dos campos de texto
                String nome = nomeProduto.getText();
                String valorTexto = valorProduto.getText();

                try {
                    // convert o valor para double
                    double valor = Double.parseDouble(valorTexto);
                    int idRestaurante = idRestauranteLogado; // Utilize a variável já existente
                    if (Cardapiox.cadastrarProduto(nome, valor, idRestaurante)) {
                        JOptionPane.showMessageDialog(janela, "Produto cadastrado com sucesso!");
                        janela.dispose();
                        ProdutosRest produtosRest = new ProdutosRest(idRestauranteLogado);
                        produtosRest.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(janela, "Erro ao cadastrar o produto.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(janela, "Digite um valor válido para o produto.");
                }
            }
        });

        JPanel panelCampos = new JPanel(new GridLayout(2, 2));
        panelCampos.add(new JLabel("Nome do Produto:"));
        panelCampos.add(nomeProduto);
        panelCampos.add(new JLabel("Valor do Produto:"));
        panelCampos.add(valorProduto);

        janela.add(panelCampos, BorderLayout.CENTER);
        janela.add(cadProduto, BorderLayout.SOUTH);
        janela.add(btnVoltar, BorderLayout.NORTH);

        janela.setSize(400, 200);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        janela.setVisible(true);
    }

    public void setVisible(boolean b) {
    }
}

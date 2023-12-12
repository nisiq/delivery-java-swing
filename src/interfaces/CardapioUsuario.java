package interfaces;

import components.Botao;
import components.Janela;
import components.Table;
import entities.Cardapiox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Interface Java Swing - Cardapio (CLIENTE)
public class CardapioUsuario extends JFrame {
    private Table table;
    private int idRestauranteEscolhido;
    private Carrinho carrinho;

    public CardapioUsuario(int idRestaurante) {
        this.idRestauranteEscolhido = idRestaurante;

        Janela janela = new Janela("Cardápio");
        janela.setLayout(new BorderLayout());

        table = new Table();
        janela.setBackground(Color.WHITE);


        Botao btnCarrinho = new Botao("Ir para o carrinho");
        Botao btnAdc = new Botao("Adicionar ao carrinho");
        Botao btnVoltar = new Botao("Voltar");


        carrinho = new Carrinho();

        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

                Restaurantes restaurantes = new Restaurantes();
                restaurantes.setVisible(true);
            }
        });

        btnCarrinho.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                carrinho.setVisible(true);
            }
        });

        // botão no topo e a tabela no centro
        janela.add(table, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnVoltar);
        buttonPanel.add(btnAdc);
        buttonPanel.add(btnCarrinho);

        janela.add(buttonPanel, BorderLayout.SOUTH);

        // puxa os dados do banco e exibe na tabela
        List<Object[]> cardapioData = Cardapiox.obterCardapio(idRestauranteEscolhido);
        table.carregarDados(cardapioData);

        btnAdc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  linha selecionada na tabela de cardápio
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // os dados do produto da linha selecionada
                    String produto = (String) table.getValueAt(selectedRow, 0);
                    double valor = (double) table.getValueAt(selectedRow, 1);

                    // adc o produto ao carrinho
                    Object[] produtoNoCarrinho = {produto, valor};
                    carrinho.adicionarProdutoAoCarrinho(produtoNoCarrinho);
                    JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho!");

                }
            }
        });

        janela.setPreferredSize(new Dimension(600, 400));
        janela.setLocationRelativeTo(null);
        janela.pack();
        janela.setVisible(true);
    }

    public CardapioUsuario() {

    }
}

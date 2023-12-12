// interfaces.ProdutosRest
package interfaces;

import components.Botao;
import components.Janela;
import components.Table;
import entities.Cardapiox;
import entities.Restaurantesx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class ProdutosRest extends JFrame {

    private int idRestauranteLogado;

    public ProdutosRest(int idRestaurante) {
        this.idRestauranteLogado = idRestaurante;

        Janela janela = new Janela("Meu Cardapio");

        janela.setLayout(new BorderLayout());

        Table table = new Table();

        Botao btnAdicionar = new Botao("Cadastrar Novo Produto");
        Botao btnRemover = new Botao("Remover Produto");
        Botao btnEditar = new Botao("Editar Produto");
        Botao btnImprimirCardapio = new Botao("Imprimir Cardápio");

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProdutosCad cadProduto = new ProdutosCad(idRestauranteLogado);
                cadProduto.setVisible(true);
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String produto = (String) table.table.getValueAt(selectedRow, 0);
                    table.removerLinhaSelecionada();
                    Cardapiox.removerProduto(produto);
                    JOptionPane.showMessageDialog(null, "Produto removido do cardapio!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para remover.");
                }
            }
        });

        btnImprimirCardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirCardapio();
                JOptionPane.showMessageDialog(null, "Cardapio impresso com sucesso! Arquivo salvo na pasta 'comanda' > 'produtos'");

            }
        });

        janela.add(table, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnImprimirCardapio);

        janela.add(buttonPanel, BorderLayout.SOUTH);

        List<Object[]> cardapioData = Cardapiox.obterCardapio(idRestauranteLogado);
        table.carregarDados(cardapioData);

        janela.setPreferredSize(new Dimension(600, 400));
        janela.setLocationRelativeTo(null);
        janela.pack();
        janela.setVisible(true);
    }

    private void imprimirCardapio() {
        List<Object[]> cardapioData = Restaurantesx.obterCardapio(idRestauranteLogado);

        if (!cardapioData.isEmpty()) {
            System.out.println("Cardápio do Restaurante:");
            for (Object[] produto : cardapioData) {
                String nomeProduto = (String) produto[0];
                double precoProduto = (double) produto[1];
                System.out.println(nomeProduto + " - R$ " + formatarValor(precoProduto));
            }
        } else {
            System.out.println("O cardápio está vazio.");
        }

        salvarCardapioEmArquivo(cardapioData);
    }

    private void salvarCardapioEmArquivo(List<Object[]> cardapio) {
        String caminhoArquivo = "src/comanda/produtos.txt";

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write("Cardápio do Restaurante:\n");

            for (Object[] produto : cardapio) {
                String nomeProduto = (String) produto[0];
                double precoProduto = (double) produto[1];

                writer.write(nomeProduto + " - R$ " + formatarValor(precoProduto) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatarValor(double valor) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(valor);
    }
}

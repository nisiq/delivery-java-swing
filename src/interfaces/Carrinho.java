// interfaces.Carrinho
package interfaces;

import components.Botao;
import components.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carrinho extends JFrame {
    private List<Object[]> produtosNoCarrinho;
    private Table table;
    private JLabel lblValorTotal;

    public Carrinho() {
        super("Carrinho");
        produtosNoCarrinho = new ArrayList<>();

        setLayout(new BorderLayout());

        table = new Table();

        Botao btnFinalizar = new Botao("Finalizar Compra");
        Botao btnRemover = new Botao("Remover Produto");
        Botao btnVoltar = new Botao("Voltar");

        // para remover o produto selecionado
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // remove o produto da tabela e do array
                    produtosNoCarrinho.remove(selectedRow);
                    table.carregarDados(produtosNoCarrinho);
                    atualizarValorTotal();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para remover.");
                }
            }
        });

        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // salva os produtos em um arquivo txt
                salvarProdutosEmArquivo();

                JOptionPane.showMessageDialog(null, "Compra finalizada. Confira sua comanda em 'comanda' > 'carrinho'");
                System.exit(0);

            }
        });


        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                CardapioUsuario cardapioUsuario = new CardapioUsuario();
                cardapioUsuario.setVisible(true);
            }
        });


        lblValorTotal = new JLabel("Valor Total: R$ 0.00");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnVoltar);
        buttonPanel.add(btnFinalizar);
        buttonPanel.add(btnRemover);

        buttonPanel.add(lblValorTotal);

        add(table, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public List<Object[]> getProdutosNoCarrinho() {
        return produtosNoCarrinho;
    }

    public void adicionarProdutoAoCarrinho(Object[] produto) {
        produtosNoCarrinho.add(produto);
        // Atualiza a tabela do carrinho
        table.carregarDados(produtosNoCarrinho);
        atualizarValorTotal();
    }

    public Table getTable() {
        return table;
    }

    // calcular e atualizar o valor total
    private void atualizarValorTotal() {
        double valorTotal = 0;
        for (Object[] produto : produtosNoCarrinho) {
            double valorProduto = (double) produto[1];
            valorTotal += valorProduto;
        }

        // valor total como format
        DecimalFormat df = new DecimalFormat("#,##0.00");
        lblValorTotal.setText("Valor Total: R$ " + df.format(valorTotal));
    }



    // salvar os produtos em um arquivo txt
    private void salvarProdutosEmArquivo() {
        String caminhoArquivo = "src/comanda/carrinho.txt";

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write("Produtos no Carrinho:\n");

            double valorTotal = 0.0;

            for (Object[] produto : produtosNoCarrinho) {
                String nomeProduto = (String) produto[0];
                double valorProduto = (double) produto[1];

                // adc o produto ao valor total
                valorTotal += valorProduto;

                // escreve o produto no arquivo
                writer.write(nomeProduto + " - R$ " + formatarValor(valorProduto) + "\n");
            }

            // valor total no final do arquivo
            writer.write("\nValor Total: R$ " + formatarValor(valorTotal));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // formatar o valor como uma String
    private String formatarValor(double valor) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(valor);
    }
}

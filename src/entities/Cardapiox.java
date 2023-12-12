package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
* Tudo que envolve as tabelas de cardapio
* */


public class Cardapiox {
    public static boolean cadastrarProduto(String nome, double valor, int idRestaurante) {
        try (Connection conn = database.conectar()) {

            // Inserir um novo produto no banco de dados (RESTAURANTE)
            String INSERIR = "INSERT INTO cardapio (produto, valor, restaurante_id) VALUES (?, ?, ?)";
            try (PreparedStatement salvar = conn.prepareStatement(INSERIR)) {

                salvar.setString(1, nome);
                salvar.setDouble(2, valor);
                salvar.setInt(3, idRestaurante);

                salvar.executeUpdate();

                System.out.println("Produto cadastrado com sucesso!!!");

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao salvar o produto no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao obter conexão com o banco de dados");
        }
        return false;
    }


    // retorna cardapio (RESTAURANTE)
    public static List<Object[]> obterCardapio(int idRestaurante) {
        List<Object[]> cardapio = new ArrayList<>();

        try (Connection conn = database.conectar()) {

            String CONSULTAR = "SELECT produto, valor FROM cardapio WHERE restaurante_id = ?";
            try (PreparedStatement consultar = conn.prepareStatement(CONSULTAR)) {
                consultar.setInt(1, idRestaurante);
                ResultSet resultado = consultar.executeQuery();

                while (resultado.next()) {
                    String produto = resultado.getString("produto");
                    double valor = resultado.getDouble("valor");

                    Object[] linha = {produto, valor};
                    cardapio.add(linha);
                }

                return cardapio;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao consultar o cardápio no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao obter conexão com o banco de dados");
        }

        return cardapio;
    }
    // cada item da lista é um array (produto e o valor)


    // remove produto selecionado (RESTAURANTE)
    public static boolean removerProduto(String produto) {
        try (Connection conn = database.conectar()) {
            String REMOVER = "DELETE FROM cardapio WHERE produto = ?";
            try (PreparedStatement remover = conn.prepareStatement(REMOVER)) {
                remover.setString(1, produto);
                remover.executeUpdate();

                System.out.println("Produto removido com sucesso");

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao remover o produto do banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao obter conexão com o banco de dados");
        }
        return false;
    }
}

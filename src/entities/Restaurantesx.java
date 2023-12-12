package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Restaurantesx {

    // puxar o nome dos restaurantes, armazenar em uma lista, pra exibir no Combobox...

    public static List<String> obterNomesRestaurantes() {
        List<String> nomesRestaurantes = new ArrayList<>();

        try (Connection conn = database.conectar()) {
            String QUERY_OBTER_NOMES = "SELECT usuario FROM restaurantes";
            try (PreparedStatement statement = conn.prepareStatement(QUERY_OBTER_NOMES)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    // itera sobre as linhas
                    while (resultSet.next()) {
                        nomesRestaurantes.add(resultSet.getString("usuario"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("erro ao obter nomes dos restaurantes do banco de dados");
        }

        System.out.println("nomes de restaurantes" + nomesRestaurantes);

        return nomesRestaurantes;
    }

    public static int obterIdRestaurante(String restauranteEscolhido) {
        try (Connection conn = database.conectar()) {
            String QUERY_OBTER_ID = "SELECT id FROM restaurantes WHERE usuario = ?";
            try (PreparedStatement statement = conn.prepareStatement(QUERY_OBTER_ID)) {
                statement.setString(1, restauranteEscolhido);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("erro ao obter ID do restaurante do banco de dados");
        }

        return -1;
    }

    public static List<Object[]> obterCardapio(int idRestaurante) {
        List<Object[]> cardapio = new ArrayList<>();

        try (Connection conn = database.conectar()) {
            String QUERY_OBTER_CARDAPIO = "SELECT produto, valor FROM cardapio WHERE restaurante_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(QUERY_OBTER_CARDAPIO)) {
                statement.setInt(1, idRestaurante);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String nomeProduto = resultSet.getString("produto");
                        double precoProduto = resultSet.getDouble("valor");
                        cardapio.add(new Object[]{nomeProduto, precoProduto});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("erro ao obter cardápio do restaurante do banco de dados");
        }

        System.out.println(cardapio);

        return cardapio;
    }

}

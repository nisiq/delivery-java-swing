package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginIn {

    public static void main(String[] args) {
        Connection conn = database.conectar();
        if (conn != null) {
            System.out.println("Sucesso!");
        }
    }

    // Validar login e obter o ID do usuário
    public static int validarLogin(String usuario, String senha) {
        try (Connection connection = database.conectar()) {
            // Verifica na tabela clientes
            String queryClientes = "SELECT id FROM clientes WHERE usuario = ? AND senha = ?";
            try (PreparedStatement preparedStatementClientes = connection.prepareStatement(queryClientes)) {
                preparedStatementClientes.setString(1, usuario);
                preparedStatementClientes.setString(2, senha);

                try (ResultSet resultSetClientes = preparedStatementClientes.executeQuery()) {
                    if (resultSetClientes.next()) {
                        return -resultSetClientes.getInt("id");
                    }
                }
            }

            // Se não encontrar na tabela clientes, verifica na tabela restaurantes
            String queryRestaurantes = "SELECT id FROM restaurantes WHERE usuario = ? AND senha = ?";
            try (PreparedStatement preparedStatementRestaurantes = connection.prepareStatement(queryRestaurantes)) {
                preparedStatementRestaurantes.setString(1, usuario);
                preparedStatementRestaurantes.setString(2, senha);

                try (ResultSet resultSetRestaurantes = preparedStatementRestaurantes.executeQuery()) {
                    if (resultSetRestaurantes.next()) {
                        // Restaurante autenticado com sucesso
                        return resultSetRestaurantes.getInt("id"); // Agora retorna o ID positivo
                    }
                }
            }

            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

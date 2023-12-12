package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Configurações - DB - Mysql

public class database {

    // caminho Driver JDBC
    private static final String CLASSE_DRIVE = "com.mysql.cj.jdbc.Driver";

    // autenticação
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    // url do meu banco
    private static final String URL_SERVIDOR = "jdbc:mysql://localhost:3306/delivery?useSSL=false&serverTimezone=UTC";


    public static Connection conectar() {
        try {
           // carrega o driver do banco de dados
            Class.forName(CLASSE_DRIVE);
            return DriverManager.getConnection(URL_SERVIDOR, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            System.exit(-7);
            return null;
        }
    }

    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}

package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*
Relacionado ao cadastro
Tanto para restaurante quanto cliente
 */

public class CriarConta {

    public static void main(String[] args) {
        Connection conn = database.conectar();
        if (conn != null) {
            System.out.println("sucessooo!");
        }
    }


    // registrar no db (RESTAURANTE)
    public static boolean CriarLoginRest(String usuario, String senha, String cep, String cnpj) {
        try (Connection connection = database.conectar()) {

            String INSERIR = "INSERT INTO restaurantes (usuario, senha, cep, cnpj) VALUES (?, ?, ?, ?)";
            try (Connection conn = database.conectar();
                 PreparedStatement salvar = conn.prepareStatement(INSERIR)) {

                salvar.setString(1, usuario);
                salvar.setString(2, senha);
                salvar.setString(3, cep);
                salvar.setString(4, cnpj);

                salvar.executeUpdate();

                System.out.println("O " + usuario + " foi criado com sucesso");
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("erro ao salvar o produto no banco de dados");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("erro ao obter entrada do usuario");
        }

        return false;
    }

    // registrar no db (CLIENTE)
    public static boolean CriarLoginCliente(String usuario, String senha, String cep, String cpf) {
        try (Connection connection = database.conectar()) {
            // Inserindo no banco de dados
            String INSERIR = "INSERT INTO clientes (usuario, senha, cep, cpf) VALUES (?, ?, ?, ?)";
            try (Connection conn = database.conectar();
                 PreparedStatement salvar = conn.prepareStatement(INSERIR)) {

                salvar.setString(1, usuario);
                salvar.setString(2, senha);
                salvar.setString(3, cep);
                salvar.setString(4, cpf);

                salvar.executeUpdate(); //att

                System.out.println("O " + usuario + " foi criado com sucesso");

                return true;

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao salvar o cliente no banco de dados");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("erro ao obter entrada do usuário");
        }

        return false;
    }


}

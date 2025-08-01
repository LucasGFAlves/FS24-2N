package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USUARIO = "root"; // O usuário que configuramos
    private static final String SENHA = "root"; // A senha que configuramos


    public static Connection conectar() {
        Connection conexao = null; // Inicializa a conexão como nula
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o bd: " + e.getMessage());
        } return conexao; // Retorna a conexão (pode ser null em caso de erro)
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) { // Verifica se a conexão não é nula antes de tentar fechar
            try {
                conexao.close(); // fecha a conexão
                System.out.println("Conexão com o bd fechada!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o bd: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection testeConexao = ConexaoDB.conectar();
        if (testeConexao != null) {
            // Se a conexão for bem-sucedida, podemos fechá-la
            ConexaoDB.fecharConexao(testeConexao);
        }
    }


}

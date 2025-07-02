package conexao; // Certifiquem-se de que o nome do pacote está correto

import java.sql.Connection; // Importa a interface Connection
import java.sql.DriverManager; // Importa a classe DriverManager para gerenciar drivers
import java.sql.SQLException; // Importa a exceção SQLException para tratamento de erros

public class ConexaoDB {

    // Informações para a conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
    private static final String USUARIO = "root"; // O usuário que configuramos
    private static final String SENHA = "alunodev"; // A senha que configuramos

    /**
     * Método para estabelecer uma conexão com o banco de dados.
     * @return Uma instância de Connection se a conexão for bem-sucedida, caso contrário, null.
     */
    public static Connection conectar() {
        Connection conexao = null; // Inicializa a conexão como nula

        try {
            // Tenta carregar o driver JDBC para MySQL (obsoleto em versões mais novas do JDBC, mas é bom conhecer)
            // Class.forName("com.mysql.cj.jdbc.Driver"); // Geralmente não é mais necessário com JDBC 4.0+

            // Tenta obter a conexão usando o DriverManager
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

        } catch (SQLException e) {
            // Captura e trata exceções de SQL (problemas com o banco de dados)
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            // Para depuração, você pode usar e.printStackTrace();
        }
        // Não vamos tratar o ClassNotFoundException porque o driver já deve estar no classpath
        // mas é bom saber que ele existe para outros casos.

        return conexao; // Retorna a conexão (pode ser null em caso de erro)
    }

    /**
     * Método para fechar uma conexão com o banco de dados.
     * @param conexao A instância de Connection a ser fechada.
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) { // Verifica se a conexão não é nula antes de tentar fechar
            try {
                conexao.close(); // Tenta fechar a conexão
                System.out.println("Conexão com o banco de dados fechada com sucesso!");
            } catch (SQLException e) {
                // Captura e trata exceções ao fechar a conexão
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    // Método main para testar a conexão diretamente
    public static void main(String[] args) {
        Connection testeConexao = ConexaoDB.conectar(); // Chama o método conectar
        if (testeConexao != null) {
            // Se a conexão for bem-sucedida, podemos fechá-la
            ConexaoDB.fecharConexao(testeConexao);
        }
    }
}
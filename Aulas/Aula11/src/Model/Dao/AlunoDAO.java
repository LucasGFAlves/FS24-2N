package Model.Dao;

import Conexao.ConexaoPostgresDB; // Ou ConexaoDB se estiver usando MySQL
import Model.Aluno; // Importa a classe Aluno
import Model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void setAluno(Aluno aluno) { // Agora recebe um objeto Aluno
        String sql = "INSERT INTO aluno (nome, idade, telefone) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoPostgresDB.conectar(); // Obtém a conexão
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                // Obtém os dados do objeto Aluno
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getTelefone());

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno '" + aluno.getNome() + "' inserido com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) ConexaoPostgresDB.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos após inserção: " + e.getMessage());
            }
        }
    }

    public List<Aluno> getAluno() { // Retorna uma lista de Alunos
        String sql = "SELECT id_aluno, nome, idade, telefone FROM aluno ORDER BY id_aluno";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunos = new ArrayList<>(); // Lista para armazenar os objetos Aluno
        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    // Cria um novo objeto Aluno para cada linha do resultado
                    int id = rs.getInt("id_aluno");
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    String telefone = rs.getString("telefone");

                    Aluno aluno = new Aluno(id, nome, idade, telefone); // Usa o construtor completo
                    alunos.add(aluno); // Adiciona o objeto à lista
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar alunos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) ConexaoPostgresDB.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos após consulta: " + e.getMessage());
            }
        }
        return alunos; // Retorna a lista de alunos
    }

    public void atualizar(Aluno aluno) { // Agora recebe um objeto Aluno
        String sql = "UPDATE aluno SET nome = ?, idade = ?, telefone = ? WHERE id_aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);

                // Obtém os dados do objeto Aluno
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getTelefone());
                stmt.setInt(4, aluno.getId()); // Usa o ID do objeto Aluno

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID " + aluno.getId() + " atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado com ID " + aluno.getId() + " para atualização.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) ConexaoPostgresDB.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos após atualização: " + e.getMessage());
            }
        }
    }

    public void removerAluno(int idAluno) { // Recebe apenas o ID para remoção
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idAluno);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID " + idAluno + " removido com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado com ID " + idAluno + " para remoção.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover aluno: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) ConexaoPostgresDB.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos após remoção: " + e.getMessage());
            }
        }
    }

    public Aluno buscarPorId(int idAluno) {
        String sql = "SELECT id_aluno, nome, idade, telefone FROM aluno WHERE id_aluno = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getInt("id_aluno"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("telefone")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Aluno por ID: " + e.getMessage());
        }
        return null;
    }
}

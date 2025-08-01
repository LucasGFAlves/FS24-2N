package Model.Dao;

import Conexao.ConexaoPostgresDB; // Ou o nome da sua classe de conexão
import Model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO { // Não implementa mais ILivroDAO

    public void inserir(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, isbn, disponivel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setString(4, livro.getIsbn());
            stmt.setBoolean(5, livro.isDisponivel());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    livro.setId(rs.getInt(1));
                }
            }
            System.out.println("Livro inserido: " + livro.getTitulo());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir livro: " + e.getMessage());
        }
    }

    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT id_livro, titulo, autor, ano_publicacao, isbn, disponivel FROM livros ORDER BY titulo";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao"),
                        rs.getString("isbn"),
                        rs.getBoolean("disponivel")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }
        return livros;
    }

    public Livro buscarPorId(int idLivro) {
        String sql = "SELECT id_livro, titulo, autor, ano_publicacao, isbn, disponivel FROM livros WHERE id_livro = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getInt("id_livro"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getInt("ano_publicacao"),
                            rs.getString("isbn"),
                            rs.getBoolean("disponivel")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro por ID: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano_publicacao = ?, isbn = ?, disponivel = ? WHERE id_livro = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setString(4, livro.getIsbn());
            stmt.setBoolean(5, livro.isDisponivel());
            stmt.setInt(6, livro.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro atualizado: " + livro.getTitulo());
            } else {
                System.out.println("Nenhum livro encontrado para atualização com ID: " + livro.getId());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void remover(int idLivro) {
        String sql = "DELETE FROM livros WHERE id_livro = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro removido com ID: " + idLivro);
            } else {
                System.out.println("Nenhum livro encontrado para remoção com ID: " + idLivro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover livro: " + e.getMessage());
        }
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT id_livro, titulo, autor, ano_publicacao, isbn, disponivel FROM livros WHERE titulo ILIKE ? ORDER BY titulo";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + titulo + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro(
                            rs.getInt("id_livro"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getInt("ano_publicacao"),
                            rs.getString("isbn"),
                            rs.getBoolean("disponivel")
                    );
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livros por título: " + e.getMessage());
        }
        return livros;
    }

    public List<Livro> buscarDisponiveis() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT id_livro, titulo, autor, ano_publicacao, isbn, disponivel FROM livros WHERE disponivel = TRUE ORDER BY titulo";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao"),
                        rs.getString("isbn"),
                        rs.getBoolean("disponivel")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livros disponíveis: " + e.getMessage());
        }
        return livros;
    }

    public void atualizarDisponibilidade(int idLivro, boolean disponivel) {
        String sql = "UPDATE livros SET disponivel = ? WHERE id_livro = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, disponivel);
            stmt.setInt(2, idLivro);
            stmt.executeUpdate();
            System.out.println("Disponibilidade do livro ID " + idLivro + " atualizada para " + disponivel);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar disponibilidade do livro: " + e.getMessage());
        }
    }
}
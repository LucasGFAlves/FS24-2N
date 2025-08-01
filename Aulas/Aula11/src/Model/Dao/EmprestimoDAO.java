package Model.Dao;

import Conexao.ConexaoPostgresDB;
import Model.Aluno;
import Model.Emprestimo;
import Model.Livro;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO { // Não implementa mais IEmprestimoDAO

    // DAOs necessários para buscar os objetos Livro e Aluno
    private LivroDAO livroDAO = new LivroDAO(); // Instância direta da classe concreta
    private AlunoDAO alunoDAO = new AlunoDAO(); // Instância direta da classe concreta

    public void registrarEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (id_livro, id_aluno, data_emprestimo) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, emprestimo.getLivro().getId());
            stmt.setInt(2, emprestimo.getAluno().getId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    emprestimo.setId(rs.getInt(1));
                }
            }
            System.out.println("Empréstimo registrado para o livro: " + emprestimo.getLivro().getTitulo() + " e aluno: " + emprestimo.getAluno().getNome());
        } catch (SQLException e) {
            System.err.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT id_emprestimo, id_livro, id_aluno, data_emprestimo, data_devolucao FROM emprestimos ORDER BY data_emprestimo DESC";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idEmprestimo = rs.getInt("id_emprestimo");
                int idLivro = rs.getInt("id_livro");
                int idAluno = rs.getInt("id_aluno");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;

                Livro livro = livroDAO.buscarPorId(idLivro);
                Aluno aluno = alunoDAO.buscarPorId(idAluno);

                if (livro != null && aluno != null) {
                    Emprestimo emprestimo = new Emprestimo(idEmprestimo, livro, aluno, dataEmprestimo, dataDevolucao);
                    emprestimos.add(emprestimo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }

    public Emprestimo buscarPorId(int idEmprestimo) {
        String sql = "SELECT id_emprestimo, id_livro, id_aluno, data_emprestimo, data_devolucao FROM emprestimos WHERE id_emprestimo = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmprestimo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idLivro = rs.getInt("id_livro");
                    int idAluno = rs.getInt("id_aluno");
                    LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                    LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;

                    Livro livro = livroDAO.buscarPorId(idLivro);
                    Aluno aluno = alunoDAO.buscarPorId(idAluno);

                    if (livro != null && aluno != null) {
                        return new Emprestimo(idEmprestimo, livro, aluno, dataEmprestimo, dataDevolucao);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimo por ID: " + e.getMessage());
        }
        return null;
    }

    public void registrarDevolucao(int idEmprestimo) {
        String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE id_emprestimo = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, idEmprestimo);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Devolução registrada para o empréstimo ID: " + idEmprestimo);
                Emprestimo emprestimo = buscarPorId(idEmprestimo);
                if (emprestimo != null && emprestimo.getLivro() != null) {
                    livroDAO.atualizarDisponibilidade(emprestimo.getLivro().getId(), true);
                }
            } else {
                System.out.println("Nenhum empréstimo encontrado com ID: " + idEmprestimo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao registrar devolução: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT id_emprestimo, id_livro, id_aluno, data_emprestimo, data_devolucao FROM emprestimos WHERE data_devolucao IS NULL ORDER BY data_emprestimo DESC";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idEmprestimo = rs.getInt("id_emprestimo");
                int idLivro = rs.getInt("id_livro");
                int idAluno = rs.getInt("id_aluno");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = null;

                Livro livro = livroDAO.buscarPorId(idLivro);
                Aluno aluno = alunoDAO.buscarPorId(idAluno);

                if (livro != null && aluno != null) {
                    Emprestimo emprestimo = new Emprestimo(idEmprestimo, livro, aluno, dataEmprestimo, dataDevolucao);
                    emprestimos.add(emprestimo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos ativos: " + e.getMessage());
        }
        return emprestimos;
    }

    public List<Emprestimo> listarEmprestimosPorAluno(int idAluno) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT id_emprestimo, id_livro, id_aluno, data_emprestimo, data_devolucao FROM emprestimos WHERE id_aluno = ? ORDER BY data_emprestimo DESC";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idEmprestimo = rs.getInt("id_emprestimo");
                    int idLivro = rs.getInt("id_livro");
                    LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                    LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;

                    Livro livro = livroDAO.buscarPorId(idLivro);
                    Aluno aluno = alunoDAO.buscarPorId(idAluno);

                    if (livro != null && aluno != null) {
                        Emprestimo emprestimo = new Emprestimo(idEmprestimo, livro, aluno, dataEmprestimo, dataDevolucao);
                        emprestimos.add(emprestimo);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos por aluno: " + e.getMessage());
        }
        return emprestimos;
    }

    public List<Emprestimo> listarEmprestimosPorLivro(int idLivro) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT id_emprestimo, id_livro, id_aluno, data_emprestimo, data_devolucao FROM emprestimos WHERE id_livro = ? ORDER BY data_emprestimo DESC";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idEmprestimo = rs.getInt("id_emprestimo");
                    int idAluno = rs.getInt("id_aluno");
                    LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                    LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;

                    Livro livro = livroDAO.buscarPorId(idLivro);
                    Aluno aluno = alunoDAO.buscarPorId(idAluno);

                    if (livro != null && aluno != null) {
                        Emprestimo emprestimo = new Emprestimo(idEmprestimo, livro, aluno, dataEmprestimo, dataDevolucao);
                        emprestimos.add(emprestimo);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos por livro: " + e.getMessage());
        }
        return emprestimos;
    }
}
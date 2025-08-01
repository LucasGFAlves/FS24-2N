package Controller;

// Importa as classes DAO concretas diretamente
import Model.Dao.AlunoDAO;
import Model.Dao.EmprestimoDAO;
import Model.Dao.LivroDAO;

import Model.Aluno;
import Model.Emprestimo;
import Model.Livro;

import java.time.LocalDate;
import java.util.List;

public class BibliotecaController {
    private LivroDAO livroDAO; // Referência direta à classe concreta
    private AlunoDAO alunoDAO; // Referência direta à classe concreta
    private EmprestimoDAO emprestimoDAO; // Referência direta à classe concreta

    public BibliotecaController() {
        this.livroDAO = new LivroDAO(); // Instancia a implementação concreta
        this.alunoDAO = new AlunoDAO();
        this.emprestimoDAO = new EmprestimoDAO();
    }

    // --- Métodos para Livros ---
    public void cadastrarLivro(String titulo, String autor, int ano, String isbn) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("O título do livro é obrigatório.");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("O ISBN do livro é obrigatório.");
        }
        Livro livro = new Livro(titulo, autor, ano, isbn, true);
        livroDAO.inserir(livro);
    }

    public List<Livro> listarTodosLivros() {
        return livroDAO.listarTodos();
    }

    public Livro buscarLivroPorId(int id) {
        return livroDAO.buscarPorId(id);
    }

    public void atualizarLivro(int id, String titulo, String autor, int ano, String isbn, boolean disponivel) throws Exception {
        Livro livro = new Livro(id, titulo, autor, ano, isbn, disponivel);
        livroDAO.atualizar(livro);
    }

    public void removerLivro(int id) {
        livroDAO.remover(id);
    }

    public List<Livro> buscarLivroPorTitulo(String titulo) {
        return livroDAO.buscarPorTitulo(titulo);
    }

    public List<Livro> listarLivrosDisponiveis() {
        return livroDAO.buscarDisponiveis();
    }


    // --- Métodos para Alunos ---
    /*public void cadastrarAluno(String nome, int idade, String telefone, String email) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome do aluno é obrigatório.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("O email do aluno é obrigatório.");
        }
        Aluno aluno = new Aluno(nome, idade, telefone, email);
        alunoDAO.inserir(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodos();
    }

    public Aluno buscarAlunoPorId(int id) {
        return alunoDAO.buscarPorId(id);
    }

    public void atualizarAluno(int id, String nome, int idade, String telefone, String email) throws Exception {
        Aluno aluno = new Aluno(id, nome, idade, telefone, email);
        alunoDAO.atualizar(aluno);
    }

    public void removerAluno(int id) {
        alunoDAO.remover(id);
    }

    public List<Aluno> buscarAlunoPorNome(String nome) {
        return alunoDAO.buscarPorNome(nome);
    }

    // --- Métodos para Empréstimos ---
    public void realizarEmprestimo(int idLivro, int idAluno) throws Exception {
        Livro livro = livroDAO.buscarPorId(idLivro);
        Aluno aluno = alunoDAO.buscarPorId(idAluno);

        if (livro == null) {
            throw new Exception("Livro não encontrado com ID: " + idLivro);
        }
        if (aluno == null) {
            throw new Exception("Aluno não encontrado com ID: " + idAluno);
        }
        if (!livro.isDisponivel()) {
            throw new Exception("Livro '" + livro.getTitulo() + "' não está disponível para empréstimo.");
        }

        Emprestimo emprestimo = new Emprestimo(livro, aluno, LocalDate.now());
        emprestimoDAO.registrarEmprestimo(emprestimo);
        livroDAO.atualizarDisponibilidade(livro.getId(), false);
    }

    public void registrarDevolucao(int idEmprestimo) throws Exception {
        Emprestimo emprestimo = emprestimoDAO.buscarPorId(idEmprestimo);
        if (emprestimo == null) {
            throw new Exception("Empréstimo não encontrado com ID: " + idEmprestimo);
        }
        if (emprestimo.getDataDevolucao() != null) {
            throw new Exception("Este empréstimo já foi devolvido.");
        }
        emprestimoDAO.registrarDevolucao(idEmprestimo);
    }

    public List<Emprestimo> listarTodosEmprestimos() {
        return emprestimoDAO.listarTodos();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoDAO.listarEmprestimosAtivos();
    }

    public List<Emprestimo> listarEmprestimosPorAluno(int idAluno) {
        return emprestimoDAO.listarEmprestimosPorAluno(idAluno);
    }

    public List<Emprestimo> listarEmprestimosPorLivro(int idLivro) {
        return emprestimoDAO.listarEmprestimosPorLivro(idLivro);
    }*/
}
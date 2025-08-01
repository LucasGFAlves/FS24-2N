package Model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private Livro livro; // Objeto Livro
    private Aluno aluno; // Objeto Aluno
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao; // Pode ser null

    // Construtor completo
    public Emprestimo(int id, Livro livro, Aluno aluno, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livro = livro;
        this.aluno = aluno;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    // Construtor para novas inserções (sem ID e sem data de devolução inicial)
    public Emprestimo(Livro livro, Aluno aluno, LocalDate dataEmprestimo) {
        this.livro = livro;
        this.aluno = aluno;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null; // Inicialmente sem data de devolução
    }

    // Getters
    public int getId() { return id; }
    public Livro getLivro() { return livro; }
    public Aluno getAluno() { return aluno; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setLivro(Livro livro) { this.livro = livro; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    @Override
    public String toString() {
        return "Emprestimo [ID=" + id + ", Livro=" + (livro != null ? livro.getTitulo() : "N/A") +
                ", Aluno=" + (aluno != null ? aluno.getNome() : "N/A") +
                ", Data Empréstimo=" + dataEmprestimo +
                ", Data Devolução=" + (dataDevolucao != null ? dataDevolucao : "Em Aberto") + "]";
    }
}
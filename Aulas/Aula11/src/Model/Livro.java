package Model;

// Importar java.time.LocalDate se for usar para datas
// import java.time.LocalDate;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String isbn;
    private boolean disponivel;

    // Construtor completo (para carregar do banco)
    public Livro(int id, String titulo, String autor, int anoPublicacao, String isbn, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
        this.disponivel = disponivel;
    }

    // Construtor para novas inserções (sem ID, que é auto-gerado)
    public Livro(String titulo, String autor, int anoPublicacao, String isbn, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
        this.disponivel = disponivel;
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public String getIsbn() { return isbn; }
    public boolean isDisponivel() { return disponivel; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return "Livro [ID=" + id + ", Título=" + titulo + ", Autor=" + autor + ", Ano=" + anoPublicacao + ", ISBN=" + isbn + ", Disponível=" + disponivel + "]";
    }
}
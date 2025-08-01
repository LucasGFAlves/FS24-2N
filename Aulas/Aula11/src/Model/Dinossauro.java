package Model;

import java.time.LocalDate;

public class Dinossauro {
    private int id;
    private String nome;
    private String especie;
    private double peso; // Em kg
    private double altura; // Em metros
    private LocalDate dataDescoberta; // Data de "descoberta" do fóssil, por exemplo

    // Construtor completo
    public Dinossauro(int id, String nome, String especie, double peso, double altura, LocalDate dataDescoberta) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
        this.altura = altura;
        this.dataDescoberta = dataDescoberta;
    }

    // Construtor para novas inserções (sem ID)
    public Dinossauro(String nome, String especie, double peso, double altura, LocalDate dataDescoberta) {
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
        this.altura = altura;
        this.dataDescoberta = dataDescoberta;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public double getPeso() { return peso; }
    public double getAltura() { return altura; }
    public LocalDate getDataDescoberta() { return dataDescoberta; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setPeso(double peso) { this.peso = peso; }
    public void setAltura(double altura) { this.altura = altura; }
    public void setDataDescoberta(LocalDate dataDescoberta) { this.dataDescoberta = dataDescoberta; }

    @Override
    public String toString() {
        return "Dinossauro [ID=" + id + ", Nome=" + nome + ", Espécie=" + especie +
                ", Peso=" + peso + "kg, Altura=" + altura + "m, Descoberta=" + dataDescoberta + "]";
    }
}
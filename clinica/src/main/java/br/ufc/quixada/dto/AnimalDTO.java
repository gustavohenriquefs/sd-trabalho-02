package br.ufc.quixada.dto;

public class AnimalDTO {
    private String nome;
    private int idade;
    private String cpfDono;

    public AnimalDTO() {}

    public AnimalDTO(String nome, int idade, String cpfDono) {
        this.nome = nome;
        this.idade = idade;
        this.cpfDono = cpfDono;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getCpfDono() { return cpfDono; }

    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setCpfDono(String cpfDono) { this.cpfDono = cpfDono; }
}

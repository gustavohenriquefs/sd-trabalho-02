package br.ufc.quixada.entities;

public class Gato extends Animal {

    private static final long serialVersionUID = 1L;

    private String cor;

    public Gato() throws java.rmi.RemoteException {
        super();
    }

    public Gato(String nome, int idade, String cpfDono, String cor) throws java.rmi.RemoteException {
        super(nome, idade, cpfDono);
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        String cpfDonoMasked = cpfDono.substring(0, 3) + "********";
        return "Nome: " + nome + ", Idade: " + idade + ", CPF do Dono: " + cpfDonoMasked;
    }
}

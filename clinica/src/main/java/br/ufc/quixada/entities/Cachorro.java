package br.ufc.quixada.entities;

public class Cachorro extends Animal {

    private static final long serialVersionUID = 1L;

    private String raca;

    public Cachorro() throws java.rmi.RemoteException {
        super();
    }

    public Cachorro(String nome, int idade, String cpfDono, String raca) throws java.rmi.RemoteException {
        super(nome, idade, cpfDono);
        this.raca = raca;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        String cpfDonoMasked = cpfDono.substring(0, 3) + "********";
        return "Nome: " + nome + ", Idade: " + idade + ", CPF do Dono: " + cpfDonoMasked;
    }
}

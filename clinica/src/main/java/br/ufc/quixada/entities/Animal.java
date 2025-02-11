package br.ufc.quixada.entities;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.ufc.quixada.models.IAnimal;

public abstract class Animal extends UnicastRemoteObject implements IAnimal, Serializable {

    private static final long serialVersionUID = 1L;

    protected String nome;
    protected int idade;
    protected String cpfDono;

    public Animal() throws RemoteException {
        this.nome = "";
        this.idade = 0;
        this.cpfDono = "";
    }

    public Animal(String nome, int idade, String cpfDono) throws RemoteException {
        this.nome = nome;
        this.idade = idade;
        this.cpfDono = cpfDono;
    }

    @Override
    public String getNome() throws RemoteException {
        return nome;
    }

    @Override
    public int getIdade() throws RemoteException {
        return idade;
    }

    @Override
    public String getCpfDono() throws RemoteException {
        return cpfDono;
    }

    @Override
    public void setNome(String nome) throws RemoteException {
        this.nome = nome;
    }

    @Override
    public void setIdade(int idade) throws RemoteException {
        this.idade = idade;
    }

    @Override
    public void setCpfDono(String cpfDono) throws RemoteException {
        this.cpfDono = cpfDono;
    }
}

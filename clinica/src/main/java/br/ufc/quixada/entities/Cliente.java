package br.ufc.quixada.entities;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.ufc.quixada.models.ICliente;

public class Cliente extends UnicastRemoteObject implements ICliente, Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private int idade;
    private String cpf;

    public Cliente() throws java.rmi.RemoteException {
        super();
    }

    public Cliente(String nome, int idade, String cpf) throws java.rmi.RemoteException {
        super();
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public String getNome() throws RemoteException {
        return nome;
    }

    public int getIdade() throws RemoteException  {
        return idade;
    }

    public String getCpf() throws RemoteException  {
        return cpf;
    }

    public void setNome(String nome) throws RemoteException  {
        this.nome = nome;
    }

    public void setIdade(int idade) throws RemoteException  {
        this.idade = idade;
    }

    public void setCpf(String cpf) throws RemoteException  {
        this.cpf = cpf;
    }

}

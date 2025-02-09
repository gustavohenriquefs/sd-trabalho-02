package br.ufc.quixada.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICliente extends Remote {
    public String getNome() throws RemoteException;
    public int getIdade() throws RemoteException;
    public String getCpf() throws RemoteException;
    public void setNome(String nome) throws RemoteException;
    public void setIdade(int idade) throws RemoteException;
    public void setCpf(String cpf) throws RemoteException;
}

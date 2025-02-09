package br.ufc.quixada.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {
  public String getNome() throws RemoteException;
  public int getIdade() throws RemoteException;
  public String getCpfDono() throws RemoteException;
  public void setNome(String nome) throws RemoteException;
  public void setIdade(int idade) throws RemoteException;
  public void setCpfDono(String cpfDono) throws RemoteException;
}

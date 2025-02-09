package br.ufc.quixada.models;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.List;

public interface IEstoque extends Remote, Serializable {
    public void adicionarAnimal(IAnimal animal) throws java.rmi.RemoteException;
    public void adicionarCliente(ICliente cliente) throws java.rmi.RemoteException;
    public void removerAnimal(IAnimal animal) throws java.rmi.RemoteException;
    public List<IAnimal> getAnimais() throws java.rmi.RemoteException;
    public String listarAnimais() throws java.rmi.RemoteException;
    public String listarAnimaisPorDono(String cpfDono) throws java.rmi.RemoteException;
}

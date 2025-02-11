package br.ufc.quixada;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufc.quixada.entities.Estoque;
import br.ufc.quixada.models.IEstoque;

public class Server {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(4999);
            IEstoque estoque = new Estoque();
            registry.rebind("Estoque", estoque);
            System.out.println("Servidor RMI rodando...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

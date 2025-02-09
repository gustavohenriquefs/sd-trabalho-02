package br.ufc.quixada;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufc.quixada.entities.Estoque;
import br.ufc.quixada.models.IEstoque;

public class Server {

    public static void main(String[] args) {
        try {
            // Criar e iniciar o RMI Registry na porta 5000
            Registry registry = LocateRegistry.createRegistry(5000);

            // Criar instância do objeto remoto
            IEstoque estoque = new Estoque();

            // Registrar o objeto no RMI Registry
            registry.rebind("Estoque", estoque);

            System.out.println("Servidor RMI rodando...");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

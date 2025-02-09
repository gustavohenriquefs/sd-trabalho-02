package br.ufc.quixada.entities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.models.IAnimal;
import br.ufc.quixada.models.ICliente;
import br.ufc.quixada.models.IEstoque;

public class Estoque extends UnicastRemoteObject implements IEstoque {

    private static final long serialVersionUID = 1L;

    private final List<IAnimal> animais;
    private final List<ICliente> clientes = new ArrayList<>();

    public Estoque() throws RemoteException {
        this.animais = new ArrayList<>();
    }

    public List<IAnimal> getAnimais() {
        return new ArrayList<>(animais);
    }

    @Override
    public void adicionarCliente(ICliente cliente) throws RemoteException {
        clientes.add(cliente);
        System.out.println("Cliente adicionado ao estoque: " + cliente.getNome());
    }

    @Override
    public void adicionarAnimal(IAnimal animal) throws RemoteException {
        boolean clienteExiste = false;
        
        for (ICliente c : clientes) {
            if (c.getCpf().equals(animal.getCpfDono())) {
                clienteExiste = true;
                break;
            }
        }

        if (!clienteExiste) {
            throw new RemoteException("Cliente não encontrado.");
        }

        animais.add(animal);

        System.out.println("Animal adicionado ao estoque: " + animal.getNome());
    }

    @Override
    public void removerAnimal(IAnimal animal) throws RemoteException {
        animais.removeIf(a -> {
            try {
                return a.getNome().equals(animal.getNome());
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public String listarAnimais() throws RemoteException {
        if (animais.isEmpty()) {
            return "Nenhum animal no estoque.";
        }
        StringBuilder lista = new StringBuilder("Animais no estoque:\n");
        for (IAnimal a : animais) {
            lista.append(a.getNome()).append("\n");
        }
        return lista.toString();
    }

    @Override
    public String listarAnimaisPorDono(String cpfDono) throws RemoteException {
        StringBuilder lista = new StringBuilder("Animais do dono " + cpfDono + ":\n");
        for (IAnimal a : animais) {
            if (a.getCpfDono().equals(cpfDono)) {
                lista.append(a.getNome()).append("\n");
            }
        }
        return lista.length() == 0 ? "Nenhum animal encontrado para este dono." : lista.toString();
    }
}

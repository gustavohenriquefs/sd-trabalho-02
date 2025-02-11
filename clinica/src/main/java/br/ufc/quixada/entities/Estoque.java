package br.ufc.quixada.entities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.quixada.data.RequestResponseProtocol;
import br.ufc.quixada.dto.AnimalDTO;
import br.ufc.quixada.dto.ClienteDTO;
import br.ufc.quixada.models.IEstoque;

public class Estoque extends UnicastRemoteObject implements IEstoque {

    private static final long serialVersionUID = 1L;
    private final List<ClienteDTO> clientes;
    private final List<AnimalDTO> animais;

    public Estoque() throws RemoteException {
        super();
        this.clientes = new ArrayList<>();
        this.animais = new ArrayList<>();
    }

    @Override
    public String doOperation(String jsonRequest) throws RemoteException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RequestResponseProtocol request = RequestResponseProtocol.fromJson(jsonRequest);

            String methodId = request.getMethodId();
            String responsePayload = "";

            switch (methodId) {
                case "adicionarCliente":
                    ClienteDTO cliente = objectMapper.readValue(request.getPayload(), ClienteDTO.class);
                    clientes.add(cliente);
                    responsePayload = "Cliente adicionado com sucesso.";
                    break;

                case "adicionarAnimal":
                    AnimalDTO animal = objectMapper.readValue(request.getPayload(), AnimalDTO.class);
                    boolean clienteExiste = clientes.stream().anyMatch(c -> c.getCpf().equals(animal.getCpfDono()));

                    if (!clienteExiste) {
                        responsePayload = "Erro: Cliente não encontrado.";
                    } else {
                        animais.add(animal);
                        responsePayload = "Animal adicionado com sucesso.";
                    }
                    break;

                case "listarAnimais":
                    responsePayload = objectMapper.writeValueAsString(animais);
                    break;

                case "listarAnimaisPorDono":
                    String cpfDono = request.getPayload();
                    List<AnimalDTO> animaisDono = new ArrayList<>();
                    for (AnimalDTO a : animais) {
                        if (a.getCpfDono().equals(cpfDono)) {
                            animaisDono.add(a);
                        }
                    }
                    responsePayload = objectMapper.writeValueAsString(animaisDono);
                    break;

                default:
                    responsePayload = "Método não encontrado.";
            }

            return new RequestResponseProtocol("Estoque", methodId, responsePayload).toJson();

        } catch (Exception e) {
            e.printStackTrace();
            return "{ \"error\": \"Erro no processamento da requisição.\" }";
        }
    }
}

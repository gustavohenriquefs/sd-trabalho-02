package br.ufc.quixada.entities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.quixada.data.RemoteObjectRef;
import br.ufc.quixada.data.RequestResponseProtocol;
import br.ufc.quixada.dto.AnimalDTO;
import br.ufc.quixada.dto.ClienteDTO;
import br.ufc.quixada.models.IEstoque;

public class Estoque extends UnicastRemoteObject implements IEstoque {

    private static final long serialVersionUID = 1L;

    // Variáveis para armazenar a última requisição e resposta (simulação do fluxo do protocolo)
    private byte[] lastRequest;
    private byte[] lastReply;

    private final List<ClienteDTO> clientes;
    private final List<AnimalDTO> animais;

    public Estoque() throws RemoteException {
        super();
        this.clientes = new ArrayList<>();
        this.animais = new ArrayList<>();
    }

    @Override
    public byte[] doOperation(RemoteObjectRef o, int method, byte[] arguments) throws RemoteException {
        this.lastRequest = arguments;

        byte[] receivedBytes = getRequest();
        String jsonRequest = new String(receivedBytes, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        RequestResponseProtocol requestProtocol;

        try {
            requestProtocol = RequestResponseProtocol.fromJson(jsonRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            String errorJson = "{\"error\":\"JSON de requisição inválido.\"}";
            return errorJson.getBytes(StandardCharsets.UTF_8);
        }

        String methodIdStr = requestProtocol.getMethodId();
        String responsePayload = "";

        try {
            switch (methodIdStr) {
                case "adicionarCliente":
                    ClienteDTO cliente = mapper.readValue(requestProtocol.getPayload(), ClienteDTO.class);
                    clientes.add(cliente);
                    responsePayload = "Cliente adicionado com sucesso.";
                    break;
                case "adicionarAnimal":
                    AnimalDTO animal = mapper.readValue(requestProtocol.getPayload(), AnimalDTO.class);
                    boolean clienteExiste = clientes.stream().anyMatch(c -> c.getCpf().equals(animal.getCpfDono()));
                    if (clienteExiste) {
                        animais.add(animal);
                        responsePayload = "Animal adicionado com sucesso.";
                    } else {
                        responsePayload = "Erro: Cliente não encontrado.";
                    }
                    break;
                case "listarAnimais":
                    responsePayload = mapper.writeValueAsString(animais);
                    break;
                case "listarAnimaisPorDono":
                    String cpfDono = requestProtocol.getPayload();
                    List<AnimalDTO> animaisDono = new ArrayList<>();
                    for (AnimalDTO a : animais) {
                        if (a.getCpfDono().equals(cpfDono)) {
                            animaisDono.add(a);
                        }
                    }
                    responsePayload = mapper.writeValueAsString(animaisDono);
                    break;
                default:
                    responsePayload = "Método não encontrado.";
                    break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            responsePayload = "Erro durante o processamento da requisição.";
        }

        RequestResponseProtocol responseProtocol = new RequestResponseProtocol(o.getObjectName(), methodIdStr, responsePayload);
        String jsonResponse;
        try {
            jsonResponse = responseProtocol.toJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"error\":\"Erro ao gerar JSON de resposta.\"}";
        }

        byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

        try {
            sendReply(responseBytes, InetAddress.getLocalHost(), 0);
        } catch (UnknownHostException | RemoteException e) {
            e.printStackTrace();
        }

        this.lastReply = responseBytes;

        return responseBytes;
    }

    @Override
    public byte[] getRequest() throws RemoteException {
        return this.lastRequest;
    }

    @Override
    public void sendReply(byte[] reply, InetAddress clientHost, int clientPort) throws RemoteException {
        this.lastReply = reply;
        System.out.println("Enviando resposta para " + clientHost.getHostAddress() + ":" + clientPort);
    }
}

package br.ufc.quixada;

import java.nio.charset.StandardCharsets;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.quixada.data.RemoteObjectRef;
import br.ufc.quixada.data.RequestResponseProtocol;
import br.ufc.quixada.dto.AnimalDTO;
import br.ufc.quixada.dto.ClienteDTO;
import br.ufc.quixada.models.IEstoque;

public class Cliente {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4999);
            IEstoque estoque = (IEstoque) registry.lookup("Estoque");

            ObjectMapper objectMapper = new ObjectMapper();
            Scanner scanner = new Scanner(System.in);

            RemoteObjectRef remoteRef = new RemoteObjectRef("Estoque");

            while (true) {
                System.out.println("\n========== MENU ==========");
                System.out.println("1. Adicionar Cliente");
                System.out.println("2. Adicionar Animal");
                System.out.println("3. Listar Animais");
                System.out.println("4. Listar Animais por Dono");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                String methodId = "";
                String payload = "";

                switch (opcao) {
                    case 1:
                        System.out.print("Nome do Cliente: ");
                        String nomeCliente = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idadeCliente = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpfCliente = scanner.nextLine();
                        ClienteDTO cliente = new ClienteDTO(nomeCliente, idadeCliente, cpfCliente);
                        payload = objectMapper.writeValueAsString(cliente);
                        methodId = "adicionarCliente";
                        break;
                    case 2:
                        System.out.print("Nome do Animal: ");
                        String nomeAnimal = scanner.nextLine();
                        System.out.print("Idade do Animal: ");
                        int idadeAnimal = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("CPF do Dono: ");
                        String cpfDono = scanner.nextLine();
                        AnimalDTO animal = new AnimalDTO(nomeAnimal, idadeAnimal, cpfDono);
                        payload = objectMapper.writeValueAsString(animal);
                        methodId = "adicionarAnimal";
                        break;
                    case 3:
                        methodId = "listarAnimais";
                        payload = "";
                        break;
                    case 4:
                        System.out.print("CPF do Dono: ");
                        String cpfConsulta = scanner.nextLine();
                        methodId = "listarAnimaisPorDono";
                        payload = cpfConsulta;
                        break;
                    case 5:
                        System.out.println("Encerrando o cliente...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        continue;
                }

                // Cria um objeto de requisição em que encapsula a referência do objeto remoto, o método a ser chamado e o payload(argumentos do método)
                RequestResponseProtocol request = new RequestResponseProtocol("Estoque", methodId, payload);
                // Converte o objeto para JSON
                String jsonRequest = request.toJson();
                // Converte o JSON para bytes
                byte[] requestBytes = jsonRequest.getBytes(StandardCharsets.UTF_8);

                // Chama o método remoto que processa a operação
                byte[] responseBytes = estoque.doOperation(remoteRef, 0, requestBytes);
                String jsonResponse = new String(responseBytes, StandardCharsets.UTF_8);

                // Desserializa a resposta para extrair o payload e exibi-lo
                RequestResponseProtocol response = RequestResponseProtocol.fromJson(jsonResponse);
                System.out.println("Resposta: " + response.getPayload());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

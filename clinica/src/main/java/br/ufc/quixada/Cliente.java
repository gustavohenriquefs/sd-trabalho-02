package br.ufc.quixada;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

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

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nomeCliente = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idadeCliente = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpfCliente = scanner.nextLine();
                        ClienteDTO cliente = new ClienteDTO(nomeCliente, idadeCliente, cpfCliente);
                        RequestResponseProtocol reqCliente = new RequestResponseProtocol("Estoque", "adicionarCliente", objectMapper.writeValueAsString(cliente));
                        System.out.println("Resposta: " + estoque.doOperation(reqCliente.toJson()));
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
                        RequestResponseProtocol reqAnimal = new RequestResponseProtocol("Estoque", "adicionarAnimal", objectMapper.writeValueAsString(animal));
                        System.out.println("Resposta: " + estoque.doOperation(reqAnimal.toJson()));
                        break;
                    case 3:
                        RequestResponseProtocol reqLista = new RequestResponseProtocol("Estoque", "listarAnimais", "");
                        System.out.println("Animais no Estoque: " + estoque.doOperation(reqLista.toJson()));
                        break;
                    case 4:
                        System.out.print("CPF do Dono: ");
                        String cpfConsulta = scanner.nextLine();
                        RequestResponseProtocol reqDono = new RequestResponseProtocol("Estoque", "listarAnimaisPorDono", cpfConsulta);
                        System.out.println("Resposta: " + estoque.doOperation(reqDono.toJson()));
                        break;
                    case 5:
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package br.ufc.quixada;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import br.ufc.quixada.entities.Cachorro;
import br.ufc.quixada.entities.Cliente;
import br.ufc.quixada.entities.Coelho;
import br.ufc.quixada.entities.Gato;
import br.ufc.quixada.models.IAnimal;
import br.ufc.quixada.models.ICliente;
import br.ufc.quixada.models.IEstoque;

public class Client {

    public static void main(String[] args) {
        try {
            // Localiza o registro RMI no servidor (porta 5000)
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);

            // Busca os objetos remotos
            IEstoque estoque = (IEstoque) registry.lookup("Estoque");

            // Scanner para entrada de dados
            Scanner scanner = new Scanner(System.in);
            boolean sair = false;

            while (!sair) {
                // Menu de opções
                System.out.println("\n===== MENU =====");
                System.out.println("1. Adicionar Cliente");
                System.out.println("2. Adicionar Animal");
                System.out.println("3. Consultar Animais de um Cliente");
                System.out.println("4. Listar Animais no Estoque");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();  // Consome a quebra de linha

                String cpfCliente = "";

                switch (opcao) {
                    case 1:
                        // Adicionar Cliente
                        System.out.print("Digite o nome do cliente: ");
                        String nomeCliente = scanner.nextLine();
                        System.out.println("Digite sua idade:");
                        int idade = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o CPF do cliente: ");
                        cpfCliente = scanner.nextLine();
                        ICliente cliente = new Cliente(nomeCliente, idade, cpfCliente);
                        estoque.adicionarCliente(cliente);
                        System.out.println("Cliente " + nomeCliente + " adicionado com sucesso ao estoque!");

                        break;

                    case 2:
                        System.out.println("Selecione a espécie do animal:");

                        String[] especies = {"Cachorro", "Gato", "Coelho"};

                        for (int i = 0; i < especies.length; i++) {
                            System.out.println((i + 1) + ". " + especies[i]);
                        }

                        System.out.print("Escolha uma opção: ");
                        int especie = scanner.nextInt();
                        scanner.nextLine();

                        String nomeEspecie = especies[especie - 1];

                        // Adicionar Animal
                        System.out.print("Digite o nome do " + nomeEspecie + ": ");
                        String nomeAnimal = scanner.nextLine();

                        System.out.println("Digite a idade do " + nomeEspecie + ":");
                        int idadeAnimal = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Digite o CPF do dono do animal: ");
                        String cpfDono = scanner.nextLine();

                        if (especie == 1) {
                            IAnimal animal = new Cachorro();
                            animal.setNome(nomeAnimal);
                            animal.setIdade(idadeAnimal);
                            animal.setCpfDono(cpfDono);
                            estoque.adicionarAnimal(animal);
                        } else if (especie == 2) {
                            IAnimal animal = new Gato();
                            animal.setNome(nomeAnimal);
                            animal.setIdade(idadeAnimal);
                            animal.setCpfDono(cpfDono);
                            estoque.adicionarAnimal(animal);
                        } else if (especie == 3) {
                            IAnimal animal = new Coelho();
                            animal.setNome(nomeAnimal);
                            animal.setIdade(idadeAnimal);
                            animal.setCpfDono(cpfDono);
                            estoque.adicionarAnimal(animal);
                        }

                        System.out.println("Animal " + nomeAnimal + " adicionado com sucesso ao estoque!");

                        break;

                    case 3:
                        // Consultar Animais de um Cliente
                        System.out.print("Digite o CPF do cliente para consultar os animais: ");

                        cpfCliente = scanner.nextLine();

                        System.out.println(estoque.listarAnimaisPorDono(cpfCliente));
                        break;

                    case 4:
                        // Listar Animais no Estoque
                        System.out.println(estoque.listarAnimais());
                        break;

                    case 5:
                        // Sair
                        sair = true;
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

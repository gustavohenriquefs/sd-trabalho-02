## Animal

- É uma class abstrata que representa um animal.
- Possui os atributos `nome`, `idade` e `cpfDono`.

## Cachorro, Gato e Coelhos

- São classes que herdam de `Animal`.

## Estoque

- Apresenta uma lista de animais e clientes.
- Possui métodos doOperation que apresenta um switch case para realizar operações de:
-- Adicionar um cliente
-- Adicionar um animal
-- listar animais
-- listar animais de um cliente

## Server

- Usa RMI em que registra a porta 4999
- Instancia um objeto de Estoque e registra ele no RMI

## Client

- Usa RMI e obtém o objeto de Estoque registrado no RMI pela porta 4999
- Possui um menu para realizar operações de:
-- Adicionar um cliente
-- Adicionar um animal
-- listar animais
-- listar animais de um cliente
- chamando os métodos do objeto de Estoque


## RequestResponseProtocol

- Classe que guarda o objeto de referência, o id do método e o payload.
- Possui métodos para converter o objeto para JSON e de JSON para objeto.
- Usado para enviar e receber mensagens entre o cliente e o servidor.

## AnimalDTO e ClienteDTO

- Usado para troca de mensagens entre o cliente e o servidor.
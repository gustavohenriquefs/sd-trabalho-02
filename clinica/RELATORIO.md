## Animal

- É uma class abstrata que representa um animal.
- Possui os atributos `nome`, `idade` e `cpfDono`.

## Cachorro, Gato e Coelhos

- São classes que herdam de `Animal`.

## Estoque

- Implementa a interface `IEstoque` que possui o método `doOperation`, `getRequest` e `sendReply`.
- doOperation: Recebe um objeto de referência, o id do método e o payload. Realiza a operação de acordo com o id do método e retorna o resultado.
- getRequest: Retorna a última requisição recebida.
- sendReply: Envia a resposta para o cliente.
- **Fluxo da Comunicação:**  
  1. **Envio da Requisição:**  
     - O cliente envia uma mensagem que é convertida para JSON usando o *ObjectMapper*.
     - Essa mensagem contém o id do método e os dados (payload) da operação.
  
  2. **Processamento no Servidor:**  
     - O JSON recebido é convertido para um objeto.
     - Com base no id do método, o servidor executa a operação solicitada.
  
  3. **Envio da Resposta:**  
     - O resultado da operação é encapsulado novamente em um objeto.
     - O objeto é convertido para JSON e enviado ao cliente pelo método `sendReply`.

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

# RemoteObjectRef

- Classe que guarda o nome do objeto remoto.
- Usado para enviar e receber mensagens entre o cliente e o servidor.
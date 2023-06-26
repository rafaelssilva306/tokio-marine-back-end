# Teste Tokio Marine back-end

O projeto "teste-angular-marine-back-end" é um sistema de agendamento de transferências financeiras que calcula taxas baseadas em operações dos tipos A, B, C e D. O projeto foi desenvolvido em Java 17, utilizando o framework Spring e suas dependências JPA, Boot DevTools e Spring Web. O banco de dados utilizado é o H2.

### Instruções de instalação
Siga as instruções abaixo para baixar e executar o projeto em sua máquina local.

### Pré-requisitos
Java Development Kit (JDK) 17
Maven
Git
### Passo a passo

1. Clone o repositório "teste-angular-marine-back-end" para o seu ambiente local usando o seguinte comando: `git clone <URL_DO_REPOSITÓRIO>`
2. Navegue até o diretório do projeto.
3. Execute o comando Maven para baixar as dependências do projeto: `mvn clean install`.
4. Inicie o servidor embutido do Spring Boot: `mvn spring-boot:run`.
   
Após executar esses passos, o projeto será compilado e estará sendo executado na porta 8080.

### Endpoints
A seguir, estão os endpoints disponíveis no projeto:
- GET /transacao: Retorna a lista de transações financeiras.
- POST /agendarTransferencia: Permite cadastrar um agendamento de transferência. Os detalhes da transferência devem ser enviados no corpo da requisição no formato JSON. Exemplo de corpo de requisição: `
{
  "contaOrigem": "123456",
  "contaDestino": "789012",
  "dataTransferencia": "2023-06-30",
  "valor": 1000.00

}`

Certifique-se de substituir os valores acima pelos dados reais da transferência que deseja agendar.

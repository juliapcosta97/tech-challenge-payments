# tech-challenge-payments
## FIAP Tech Challenge - Payments Module


### Escopo

- Responsável por realizar a cobrança de um pedido gerado anteriormente.
- Geração do link / QRCode de pagamento e da gestão do status de pagamento.

### Requisitos

- O sistema deverá possuir uma opção de pagamento integrada, no caso, para MVP, a forma de pagamento oferecida será via QRCode do Mercado Pago.

### Exemplos

- QRCode

![Captura de Tela 2024-01-27 às 16 19 15](https://github.com/juliapcosta97/tech-challenge-payments/assets/15149920/13ba5400-d68f-4a91-8dfc-930156f2591e)

- Link de pagamento
![Captura de Tela 2024-01-27 às 16 21 47](https://github.com/juliapcosta97/tech-challenge-payments/assets/15149920/9666e014-a507-41ee-aa39-a7205cfae2bb)


### Artefatos
- [Repositório público de relatórios do SonarCloud](https://sonarcloud.io/project/overview?id=juliapcosta97_tech-challenge-payments)

### Entregáveis
Dando continuidade ao desenvolvimento do software para a lanchonete, teremos as seguintes melhorias e alterações:
- [x] Refatore o projeto separando em ao menos 3 microsserviços:
    - ~~Pedido: responsável por retornar as informações necessárias para montar um pedido.~~
    - **Pagamento: responsável por realizar a cobrança de um pedido gerado anteriormente.**
    - ~~Produção**: responsável por acompanhar a produção/fila de pedidos e atualização de status.~~
- [x] Lembre-se de trabalhar com bancos de dados para cada aplicação. Use ao menos um banco de dados NoSQL e um SQL; caso queira fazer com mais bancos, você pode decidir quais utilizar. 
- [x] Ao refatorar, os microsserviços devem conter testes unitários usando BDD com no mínimo 80% de cobertura de testes por cada microsserviço.
- [x] Seus repositórios devem ser separados para cada aplicação e devem respeitar as seguintes regras:
    - [x] Main protegida.
    - [x] PR para branch main deve validar o build da aplicação, e qualidade de código via sonarqube.
    - [x] Automatize o deploy dos seus microsserviços.

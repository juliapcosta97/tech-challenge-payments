# tech-challenge-payments
## FIAP Tech Challenge - Payments Module


### Escopo

- Responsável por realizar a cobrança de um pedido gerado anteriormente.
- Geração do link / QRCode de pagamento e da gestão do status de pagamento.

### Requisitos

- O sistema deverá possuir uma opção de pagamento integrada, no caso, para MVP, a forma de pagamento oferecida será via QRCode do Mercado Pago.

### Tutorial de Execução do código

- Executar o comando docker-compose -f docker-compose.yml up -d para subir a aplicação e os serviços que ela tem dependência (Kafka)

### Relatórios OWASP

- Antes: [ZAP Scanning Report](https://github.com/juliapcosta97/tech-challenge-payments/blob/main/owasp/2024-03-16-ZAP-Report-.html)
- Depois: [ZAP Scanning Report](https://github.com/juliapcosta97/tech-challenge-payments/blob/main/owasp/2024-03-16-ZAP-Report-.html)

### Exemplos

- QRCode

![Captura de Tela 2024-01-27 às 16 19 15](https://github.com/juliapcosta97/tech-challenge-payments/assets/15149920/13ba5400-d68f-4a91-8dfc-930156f2591e)

- Link de pagamento
![Captura de Tela 2024-01-27 às 16 21 47](https://github.com/juliapcosta97/tech-challenge-payments/assets/15149920/9666e014-a507-41ee-aa39-a7205cfae2bb)

- Endpoint de callback para o Webhook

  curl --location 'http://localhost:8080/api/payment/notify?status=SUCCESS&order_id=2253526'

  POssiveis status: SUCCESS / PENDING / FAILURE


### Artefatos
- [Repositório público de relatórios do SonarCloud](https://sonarcloud.io/project/overview?id=juliapcosta97_tech-challenge-payments)




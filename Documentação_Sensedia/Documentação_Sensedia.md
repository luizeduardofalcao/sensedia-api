# Documentação_Sensedia

Documentação do projeto **sensedia**: API REST de consulta de pedidos (orders), construída com Spring Boot.

## Visão geral

A aplicação expõe um endpoint HTTP para buscar um pedido pelo identificador. Os dados são mantidos em memória (mapa estático), sem banco de dados.

| Item | Valor |
|------|--------|
| Nome da aplicação | `sensedia` |
| Group / Artifact | `com.example` / `sensedia` |
| Versão | `0.0.1-SNAPSHOT` |
| Java | 17 |
| Spring Boot | 4.1.1 |
| Build | Maven |

## Stack

- Spring Boot Web MVC (`spring-boot-starter-webmvc`)
- Testes com `spring-boot-starter-webmvc-test`
- Maven Wrapper (`mvnw` / `mvnw.cmd`)

## Estrutura do código

```
src/main/java/com/example/sensedia/
├── SensediaApplication.java          # Ponto de entrada
├── controller/OrderController.java   # Camada HTTP
├── service/OrderService.java         # Regras e dados em memória
└── dto/OrderResponse.java            # Contrato de resposta
```

### SensediaApplication

Classe anotada com `@SpringBootApplication`. O scan inclui os pacotes:

- `com.example.sensedia`
- `br.com.aula`

### OrderController

- `@RestController`
- Base path: `/orders`
- Injeta `OrderService` via construtor

### OrderService

Armazena pedidos em um `Map<Long, OrderResponse>` imutável (`Map.of`). A busca retorna `Optional<OrderResponse>`.

### OrderResponse

Record Java com os campos:

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `orderId` | `Long` | Identificador do pedido |
| `status` | `String` | Situação atual |
| `deliveryMethod` | `String` | Modalidade de entrega |

## API

### Consultar pedido por ID

```
GET /orders/{orderId}
```

**Parâmetros**

| Nome | Local | Tipo | Obrigatório |
|------|-------|------|-------------|
| `orderId` | path | `Long` | Sim |

**Respostas**

| HTTP | Quando | Corpo |
|------|--------|-------|
| `200 OK` | Pedido encontrado | JSON `OrderResponse` |
| `404 Not Found` | Pedido inexistente | vazio |

**Exemplo — pedido existente**

```http
GET /orders/12345
```

```json
{
  "orderId": 12345,
  "status": "IN_TRANSIT",
  "deliveryMethod": "EXPRESS"
}
```

**Exemplo — pedido inexistente**

```http
GET /orders/99999
```

Resposta: `404 Not Found`.

## Dados em memória

Pedidos disponíveis na inicialização:

| orderId | status | deliveryMethod |
|---------|--------|----------------|
| 12345 | `IN_TRANSIT` | `EXPRESS` |
| 12346 | `DELIVERED` | `STANDARD` |

Qualquer outro ID resulta em 404.

## Configuração

Arquivo: `src/main/java/com/example/sensedia/resources/application.properties`

```properties
spring.application.name=sensedia
```

A porta padrão do Spring Boot é `8080`, salvo alteração local.

## Como executar

Na raiz do projeto:

```bash
.\mvnw.cmd spring-boot:run
```

Ou, após gerar o artefato:

```bash
.\mvnw.cmd clean package
java -jar target/sensedia-0.0.1-SNAPSHOT.jar
```

## Testes

Existe um teste de contexto em `src/test/java/com/example/sensedia/SensediaApplicationTests.java` (`contextLoads`).

```bash
.\mvnw.cmd test
```

## Fluxo de uma requisição

1. O cliente chama `GET /orders/{orderId}`.
2. `OrderController.findById` recebe o path variable.
3. `OrderService.findById` consulta o mapa em memória.
4. Se houver registro, a API devolve `200` com `OrderResponse`.
5. Se não houver, a API devolve `404`.

## Observações

- Não há persistência, autenticação nem validação de entrada além do tipo do path variable.
- O pacote `br.com.aula` está no scan da aplicação, mas não há classes desse pacote neste repositório.
- Este documento descreve o estado atual do código; atualize-o quando a API ou os dados mudarem.

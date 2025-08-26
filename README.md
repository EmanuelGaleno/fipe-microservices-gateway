# API Gateway - Sistema FIPE

Este projeto implementa a **API-1** (Gateway/Orquestradora) para integração com o serviço FIPE, seguindo os requisitos especificados baseados em um teste.

## 🏗️ Arquitetura

- **API-1**: Gateway/Orquestradora (este projeto)
- **API-2**: Processadora de filas (projeto separado, em desenvolvimento)
- **Banco**: PostgreSQL com Flyway para migrações
- **Fila**: RabbitMQ para processamento assíncrono
- **Cache**: Redis para otimização de consultas
- **Segurança**: Autenticação por API Key

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Security**
- **Spring Data JPA**
- **Spring AMQP (RabbitMQ)**
- **Spring Data Redis**
- **Flyway**
- **PostgreSQL**
- **Lombok**
- **Swagger/OpenAPI**

## 📋 Funcionalidades Implementadas

### ✅ Requisitos Atendidos

1. **1.1** ✅ Serviço REST para acionar "carga inicial" dos dados de veículos
2. **1.2** ✅ Lógica para buscar "marcas" no serviço da FIPE
3. **1.3** ✅ Envio para "fila" as "marcas" para API-2 consumir
4. **1.6** ✅ Serviço REST para buscar "marcas" armazenadas no banco
5. **1.7** ✅ Serviço REST para buscar "códigos", "modelos" e "observações" por marca
6. **1.8** ✅ Serviço REST para salvar dados alterados do veículo
7. **1.9** ✅ Camada de cache com Redis
8. **1.10** ✅ Autenticação para proteger endpoints

### 🔄 Requisitos da API-2 (não implementado aqui, em desenvolvimento)

4. **1.4** Lógica para buscar "códigos" e "modelos" dos veículos
5. **1.5** Lógica para salvar no banco de dados

## 🛠️ Configuração do Ambiente

### Pré-requisitos

- Java 17+
- Maven 3.6+
- Docker e Docker Compose

### 1. Clone o repositório

```bash
git clone <repository-url>
cd api-gateway
```

### 2. Levante os serviços de infraestrutura

```bash
docker-compose up -d
```

**Serviços disponíveis:**
- PostgreSQL: `localhost:5432`
- RabbitMQ: `localhost:5672` (Management: `localhost:15672`)
- Redis: `localhost:6379`

### 3. Execute a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 🔐 Autenticação

Todos os endpoints (exceto Swagger e Actuator) requerem autenticação por API Key.

**Header necessário:**
```
X-API-Key: fipe-api-key-2024
```

## 📚 Endpoints da API

### Swagger/OpenAPI
- **URL**: `http://localhost:8080/swagger-ui/index.html`
- **Documentação**: `http://localhost:8080/v3/api-docs`

### 1. Carga Inicial
```http
POST /carga-inicial
X-API-Key: fipe-api-key-2024
Content-Type: application/json

{
  "types": ["CARROS", "MOTOS", "CAMINHOES"]
}
```

### 2. Listar Marcas
```http
GET /marcas?type=CARROS
X-API-Key: fipe-api-key-2024
```

### 3. Listar Veículos por Marca
```http
GET /veiculos?type=CARROS&brand=59&page=0&size=50
X-API-Key: fipe-api-key-2024
```

### 4. Atualizar Veículo
```http
PUT /veiculos/{id}
X-API-Key: fipe-api-key-2024
Content-Type: application/json

{
  "model": "Novo Nome do Modelo",
  "observacoes": "Observações atualizadas"
}
```

## 🗄️ Estrutura do Banco

### Tabelas

- **`brand`**: Marcas de veículos (FIPE)
- **`vehicle`**: Modelos de veículos (FIPE)

### Migrações

- **V1**: Criação das tabelas
- **V2**: Dados de exemplo (VW - VolksWagen)

## 🔍 Monitoramento

- **Actuator**: `http://localhost:8080/actuator`
- **Health Check**: `http://localhost:8080/actuator/health`

## 🧪 Testes

```bash
# Testes unitários
mvn test

# Testes de integração
mvn verify
```

## 📁 Estrutura do Projeto

```
src/main/java/com/r2r/fipe/gateway/
├── config/          # Configurações (Security, Redis, RabbitMQ, etc.)
├── controller/      # Controllers REST
├── service/         # Lógica de negócio
├── repository/      # Acesso a dados
├── entity/          # Entidades JPA
├── dto/            # Objetos de transferência
├── mapper/         # Conversores Entity ↔ DTO
├── client/         # Cliente HTTP para FIPE
├── enums/          # Enumerações
├── exception/      # Exceções customizadas
└── advice/         # Tratamento global de exceções
```

## 🚨 Troubleshooting

### Problemas Comuns

1. **Erro de conexão com banco**: Verifique se o PostgreSQL está rodando
2. **Erro de conexão com RabbitMQ**: Verifique se o RabbitMQ está rodando
3. **Erro de conexão com Redis**: Verifique se o Redis está rodando
4. **Erro de autenticação**: Verifique se o header `X-API-Key` está correto

### Logs

```bash
# Logs da aplicação
tail -f logs/application.log

# Logs dos containers
docker-compose logs -f
```

## 🔄 Próximos Passos

Para completar o sistema, é necessário implementar a **API-2**(em desenvolvimento) que irá:

1. Consumir mensagens da fila RabbitMQ
2. Buscar modelos na API FIPE
3. Salvar dados no banco PostgreSQL

## 📝 Licença

Este projeto foi desenvolvido para fins de teste e avaliação técnica.

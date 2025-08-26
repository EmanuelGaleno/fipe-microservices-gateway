# Instruções de Execução - API Gateway FIPE

## 🚀 Execução Rápida

### 1. Pré-requisitos
- Java 17+ instalado
- Maven 3.6+ instalado
- Docker e Docker Compose instalados

### 2. Levantar Infraestrutura
```bash
# Na pasta raiz do projeto
docker-compose up -d
```

### 3. Executar Aplicação
```bash
# Perfil de desenvolvimento (recomendado para testes)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Ou perfil padrão
mvn spring-boot:run
```

### 4. Acessar Aplicação
- **API**: http://localhost:8080
- **Swagger**: http://localhost:8080/swagger-ui/index.html
- **Health Check**: http://localhost:8080/actuator/health

## 🔧 Configurações Detalhadas

### Variáveis de Ambiente
```bash
# Para produção, defina:
export FIPE_API_KEY=sua-api-key-secreta
export SPRING_PROFILES_ACTIVE=prod
```

### Perfis Disponíveis
- **dev**: Desenvolvimento (DDL auto-update, logs detalhados)
- **test**: Testes (H2 in-memory, sem Flyway)
- **prod**: Produção (DDL validate, Flyway habilitado)

## 📊 Verificação de Funcionamento

### 1. Verificar Serviços
```bash
# Status dos containers
docker-compose ps

# Logs dos serviços
docker-compose logs -f postgres
docker-compose logs -f rabbitmq
docker-compose logs -f redis
```

### 2. Testar Conexões
```bash
# PostgreSQL
psql -h localhost -U fipe_user -d fipe

# RabbitMQ Management
# Acesse: http://localhost:15672
# Usuário: fipe_guest
# Senha: fipepass

# Redis
redis-cli -h localhost -p 6379 ping
```

### 3. Testar Endpoints
```bash
# Health Check
curl http://localhost:8080/actuator/health

# Listar Marcas (com autenticação)
curl -H "X-API-Key: dev-api-key-2024" \
     "http://localhost:8080/marcas?type=CARROS"

# Carga Inicial
curl -X POST \
     -H "X-API-Key: dev-api-key-2024" \
     -H "Content-Type: application/json" \
     -d '{"types":["CARROS"]}' \
     http://localhost:8080/carga-inicial
```

## 🧪 Execução de Testes

### Testes Unitários
```bash
mvn test
```

### Testes de Integração
```bash
mvn verify
```

### Testes com Cobertura
```bash
mvn jacoco:report
```

## 🐛 Troubleshooting

### Problemas Comuns

#### 1. Erro de Conexão com Banco
```bash
# Verificar se PostgreSQL está rodando
docker-compose ps postgres

# Verificar logs
docker-compose logs postgres

# Testar conexão
psql -h localhost -U fipe_user -d fipe
```

#### 2. Erro de Conexão com RabbitMQ
```bash
# Verificar se RabbitMQ está rodando
docker-compose ps rabbitmq

# Verificar logs
docker-compose logs rabbitmq

# Acessar management
# http://localhost:15672
```

#### 3. Erro de Conexão com Redis
```bash
# Verificar se Redis está rodando
docker-compose ps redis

# Verificar logs
docker-compose logs redis

# Testar conexão
redis-cli -h localhost -p 6379 ping
```

#### 4. Erro de Autenticação
```bash
# Verificar se o header X-API-Key está correto
# Para desenvolvimento: dev-api-key-2024
# Para produção: verificar variável FIPE_API_KEY
```

#### 5. Erro de Porta em Uso
```bash
# Verificar se a porta 8080 está livre
netstat -an | grep 8080

# Ou usar porta alternativa
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8081"
```

### Logs da Aplicação
```bash
# Logs em tempo real
tail -f logs/api-gateway.log

# Ou via console (perfil dev)
# Os logs aparecem no terminal onde a aplicação foi executada
```

## 🔄 Reinicialização

### Reiniciar Apenas a Aplicação
```bash
# Parar aplicação (Ctrl+C)
# Executar novamente
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Reiniciar Toda a Infraestrutura
```bash
# Parar todos os serviços
docker-compose down

# Remover volumes (cuidado: perde dados)
docker-compose down -v

# Levantar novamente
docker-compose up -d
```

## 📈 Monitoramento

### Métricas Disponíveis
- **Health**: http://localhost:8080/actuator/health
- **Info**: http://localhost:8080/actuator/info
- **Metrics**: http://localhost:8080/actuator/metrics
- **Environment**: http://localhost:8080/actuator/env

### Logs Estruturados
Os logs incluem:
- Timestamp
- Nível de log
- Thread
- Logger
- Mensagem
- Stack trace (quando aplicável)

## 🚨 Segurança

### API Key
- **Desenvolvimento**: `dev-api-key-2024`
- **Teste**: `test-api-key`
- **Produção**: Configurar via variável de ambiente `FIPE_API_KEY`

### Endpoints Públicos
- `/swagger-ui/**` - Interface Swagger
- `/v3/api-docs/**` - Documentação OpenAPI
- `/actuator/**` - Endpoints de monitoramento

### Endpoints Protegidos
Todos os outros endpoints requerem o header `X-API-Key` válido.

## 📝 Notas Importantes

1. **Primeira Execução**: O Flyway criará as tabelas automaticamente
2. **Dados de Exemplo**: O arquivo V2__seed_tables.sql insere dados de exemplo
3. **Cache**: Redis é usado para cache de consultas (TTL: 30 minutos)
4. **Fila**: RabbitMQ é usado para processamento assíncrono
5. **Banco**: PostgreSQL é usado para persistência dos dados

## 🔗 Links Úteis

- **Documentação FIPE**: https://deividfortuna.github.io/fipe/
- **Spring Boot**: https://spring.io/projects/spring-boot
- **Docker Compose**: https://docs.docker.com/compose/
- **PostgreSQL**: https://www.postgresql.org/docs/
- **RabbitMQ**: https://www.rabbitmq.com/documentation.html
- **Redis**: https://redis.io/documentation

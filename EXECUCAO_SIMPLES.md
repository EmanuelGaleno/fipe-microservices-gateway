# 🚀 Execução Simples da API Gateway FIPE

## 🔧 **Problemas Identificados e Soluções**

### **1. Warnings de Logging Corrigidos**
- ✅ Configuração do logback corrigida
- ✅ Appender CONSOLE configurado corretamente
- ✅ JBoss logging configurado

### **2. Configurações Otimizadas**
- ✅ Perfis de ambiente configurados
- ✅ Configuração simplificada para testes

## 🎯 **Como Executar (Passo a Passo)**

### **Opção 1: Perfil Simple (Recomendado para Primeira Execução)**
```bash
# Usa H2 in-memory, sem dependências externas
mvn spring-boot:run -Dspring-boot.run.profiles=simple
```

### **Opção 2: Perfil de Desenvolvimento**
```bash
# Primeiro levante a infraestrutura
docker-compose up -d

# Execute com perfil dev
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### **Opção 3: Perfil Padrão (Produção)**
```bash
# Primeiro levante a infraestrutura
docker-compose up -d

# Execute com perfil padrão
mvn spring-boot:run
```

## 📊 **Perfis Disponíveis**

| Perfil | Banco | Cache | Flyway | Uso |
|--------|-------|-------|--------|-----|
| `simple` | H2 (memória) | Desabilitado | Desabilitado | **Testes iniciais** |
| `dev` | PostgreSQL | Redis | Desabilitado | Desenvolvimento |
| `test` | H2 (memória) | Desabilitado | Desabilitado | Testes unitários |
| `prod` | PostgreSQL | Redis | Habilitado | Produção |

## 🧪 **Teste Rápido com Perfil Simple**

### **1. Execute a aplicação:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=simple
```

### **2. Teste os endpoints:**
```bash
# Health Check
curl http://localhost:8080/actuator/health

# Swagger UI
# Acesse: http://localhost:8080/swagger-ui/index.html

# Listar Marcas (com autenticação)
curl -H "X-API-Key: simple-api-key" \
     "http://localhost:8080/marcas?type=CARROS"
```

## 🔍 **Verificação de Funcionamento**

### **Logs Esperados:**
```
14:19:30,351 [main] INFO  com.r2r.fipe.gateway.ApiGatewayApplication - Started ApiGatewayApplication
14:19:30,352 [main] INFO  org.springframework.boot.web.embedded.tomcat.TomcatWebServer - Tomcat started on port(s): 8080
```

### **Endpoints Disponíveis:**
- ✅ `/actuator/health` - Health check
- ✅ `/swagger-ui/index.html` - Interface Swagger
- ✅ `/v3/api-docs` - Documentação OpenAPI
- ✅ `/carga-inicial` - Carga inicial (com autenticação)
- ✅ `/marcas` - Listar marcas (com autenticação)
- ✅ `/veiculos` - Listar veículos (com autenticação)

## 🚨 **Troubleshooting**

### **Se a aplicação não iniciar:**

1. **Verifique se a porta 8080 está livre:**
```bash
netstat -an | grep 8080
```

2. **Use porta alternativa:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=simple -Dserver.port=8081
```

3. **Verifique logs detalhados:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=simple -Dlogging.level.root=DEBUG
```

### **Se houver erro de dependências:**

1. **Limpe e recompile:**
```bash
mvn clean compile
```

2. **Atualize dependências:**
```bash
mvn dependency:resolve
```

## 🎉 **Sucesso!**

Quando a aplicação estiver funcionando, você verá:
- ✅ Spring Boot iniciado
- ✅ Tomcat rodando na porta 8080
- ✅ Endpoints disponíveis
- ✅ Swagger UI acessível

## 🔄 **Próximos Passos**

1. **Teste com perfil simple** ✅
2. **Configure infraestrutura** (PostgreSQL, Redis, RabbitMQ)
3. **Teste com perfil dev**
4. **Implemente API-2** para consumir filas

---

**API Key para perfil simple:** `simple-api-key`
**URL da aplicação:** http://localhost:8080
**Swagger UI:** http://localhost:8080/swagger-ui/index.html

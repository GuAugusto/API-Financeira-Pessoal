# 💰 API Financeira Pessoal

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

API REST profissional para gestão financeira pessoal, desenvolvida com **Java 17 + Spring Boot 3**, seguindo **padrões bancários** utilizados por Itaú, Nubank e outras instituições financeiras.

## 🚀 Funcionalidades

✅ **Gestão completa de contas financeiras** (CRUD)  
✅ **Banco de dados H2** em memória (sem instalação necessária)  
✅ **Migrações automáticas** com Flyway (controle de versão do banco)  
✅ **Documentação automática** com Swagger/OpenAPI  
✅ **Logs estruturados em JSON** para auditoria  
✅ **Autenticação Spring Security** com BCrypt  
✅ **Seed automático** de usuário admin  
✅ **Docker e Docker Compose** para deployment  
✅ **Arquitetura limpa** (Controller → Service → Repository)

## 📋 Pré-requisitos

- **Java 17** ou superior
- **Maven 3.8+**
- (Opcional) **Docker** e **Docker Compose**

## 🔧 Como executar

### Opção 1: Maven (Recomendado para desenvolvimento)

```bash
# Clone o repositório
git clone <seu-repositorio>
cd api-financeira-pessoal

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

### Opção 2: JAR Executável

```bash
# Gere o JAR
mvn clean package -DskipTests

# Execute
java -jar target/api-financeira-pessoal-1.0.0.jar
```

### Opção 3: Docker

```bash
# Build da imagem
mvn clean package -DskipTests
docker build -t api-financeira .

# Execute o container
docker run -p 8080:8080 api-financeira
```

### Opção 4: Docker Compose

```bash
# Suba toda a stack
mvn clean package -DskipTests
docker compose up -d

# Verifique os logs
docker compose logs -f

# Pare os containers
docker compose down
```

## 🌐 Acessos

Após iniciar a aplicação:

| Serviço | URL | Descrição |
|---------|-----|-----------|
| **API** | http://localhost:8080 | Endpoints REST |
| **Swagger UI** | http://localhost:8080/swagger-ui.html | Documentação interativa |
| **H2 Console** | http://localhost:8080/h2-console | Console do banco de dados |
| **API Docs** | http://localhost:8080/v3/api-docs | OpenAPI JSON |

### 🔐 Credenciais H2 Console

- **JDBC URL**: `jdbc:h2:mem:financedb`
- **Username**: `sa`
- **Password**: (deixe em branco)

### 👤 Usuário Admin (criado automaticamente)

- **Username**: `admin`
- **Password**: `admin123`
- **Role**: `ADMIN`

## 📡 Endpoints da API

### Contas Financeiras

#### Criar nova conta
```bash
POST /accounts
Content-Type: application/json

{
  "name": "Carteira",
  "balance": 150.00
}
```

#### Listar todas as contas
```bash
GET /accounts
```

#### Buscar conta por ID
```bash
GET /accounts/{id}
```

#### Atualizar conta
```bash
PUT /accounts/{id}
Content-Type: application/json

{
  "name": "Carteira Atualizada",
  "balance": 300.00
}
```

#### Deletar conta
```bash
DELETE /accounts/{id}
```

### Exemplos com cURL

```bash
# Criar conta
curl -X POST http://localhost:8080/accounts \
  -H "Content-Type: application/json" \
  -d '{"name":"Nubank","balance":1000.00}'

# Listar contas
curl http://localhost:8080/accounts

# Buscar conta específica
curl http://localhost:8080/accounts/1

# Atualizar conta
curl -X PUT http://localhost:8080/accounts/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Nubank Premium","balance":2000.00}'

# Deletar conta
curl -X DELETE http://localhost:8080/accounts/1
```

## 🏗️ Arquitetura do Projeto

```
api-financeira-pessoal/
├── src/
│   ├── main/
│   │   ├── java/com/finance/api/
│   │   │   ├── controller/         # Controllers REST
│   │   │   │   └── AccountController.java
│   │   │   ├── service/            # Lógica de negócio
│   │   │   │   └── AccountService.java
│   │   │   ├── repository/         # Acesso a dados (JPA)
│   │   │   │   ├── AccountRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── model/              # Entidades JPA
│   │   │   │   ├── Account.java
│   │   │   │   └── User.java
│   │   │   ├── dto/                # Data Transfer Objects
│   │   │   │   └── AccountDTO.java
│   │   │   ├── config/             # Configurações
│   │   │   │   ├── SwaggerConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── DataInitializer.java    # Seed de dados
│   │   │   └── FinanceApiApplication.java
│   │   └── resources/
│   │       ├── db/migration/       # Migrações Flyway
│   │       │   ├── V1__init.sql
│   │       │   └── V2__insert_data.sql
│   │       ├── application.properties
│   │       └── logback-spring.xml
│   └── test/
│       └── java/com/finance/api/
├── target/                         # Arquivos compilados
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── .gitignore
└── README.md
```

### Camadas da Aplicação

1. **Controller**: Recebe requisições HTTP e retorna respostas
2. **Service**: Contém a lógica de negócio
3. **Repository**: Acessa o banco de dados via JPA
4. **Model**: Entidades que representam as tabelas
5. **DTO**: Objetos para transferência de dados
6. **Config**: Configurações do Spring (Security, Swagger, etc)

## 🗃️ Banco de Dados

### Flyway Migrations

O Flyway gerencia automaticamente as migrações do banco:

- **V1__init.sql**: Cria as tabelas `users` e `accounts`
- **V2__insert_data.sql**: Insere dados iniciais (4 contas exemplo)

As migrações são executadas automaticamente na inicialização.

### Estrutura das Tabelas

#### Tabela `users`
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);
```

#### Tabela `accounts`
```sql
CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 📊 Logs Estruturados

A aplicação gera logs em formato JSON para facilitar auditoria e monitoramento:

```json
{
  "timestamp": "2025-12-11T20:41:03.125",
  "level": "INFO",
  "thread": "main",
  "logger": "com.finance.api.controller.AccountController",
  "message": "Account created successfully with id: 1",
  "context": "default"
}
```

Isso é o **padrão bancário** usado por Itaú, Nubank, Inter, C6 e Bradesco.

## 🔒 Segurança

### Recursos de Segurança Implementados

✅ **BCrypt** para hash de senhas  
✅ **Spring Security** configurado  
✅ **CSRF** desabilitado para APIs REST  
✅ **Headers de segurança** configurados  
✅ **Endpoints públicos** documentados

### Endpoints Públicos (sem autenticação)

- `/h2-console/**` - Console do banco
- `/swagger-ui/**` - Documentação Swagger
- `/v3/api-docs/**` - OpenAPI JSON
- `/accounts/**` - Endpoints de contas

## 🧪 Testes

### Executar todos os testes
```bash
mvn test
```

### Executar com cobertura
```bash
mvn clean test jacoco:report
```

O relatório de cobertura estará em `target/site/jacoco/index.html`

## 📦 Build e Deploy

### Build para Produção

```bash
# Gera JAR otimizado
mvn clean package -DskipTests

# JAR estará em:
# target/api-financeira-pessoal-1.0.0.jar
```

### Deploy com Docker

```bash
# Build da imagem
docker build -t api-financeira:1.0.0 .

# Tag para registry
docker tag api-financeira:1.0.0 seu-registry/api-financeira:1.0.0

# Push para registry
docker push seu-registry/api-financeira:1.0.0
```

## 🔍 Monitoramento

### Health Check

```bash
curl http://localhost:8080/actuator/health
```

### Métricas (adicionar Spring Boot Actuator)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## 🚀 Melhorias Futuras

- [ ] Adicionar transações financeiras (débito/crédito)
- [ ] Implementar categorias de gastos
- [ ] Criar relatórios financeiros
- [ ] Adicionar autenticação JWT
- [ ] Implementar paginação nos endpoints
- [ ] Adicionar testes de integração
- [ ] Configurar CI/CD com GitHub Actions
- [ ] Adicionar cache com Redis
- [ ] Implementar rate limiting
- [ ] Adicionar notificações por email

## 📚 Documentação Adicional

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Flyway Documentation](https://flywaydb.org/documentation/)
- [Swagger/OpenAPI](https://swagger.io/specification/)
- [H2 Database](http://www.h2database.com/html/main.html)

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

Desenvolvido com ☕ e Spring Boot

---

⭐ Se este projeto foi útil, considere dar uma estrela!

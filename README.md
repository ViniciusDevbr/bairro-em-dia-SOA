# Bairro em Dia — Backend SOA

## 1. Descrição do projeto

O **Bairro em Dia** é uma solução CitizenTech desenvolvida para facilitar o registro e o acompanhamento de problemas em espaços públicos, como iluminação pública danificada, descarte irregular de lixo, buracos nas vias e problemas em calçadas.

Este repositório contém exclusivamente a implementação da camada de serviços (SOA), desenvolvida em Java com Spring Boot.

O backend é responsável pelo gerenciamento dos dados relacionados às ocorrências urbanas, bairros, categorias, confirmações e histórico de atualizações.

## 2. Objetivos

- Disponibilizar uma API REST para gerenciamento de ocorrências.
- Organizar as responsabilidades da camada de serviços.
- Permitir o cadastro e a consulta de bairros e categorias.
- Registrar e acompanhar ocorrências urbanas.
- Gerenciar alterações de status e histórico.
- Armazenar os dados em um banco PostgreSQL.
- Documentar automaticamente os endpoints.
- Implementar testes automatizados.

## 3. Tecnologias utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java 21 | Linguagem de programação |
| Spring Boot 3.4.5 | Desenvolvimento da API REST |
| Spring Web | Criação dos endpoints |
| Spring Data JPA | Persistência de dados |
| Spring Validation | Validação de informações |
| PostgreSQL 16 | Banco de dados relacional |
| Flyway | Controle de migrações |
| Springdoc OpenAPI | Documentação da API |
| JUnit 5 | Testes automatizados |
| Mockito | Simulação de dependências |
| Maven | Gerenciamento de dependências e build |

## 4. Arquitetura da solução

A aplicação utiliza uma arquitetura organizada em camadas, separando as responsabilidades do sistema.

- **Controller:** recebe as requisições HTTP e disponibiliza os endpoints.
- **Service:** concentra as regras de negócio.
- **Repository:** realiza as operações de persistência.
- **Entity:** representa as entidades do domínio.
- **DTO:** organiza os dados de entrada e saída da API.
- **Validation:** realiza validações das informações recebidas.

Essa organização facilita a manutenção, os testes e a evolução do sistema.

## 5. Domínio da aplicação

O backend trabalha com os seguintes elementos:

- **Ocorrências:** problemas registrados pelos cidadãos.
- **Bairros:** localidades relacionadas às ocorrências.
- **Categorias:** classificação dos problemas urbanos.
- **Confirmações:** registros de outros moradores que também foram afetados.
- **Histórico:** acompanhamento das alterações realizadas nas ocorrências.

## 6. Endpoints principais

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/health` | Verificação da API |
| GET/POST | `/api/bairros` | Consultar e cadastrar bairros |
| GET/POST | `/api/categorias` | Consultar e cadastrar categorias |
| GET/POST | `/api/ocorrencias` | Listar e cadastrar ocorrências |
| GET/PUT/DELETE | `/api/ocorrencias/{id}` | Consultar, editar e excluir ocorrência |
| PATCH | `/api/ocorrencias/{id}/status` | Atualizar status |
| GET | `/api/ocorrencias/{id}/atualizacoes` | Consultar histórico |
| POST | `/api/ocorrencias/{id}/confirmacoes` | Registrar confirmação |
| GET | `/api/ocorrencias/{id}/previsao` | Consultar previsão externa, quando disponível |

A documentação OpenAPI/Swagger apresenta os contratos e os endpoints disponibilizados pela implementação.

## 7. Regras de negócio

As regras de domínio previstas incluem:

- Uma confirmação por morador para cada ocorrência.
- Fluxo de status: `ABERTA → EM_ANALISE → EM_ANDAMENTO → RESOLVIDA`.
- Não permitir retrocesso de status nem reabertura de ocorrência resolvida.
- Utilizar a quantidade de confirmações como indicador de relevância colaborativa.
- Exigir título, bairro e categoria no cadastro de ocorrência.
- Utilizar DTOs e validações para organizar e validar as requisições.

## 8. Banco de dados e migrações

O projeto utiliza PostgreSQL para persistência dos dados.

O Flyway é utilizado para controlar a evolução da estrutura do banco por meio de scripts de migração versionados.

As migrações são executadas conforme a configuração da aplicação.

## 9. Como executar o projeto

### Pré-requisitos

- JDK 21.
- Maven 3.9 ou superior.
- PostgreSQL 16 ou Docker Desktop.

### Configuração do banco

Configure as variáveis de ambiente:

```env
DB_URL=jdbc:postgresql://localhost:5432/bairroemdia
DB_USER=postgres
DB_PASSWORD=postgres
```

### Executar a aplicação

Entre na pasta `soa`:

```bash
cd soa
```

Execute:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

`http://localhost:8080`

### Executar os testes

Na pasta `soa`, execute:

```bash
mvn clean test
```

## 10. Documentação Swagger

Após iniciar a aplicação, acesse:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

A documentação permite visualizar os endpoints, os métodos HTTP, os parâmetros e os modelos de requisição e resposta disponibilizados pela API.

## 11. Integração com os demais componentes

O backend SOA faz parte da arquitetura geral do Bairro em Dia.

O fluxo de integração proposto é:

1. O aplicativo Mobile realiza as operações por meio da API ASP.NET/C#.
2. A API ASP.NET/C# consome os serviços Java por HTTP.
3. O backend Java processa as operações relacionadas às ocorrências.
4. O Java realiza a persistência dos dados no PostgreSQL.

A API ASP.NET não deve acessar diretamente o banco de dados utilizado pelo serviço Java.

A comunicação entre serviços deve ser protegida por mecanismos de autenticação e autorização antes de qualquer utilização em produção.

## 12. Segurança e limitações

Os endpoints deste protótipo não implementam autenticação e autorização completas.

Para uma utilização em ambiente real, são necessárias medidas adicionais, como:

- Autenticação entre serviços.
- Controle de acesso por perfil.
- Validação da identidade do morador.
- Rate limiting.
- Auditoria das operações.
- Adequação às exigências da LGPD.

Não utilizar dados pessoais reais durante as demonstrações acadêmicas.

## 13. Integrantes

- Vinicius Henrique — RM556908
- Gustavo Pierre — RM558928
- Gabriel Belo — RM551669
- Laura Souza — RM556320

## 14. Escopo deste repositório

Este repositório corresponde exclusivamente à disciplina de **SOA e Web Services**.

O foco da entrega é a arquitetura do backend, a implementação da API REST, a conexão com o banco de dados, as migrações, a documentação automática e os testes automatizados.

A implementação de modelos de Machine Learning e o treinamento com bases de dados não fazem parte do escopo deste repositório.
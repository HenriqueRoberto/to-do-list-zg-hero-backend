# To-Do List ZG Hero - Backend

Aplicação para gerenciamento de tarefas em modelo Kanban (Backlog, To-do, Doing, Done). Toda a lógica de negócio (validações, ordenação por prioridade, organização das tarefas) fica no back-end, exposta para o front-end através de uma API REST.

Os dados são mantidos em memória (sem banco de dados), portanto são reiniciados a cada execução.

## Front-end

O front-end (Angular) fica em um repositório separado e consome esta API. Para a aplicação funcionar por completo, os dois projetos devem estar rodando simultaneamente:

- Back-end: `http://localhost:8080` (este repositório)
- Front-end: `http://localhost:4200`

Repositório do front: https://github.com/HenriqueRoberto/to-do-list-zg-hero-frontend

## Tecnologias

- Java 21
- Spring Boot
- Gradle
- JUnit 5 + Mockito

## Modelo da tarefa (Task)

| Campo        | Tipo      | Obrigatório | Descrição                                    |
|--------------|-----------|-------------|----------------------------------------------|
| `id`         | String    | gerado      | Identificador único, gerado automaticamente  |
| `nome`       | String    | sim         | Nome da tarefa                               |
| `descricao`  | String    | não         | Descrição detalhada                          |
| `categoria`  | String    | não         | Categoria da tarefa                          |
| `prioridade` | Integer   | sim         | Prioridade de 1 a 5                          |
| `status`     | Status    | não         | BACKLOG (padrão), TODO, DOING ou DONE        |
| `dataLimite` | LocalDate | não         | Data limite; não pode estar no passado       |

## Arquitetura

O projeto segue uma separação em camadas:

- **model** — entidade `Task` e enum `Status`
- **repository** — persistência em memória (interface + implementação)
- **service** — regras de negócio (CRUD, atualização parcial, ordenação)
- **dto** — objetos de entrada/saída da API e mapper
- **controller** — endpoints REST
- **exception** — tratamento global de erros
- **config** — configuração de CORS

## Como rodar

Sobe a API para o front-end consumir:

```bash
./gradlew bootRun
```

A API fica disponível em `http://localhost:8080`.

## Como rodar os testes

```bash
./gradlew test
```

## Endpoints

Base URL: `http://localhost:8080`

| Método | Rota          | Descrição                                         | Corpo (JSON)                                                                                      |
|--------|---------------|---------------------------------------------------|--------------------------------------------------------------------------------------------------|
| POST   | `/tasks`      | Cria uma tarefa                                   | `nome`, `prioridade` (obrigatórios); `descricao`, `categoria`, `status`, `dataLimite` (opcionais) |
| GET    | `/tasks`      | Lista todas as tarefas (ordenadas por prioridade) | —                                                                                                |
| GET    | `/tasks/{id}` | Busca uma tarefa por id                           | —                                                                                                |
| PATCH  | `/tasks/{id}` | Atualiza parcialmente uma tarefa                  | qualquer campo (todos opcionais)                                                                 |
| DELETE | `/tasks/{id}` | Remove uma tarefa                                 | —                                                                                                |

### Status possíveis
`BACKLOG` (padrão), `TODO`, `DOING`, `DONE`

### Exemplo — corpo da requisição de criação
```json
{
"nome": "Estudar Spring",
"prioridade": 2,
"categoria": "Backend"
}
```

### Regras de negócio
- Nome é obrigatório
- Prioridade é obrigatória (1 a 5)
- Status padrão é `BACKLOG`
- Data limite não pode estar no passado
- Descrição, categoria e data podem ser nulas
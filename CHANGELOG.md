## [1.1.2] - 2026-10-29

### Corrigido
- `workspaceId` era perdido ao gerar o id da tarefa no repository, impedindo a criação de tarefas

## [1.1.1] - 2026-10-29

### Corrigido
- Workspace padrão criado no start (sem ele não era possível criar tarefas)

## [1.1.0] - 2026-10-23

### Adicionado
- CRUD de workspaces (criar, listar, deletar)
- Campo `workspaceId` nas tarefas
- Filtro de tarefas por workspace (`GET /tasks?workspaceId=`)

# Changelog

Todas as mudanças notáveis do projeto são documentadas neste arquivo.

O formato segue o [Semantic Versioning](https://semver.org/lang/pt-BR/).

## [1.0.1] - 2026-09-04

### Adicionado
- Documentação dos campos do modelo Task no README

## [1.0.0] - 2026-09-04

### Adicionado
- CRUD completo de tarefas (criar, listar, buscar por id, atualizar parcial, deletar)
- Entidade `Task` com Builder e validações
- Enum `Status` (BACKLOG, TODO, DOING, DONE)
- Persistência em memória
- Ordenação de tarefas por prioridade
- DTOs de request, update e response com mapper
- Endpoints REST (`/tasks`)
- Tratamento global de erros
- Configuração de CORS para o front-end
- Testes unitários da camada de serviço (JUnit 5 + Mockito)
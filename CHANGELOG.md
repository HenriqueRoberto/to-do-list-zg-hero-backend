# Changelog

Todas as mudanças notáveis do projeto são documentadas neste arquivo.

O formato segue o [Semantic Versioning](https://semver.org/lang/pt-BR/).

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
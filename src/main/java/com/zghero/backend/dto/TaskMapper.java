package com.zghero.backend.dto;

import com.zghero.backend.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  public Task toEntity(TaskRequestDTO dto) {
    return Task.builder()
        .nome(dto.getNome())
        .descricao(dto.getDescricao())
        .categoria(dto.getCategoria())
        .prioridade(dto.getPrioridade())
        .status(dto.getStatus())
        .dataLimite(dto.getDataLimite())
        .build();
  }

  public TaskResponseDTO toResponse(Task task) {
    TaskResponseDTO dto = new TaskResponseDTO();
    dto.setId(task.getId());
    dto.setNome(task.getNome());
    dto.setDescricao(task.getDescricao());
    dto.setCategoria(task.getCategoria());
    dto.setPrioridade(task.getPrioridade());
    dto.setStatus(task.getStatus());
    dto.setDataLimite(task.getDataLimite());
    return dto;
  }
}
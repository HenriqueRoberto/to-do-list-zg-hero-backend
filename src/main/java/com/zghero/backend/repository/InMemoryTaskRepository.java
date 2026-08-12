package com.zghero.backend.repository;

import com.zghero.backend.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

  private final Map<String, Task> tasks = new HashMap<>();

  @Override
  public Task save(Task task) {
    if (task.getId() == null) {
      String novoId = UUID.randomUUID().toString();

      Task novaTask = Task.builder()
          .id(novoId)
          .nome(task.getNome())
          .descricao(task.getDescricao())
          .categoria(task.getCategoria())
          .prioridade(task.getPrioridade())
          .status(task.getStatus())
          .dataLimite(task.getDataLimite())
          .build();

      tasks.put(novoId, novaTask);
      return novaTask;
    }

    tasks.put(task.getId(), task);
    return task;
  }

  @Override
  public List<Task> findAll() {
    return new ArrayList<>(tasks.values());
  }

  @Override
  public Optional<Task> findById(String id) {
    return Optional.ofNullable(tasks.get(id));
  }

  @Override
  public void deleteById(String id) {
    tasks.remove(id);
  }
}
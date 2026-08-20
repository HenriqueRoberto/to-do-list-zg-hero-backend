package com.zghero.backend.service;

import com.zghero.backend.model.Task;
import com.zghero.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task criarTask(Task task) {
    return taskRepository.save(task);
  }

  public List<Task> listarTasks() {
    return taskRepository.findAll();
  }

  public Task buscarPorId(String id) {
    return taskRepository.findById(id).orElse(null);
  }

  public void deletarTask(String id) {
    taskRepository.deleteById(id);
  }

  public Task atualizarTask(String id, Task dadosNovos) {
    Task taskExistente = taskRepository.findById(id).orElse(null);

    if (taskExistente == null) {
      return null;
    }

    if (dadosNovos.getNome() != null) {
      taskExistente.setNome(dadosNovos.getNome());
    }
    if (dadosNovos.getDescricao() != null) {
      taskExistente.setDescricao(dadosNovos.getDescricao());
    }
    if (dadosNovos.getCategoria() != null) {
      taskExistente.setCategoria(dadosNovos.getCategoria());
    }
    if (dadosNovos.getPrioridade() != 0) {
      taskExistente.setPrioridade(dadosNovos.getPrioridade());
    }
    if (dadosNovos.getStatus() != null) {
      taskExistente.setStatus(dadosNovos.getStatus());
    }
    if (dadosNovos.getDataLimite() != null) {
      taskExistente.setDataLimite(dadosNovos.getDataLimite());
    }

    return taskRepository.save(taskExistente);
  }
}
package com.zghero.backend.service;

import com.zghero.backend.model.Status;
import com.zghero.backend.model.Task;
import com.zghero.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskFilterService {

  private final TaskRepository taskRepository;

  public TaskFilterService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> filtrarPorStatus(Status status) {
    List<Task> todas = taskRepository.findAll();
    List<Task> filtradas = new ArrayList<>();

    for (Task task : todas) {
      if (task.getStatus() == status) {
        filtradas.add(task);
      }
    }

    return filtradas;
  }

  public List<Task> filtrarPorCategoria(String categoria) {
    List<Task> todas = taskRepository.findAll();
    List<Task> filtradas = new ArrayList<>();

    for (Task task : todas) {
      if (categoria.equals(task.getCategoria())) {
        filtradas.add(task);
      }
    }

    return filtradas;
  }

  public List<Task> filtrarPorPrioridade(int prioridade) {
    List<Task> todas = taskRepository.findAll();
    List<Task> filtradas = new ArrayList<>();

    for (Task task : todas) {
      if (task.getPrioridade() == prioridade) {
        filtradas.add(task);
      }
    }

    return filtradas;
  }

  public List<Task> filtrarPorDataExata(LocalDate data) {
    List<Task> todas = taskRepository.findAll();
    List<Task> filtradas = new ArrayList<>();

    for (Task task : todas) {
      if (data.equals(task.getDataLimite())) {
        filtradas.add(task);
      }
    }

    return filtradas;
  }

  public List<Task> filtrarAteData(LocalDate dataLimite) {
    List<Task> todas = taskRepository.findAll();
    List<Task> filtradas = new ArrayList<>();

    for (Task task : todas) {
      if (task.getDataLimite() != null && !task.getDataLimite().isAfter(dataLimite)) {
        filtradas.add(task);
      }
    }

    return filtradas;
  }
}
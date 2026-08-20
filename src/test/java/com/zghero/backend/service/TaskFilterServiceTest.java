package com.zghero.backend.service;

import com.zghero.backend.model.Status;
import com.zghero.backend.model.Task;
import com.zghero.backend.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskFilterServiceTest {

  private TaskRepository taskRepository;
  private TaskFilterService taskFilterService;

  @BeforeEach
  void setUp() {
    taskRepository = Mockito.mock(TaskRepository.class);
    taskFilterService = new TaskFilterService(taskRepository);
  }

  @Test
  void deveFiltrarPorStatus() {
    Task task1 = Task.builder().id("1").nome("T1").prioridade(1).status(Status.DOING).build();
    Task task2 = Task.builder().id("2").nome("T2").prioridade(2).status(Status.BACKLOG).build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<Task> resultado = taskFilterService.filtrarPorStatus(Status.DOING);

    assertEquals(1, resultado.size());
    assertEquals("T1", resultado.get(0).getNome());
  }

  @Test
  void deveFiltrarPorCategoria() {
    Task task1 = Task.builder().id("1").nome("T1").prioridade(1).categoria("Back").build();
    Task task2 = Task.builder().id("2").nome("T2").prioridade(2).categoria("Front").build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<Task> resultado = taskFilterService.filtrarPorCategoria("Back");

    assertEquals(1, resultado.size());
    assertEquals("T1", resultado.get(0).getNome());
  }

  @Test
  void deveFiltrarPorPrioridade() {
    Task task1 = Task.builder().id("1").nome("T1").prioridade(3).build();
    Task task2 = Task.builder().id("2").nome("T2").prioridade(1).build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<Task> resultado = taskFilterService.filtrarPorPrioridade(3);

    assertEquals(1, resultado.size());
    assertEquals("T1", resultado.get(0).getNome());
  }

  @Test
  void deveFiltrarPorDataExata() {
    LocalDate data = LocalDate.of(2026, 12, 25);
    Task task1 = Task.builder().id("1").nome("T1").prioridade(1).dataLimite(data).build();
    Task task2 = Task.builder().id("2").nome("T2").prioridade(2).dataLimite(LocalDate.of(2026, 12, 31)).build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<Task> resultado = taskFilterService.filtrarPorDataExata(data);

    assertEquals(1, resultado.size());
    assertEquals("T1", resultado.get(0).getNome());
  }

  @Test
  void deveFiltrarAteData() {
    Task task1 = Task.builder().id("1").nome("T1").prioridade(1).dataLimite(LocalDate.of(2026, 10, 1)).build();
    Task task2 = Task.builder().id("2").nome("T2").prioridade(2).dataLimite(LocalDate.of(2026, 12, 31)).build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<Task> resultado = taskFilterService.filtrarAteData(LocalDate.of(2026, 11, 1));

    assertEquals(1, resultado.size());
    assertEquals("T1", resultado.get(0).getNome());
  }
}
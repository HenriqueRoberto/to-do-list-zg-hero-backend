package com.zghero.backend.service;

import com.zghero.backend.dto.TaskUpdateDTO;
import com.zghero.backend.model.Task;
import com.zghero.backend.model.Status;
import com.zghero.backend.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskServiceTest {

  private TaskRepository taskRepository;
  private TaskService taskService;

  @BeforeEach
  void setUp() {
    taskRepository = Mockito.mock(TaskRepository.class);
    taskService = new TaskService(taskRepository);
  }

  @Test
  void deveCriarTaskComSucesso() {
    Task taskSemId = Task.builder()
        .nome("Estudar Java")
        .prioridade(3)
        .build();

    Task taskComId = Task.builder()
        .id("abc123")
        .nome("Estudar Java")
        .prioridade(3)
        .status(Status.BACKLOG)
        .build();

    when(taskRepository.save(any(Task.class))).thenReturn(taskComId);

    Task resultado = taskService.criarTask(taskSemId);

    assertNotNull(resultado.getId());
    assertEquals("Estudar Java", resultado.getNome());
    assertEquals(3, resultado.getPrioridade());
    assertEquals(Status.BACKLOG, resultado.getStatus());
  }

  @Test
  void deveListarTasks() {
    Task task1 = Task.builder().id("1").nome("Task 1").prioridade(1).build();
    Task task2 = Task.builder().id("2").nome("Task 2").prioridade(2).build();

    when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

    List<Task> resultado = taskService.listarTasks();

    assertEquals(2, resultado.size());
  }

  @Test
  void deveBuscarTaskPorId() {
    Task task = Task.builder().id("abc").nome("Teste").prioridade(1).build();

    when(taskRepository.findById("abc")).thenReturn(Optional.of(task));

    Task resultado = taskService.buscarPorId("abc");

    assertNotNull(resultado);
    assertEquals("abc", resultado.getId());
  }

  @Test
  void deveRetornarNullQuandoIdNaoExiste() {
    when(taskRepository.findById("xyz")).thenReturn(Optional.empty());

    Task resultado = taskService.buscarPorId("xyz");

    assertNull(resultado);
  }

  @Test
  void deveDeletarTask() {
    taskService.deletarTask("abc");

    Mockito.verify(taskRepository).deleteById("abc");
  }

  @Test
  void deveAtualizarParcialmente() {
    Task taskOriginal = Task.builder()
        .id("abc")
        .nome("Nome original")
        .prioridade(2)
        .status(Status.BACKLOG)
        .build();

    TaskUpdateDTO dadosNovos = new TaskUpdateDTO();
    dadosNovos.setNome("Nome atualizado");
    dadosNovos.setPrioridade(4);

    when(taskRepository.findById("abc")).thenReturn(Optional.of(taskOriginal));
    when(taskRepository.save(any(Task.class))).thenReturn(taskOriginal);

    Task resultado = taskService.atualizarTask("abc", dadosNovos);

    assertEquals("Nome atualizado", resultado.getNome());
    assertEquals(4, resultado.getPrioridade());
    assertEquals(Status.BACKLOG, resultado.getStatus());
  }

  @Test
  void deveRetornarNullAoAtualizarIdInexistente() {
    when(taskRepository.findById("xyz")).thenReturn(Optional.empty());

    TaskUpdateDTO dados = new TaskUpdateDTO();
    dados.setNome("Teste");
    dados.setPrioridade(1);

    Task resultado = taskService.atualizarTask("xyz", dados);

    assertNull(resultado);
  }
}
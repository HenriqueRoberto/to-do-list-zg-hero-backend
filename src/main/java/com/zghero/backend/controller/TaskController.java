package com.zghero.backend.controller;

import com.zghero.backend.dto.TaskMapper;
import com.zghero.backend.dto.TaskRequestDTO;
import com.zghero.backend.dto.TaskResponseDTO;
import com.zghero.backend.model.Task;
import com.zghero.backend.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;
  private final TaskMapper taskMapper;

  public TaskController(TaskService taskService, TaskMapper taskMapper) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  @PostMapping
  public ResponseEntity<TaskResponseDTO> criar(@RequestBody TaskRequestDTO dto) {
    Task task = taskMapper.toEntity(dto);
    Task criada = taskService.criarTask(task);
    return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toResponse(criada));
  }

  @GetMapping
  public List<TaskResponseDTO> listar() {
    List<TaskResponseDTO> resposta = new ArrayList<>();
    for (Task task : taskService.listarTasks()) {
      resposta.add(taskMapper.toResponse(task));
    }
    return resposta;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponseDTO> buscarPorId(@PathVariable String id) {
    Task task = taskService.buscarPorId(id);
    if (task == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(taskMapper.toResponse(task));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable String id) {
    taskService.deletarTask(id);
    return ResponseEntity.noContent().build();
  }
}
package com.zghero.backend.repository;

import com.zghero.backend.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
  Task save(Task task);

  List<Task> findAll();

  Optional<Task> findById(String id);

  void deleteById(String id);

}

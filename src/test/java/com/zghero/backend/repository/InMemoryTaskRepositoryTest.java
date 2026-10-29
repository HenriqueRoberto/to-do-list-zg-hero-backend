package com.zghero.backend.repository;

import com.zghero.backend.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskRepositoryTest {

  @Test
  void deveGerarIdEPreservarWorkspaceId() {
    InMemoryTaskRepository repository = new InMemoryTaskRepository();

    Task semId = Task.builder()
        .workspaceId("w1")
        .nome("Teste")
        .prioridade(2)
        .build();

    Task salva = repository.save(semId);

    assertNotNull(salva.getId());
    assertEquals("w1", salva.getWorkspaceId());
    assertEquals("Teste", salva.getNome());
  }
}
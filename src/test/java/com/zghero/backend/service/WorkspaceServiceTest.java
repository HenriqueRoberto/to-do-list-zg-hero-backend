package com.zghero.backend.service;

import com.zghero.backend.model.Workspace;
import com.zghero.backend.repository.WorkspaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WorkspaceServiceTest {

  private WorkspaceRepository workspaceRepository;
  private WorkspaceService workspaceService;

  @BeforeEach
  void setUp() {
    workspaceRepository = Mockito.mock(WorkspaceRepository.class);
    workspaceService = new WorkspaceService(workspaceRepository);
  }

  @Test
  void deveCriarWorkspace() {
    Workspace entrada = Workspace.builder().nome("Pessoal").build();
    Workspace salvo = Workspace.builder().id("w1").nome("Pessoal").build();

    when(workspaceRepository.save(any(Workspace.class))).thenReturn(salvo);

    Workspace resultado = workspaceService.criarWorkspace(entrada);

    assertNotNull(resultado.getId());
    assertEquals("Pessoal", resultado.getNome());
  }

  @Test
  void deveListarWorkspaces() {
    Workspace w1 = Workspace.builder().id("1").nome("Pessoal").build();
    Workspace w2 = Workspace.builder().id("2").nome("Trabalho").build();

    when(workspaceRepository.findAll()).thenReturn(List.of(w1, w2));

    assertEquals(2, workspaceService.listarWorkspaces().size());
  }

  @Test
  void deveBuscarPorId() {
    Workspace w = Workspace.builder().id("abc").nome("Pessoal").build();
    when(workspaceRepository.findById("abc")).thenReturn(Optional.of(w));

    Workspace resultado = workspaceService.buscarPorId("abc");

    assertNotNull(resultado);
    assertEquals("abc", resultado.getId());
  }

  @Test
  void deveRetornarNullQuandoNaoExiste() {
    when(workspaceRepository.findById("xyz")).thenReturn(Optional.empty());
    assertNull(workspaceService.buscarPorId("xyz"));
  }

  @Test
  void deveDeletarWorkspace() {
    workspaceService.deletarWorkspace("abc");
    Mockito.verify(workspaceRepository).deleteById("abc");
  }
}
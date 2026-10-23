package com.zghero.backend.controller;

import com.zghero.backend.dto.WorkspaceMapper;
import com.zghero.backend.dto.WorkspaceRequestDTO;
import com.zghero.backend.dto.WorkspaceResponseDTO;
import com.zghero.backend.model.Workspace;
import com.zghero.backend.service.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

  private final WorkspaceService workspaceService;
  private final WorkspaceMapper workspaceMapper;

  public WorkspaceController(WorkspaceService workspaceService, WorkspaceMapper workspaceMapper) {
    this.workspaceService = workspaceService;
    this.workspaceMapper = workspaceMapper;
  }

  @PostMapping
  public ResponseEntity<WorkspaceResponseDTO> criar(@RequestBody WorkspaceRequestDTO dto) {
    Workspace workspace = workspaceMapper.toEntity(dto);
    Workspace criado = workspaceService.criarWorkspace(workspace);
    return ResponseEntity.status(HttpStatus.CREATED).body(workspaceMapper.toResponse(criado));
  }

  @GetMapping
  public List<WorkspaceResponseDTO> listar() {
    List<WorkspaceResponseDTO> resposta = new ArrayList<>();
    for (Workspace workspace : workspaceService.listarWorkspaces()) {
      resposta.add(workspaceMapper.toResponse(workspace));
    }
    return resposta;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable String id) {
    workspaceService.deletarWorkspace(id);
    return ResponseEntity.noContent().build();
  }
}
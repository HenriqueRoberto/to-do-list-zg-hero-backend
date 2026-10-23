package com.zghero.backend.service;

import com.zghero.backend.model.Workspace;
import com.zghero.backend.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {

  private final WorkspaceRepository workspaceRepository;

  public WorkspaceService(WorkspaceRepository workspaceRepository) {
    this.workspaceRepository = workspaceRepository;
  }

  public Workspace criarWorkspace(Workspace workspace) {
    return workspaceRepository.save(workspace);
  }

  public List<Workspace> listarWorkspaces() {
    return workspaceRepository.findAll();
  }

  public Workspace buscarPorId(String id) {
    return workspaceRepository.findById(id).orElse(null);
  }

  public void deletarWorkspace(String id) {
    workspaceRepository.deleteById(id);
  }
}
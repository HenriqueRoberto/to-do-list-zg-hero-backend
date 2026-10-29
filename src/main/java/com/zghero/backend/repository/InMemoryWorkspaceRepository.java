package com.zghero.backend.repository;

import com.zghero.backend.model.Workspace;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryWorkspaceRepository implements WorkspaceRepository {

  private final Map<String, Workspace> workspaces = new HashMap<>();

  public InMemoryWorkspaceRepository() {
    Workspace padrao = Workspace.builder()
        .id("default")
        .nome("Pessoal")
        .build();
    workspaces.put(padrao.getId(), padrao);
  }

  @Override
  public Workspace save(Workspace workspace) {
    if (workspace.getId() == null) {
      String novoId = UUID.randomUUID().toString();

      Workspace novo = Workspace.builder()
          .id(novoId)
          .nome(workspace.getNome())
          .build();

      workspaces.put(novoId, novo);
      return novo;
    }

    workspaces.put(workspace.getId(), workspace);
    return workspace;
  }

  @Override
  public List<Workspace> findAll() {
    return new ArrayList<>(workspaces.values());
  }

  @Override
  public Optional<Workspace> findById(String id) {
    return Optional.ofNullable(workspaces.get(id));
  }

  @Override
  public void deleteById(String id) {
    workspaces.remove(id);
  }
}
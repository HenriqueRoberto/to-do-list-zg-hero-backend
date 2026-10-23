package com.zghero.backend.repository;

import com.zghero.backend.model.Workspace;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository {

  Workspace save(Workspace workspace);

  List<Workspace> findAll();

  Optional<Workspace> findById(String id);

  void deleteById(String id);
}
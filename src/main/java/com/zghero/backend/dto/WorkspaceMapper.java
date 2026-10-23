package com.zghero.backend.dto;

import com.zghero.backend.model.Workspace;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper {

  public Workspace toEntity(WorkspaceRequestDTO dto) {
    return Workspace.builder()
        .nome(dto.getNome())
        .build();
  }

  public WorkspaceResponseDTO toResponse(Workspace workspace) {
    WorkspaceResponseDTO dto = new WorkspaceResponseDTO();
    dto.setId(workspace.getId());
    dto.setNome(workspace.getNome());
    return dto;
  }
}

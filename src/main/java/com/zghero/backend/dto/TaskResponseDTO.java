package com.zghero.backend.dto;

import com.zghero.backend.model.Status;

import java.time.LocalDate;

public class TaskResponseDTO {

  private String id;
  private String nome;
  private String descricao;
  private String categoria;
  private int prioridade;
  private Status status;
  private LocalDate dataLimite;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public int getPrioridade() {
    return prioridade;
  }

  public void setPrioridade(int prioridade) {
    this.prioridade = prioridade;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public LocalDate getDataLimite() {
    return dataLimite;
  }

  public void setDataLimite(LocalDate dataLimite) {
    this.dataLimite = dataLimite;
  }
}
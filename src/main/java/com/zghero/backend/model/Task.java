package com.zghero.backend.model;

import java.time.LocalDate;

public class Task {

  private final String id;
  private String nome;
  private String descricao;
  private String categoria;
  private Integer prioridade;
  private Status status;
  private LocalDate dataLimite;

  private Task(Builder builder) {
    this.id = builder.id;
    this.nome = builder.nome;
    this.descricao = builder.descricao;
    this.categoria = builder.categoria;
    this.prioridade = builder.prioridade;
    this.status = builder.status;
    this.dataLimite = builder.dataLimite;
  }

  public static Builder builder() {
    return new Builder();
  }

  private static void validarNome(String nome) {
    if (nome == null || nome.isBlank()) {
      throw new IllegalArgumentException("Nome é obrigatório");
    }
  }

  private static void validarPrioridade(Integer prioridade) {
    if (prioridade == null || prioridade < 1 || prioridade > 5) {
      throw new IllegalArgumentException("Prioridade é obrigatória e deve estar entre 1 e 5");
    }
  }

  private static void validarDataLimite(LocalDate dataLimite) {
    if (dataLimite != null && dataLimite.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Data limite não pode estar no passado");
    }
  }

  public String getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    validarNome(nome);
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

  public Integer getPrioridade() {
    return prioridade;
  }

  public void setPrioridade(Integer prioridade) {
    validarPrioridade(prioridade);
    this.prioridade = prioridade;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = (status != null) ? status : Status.BACKLOG;
  }

  public LocalDate getDataLimite() {
    return dataLimite;
  }

  public void setDataLimite(LocalDate dataLimite) {
    validarDataLimite(dataLimite);
    this.dataLimite = dataLimite;
  }

  public static class Builder {
    private String id;
    private String nome;
    private String descricao;
    private String categoria;
    private Integer prioridade;
    private Status status;
    private LocalDate dataLimite;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Builder descricao(String descricao) {
      this.descricao = descricao;
      return this;
    }

    public Builder categoria(String categoria) {
      this.categoria = categoria;
      return this;
    }

    public Builder prioridade(Integer prioridade) {
      this.prioridade = prioridade;
      return this;
    }

    public Builder status(Status status) {
      this.status = status;
      return this;
    }

    public Builder dataLimite(LocalDate dataLimite) {
      this.dataLimite = dataLimite;
      return this;
    }

    public Task build() {
      validarNome(nome);
      validarPrioridade(prioridade);
      validarDataLimite(dataLimite);

      if (status == null) {
        status = Status.BACKLOG;
      }

      return new Task(this);
    }
  }
}
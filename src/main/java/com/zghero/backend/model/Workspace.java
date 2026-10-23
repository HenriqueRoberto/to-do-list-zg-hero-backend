package com.zghero.backend.model;

public class Workspace {

  private final String id;
  private String nome;

  private Workspace(Builder builder) {
    this.id = builder.id;
    this.nome = builder.nome;
  }

  public static Builder builder() {
    return new Builder();
  }

  private static void validarNome(String nome) {
    if (nome == null || nome.isBlank()) {
      throw new IllegalArgumentException("Nome do workspace é obrigatório");
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

  public static class Builder {
    private String id;
    private String nome;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Workspace build() {
      validarNome(nome);
      return new Workspace(this);
    }
  }
}
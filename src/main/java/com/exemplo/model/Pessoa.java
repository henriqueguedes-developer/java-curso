package com.exemplo.model; // Aqui, você usa o pacote adequado

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Esta anotação indica que a classe é uma entidade JPA
public class Pessoa {

    @Id // Define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o ID será gerado automaticamente
    private Long id;

    private String nome;
    private int idade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

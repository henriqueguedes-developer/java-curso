package com.exemplo;

import com.exemplo.model.Pessoa;
import com.exemplo.repository.PessoaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Cria o EntityManager a partir da unidade de persistência
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("apiPU");
        EntityManager em = emf.createEntityManager();

        // Instancia o repositório com o EntityManager
        PessoaRepository pessoaRepository = new PessoaRepository(em);

        // Lista todas as pessoas
        List<Pessoa> pessoas = pessoaRepository.findAll();

        System.out.println("Pessoas cadastradas no banco:");
        for (Pessoa pessoa : pessoas) {
            System.out.println(
                    "ID: " + pessoa.getId() + " | Nome: " + pessoa.getNome() + " | Idade: " + pessoa.getIdade());
        }

        // Fecha os recursos
        em.close();
        emf.close();
    }
}

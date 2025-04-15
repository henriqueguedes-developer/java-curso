package com.exemplo.repository;

import com.exemplo.model.Pessoa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class PessoaRepository {

    private EntityManager entityManager;

    // Construtor com EntityManager
    public PessoaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Pessoa pessoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(pessoa);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Pessoa findById(Long id) {
        return entityManager.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                .getResultList();
    }

    public void update(Pessoa pessoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(pessoa);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void delete(Pessoa pessoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(pessoa) ? pessoa : entityManager.merge(pessoa));
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
// Note: Certifique-se de que o EntityManager está sendo gerenciado corretamente
// e que as transações estão sendo iniciadas e finalizadas adequadamente.
// Isso é importante para garantir a integridade dos dados e evitar problemas de
// concorrência.
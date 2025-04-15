package com.exemplo.service;

import java.util.List;

import com.exemplo.model.Pessoa;
import com.exemplo.repository.PessoaRepository;

public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // Método para salvar uma nova pessoa
    public void save(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    // Método para encontrar uma pessoa pelo ID
    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id);
    }

    // Método para listar todas as pessoas
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    // Método para atualizar uma pessoa
    public void update(Pessoa pessoa) {
        pessoaRepository.update(pessoa);
    }

    // Método para remover uma pessoa
    public void delete(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }
}

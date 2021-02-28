package br.com.esig.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esig.teste.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}

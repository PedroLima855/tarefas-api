package br.com.esig.teste.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.esig.teste.model.Tarefa;

@Repository
public class ConsultaTarefaRepositoryImpl {

	private final EntityManager manager;

	public ConsultaTarefaRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	public List<Tarefa> buscar(Long id, String titulo, String descricao, String responsavel, Boolean concluida) {

		String query = "select T from Tarefa as T ";
		String condicao = "where";

		if (id != null) {
			query += condicao + " T.id = :id";
			condicao = " and ";
		}

		if (titulo != null) {
			query += condicao + " UPPER(T.titulo) LIKE CONCAT('%',UPPER(:titulo),'%')";
			condicao = " and ";
		}

		if (descricao != null) {
			query += condicao + " UPPER(T.descricao) LIKE CONCAT('%',UPPER(:descricao),'%') ";
			condicao = " and ";
		}

		if (responsavel != null) {
			query += condicao + " UPPER(T.responsavel) LIKE CONCAT('%',UPPER(:responsavel),'%') ";
			condicao = " and ";
		}

		if (concluida != null) {
			query += condicao + " T.concluida = :concluida ";
			condicao = " and ";
		}

		TypedQuery<Tarefa> q = manager.createQuery(query, Tarefa.class);

		if (id != null) {
			q.setParameter("id", id);
		}

		if (titulo != null) {
			q.setParameter("titulo", titulo);
		}

		if (descricao != null) {
			q.setParameter("descricao", descricao);
		}

		if (responsavel != null) {
			q.setParameter("responsavel", responsavel);
		}

		if (concluida != null) {
			q.setParameter("concluida", concluida);
		}

		return q.getResultList();
	}

}

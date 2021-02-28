package br.com.esig.teste.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.esig.teste.event.RecursoEvento;
import br.com.esig.teste.model.Tarefa;
import br.com.esig.teste.repository.ConsultaTarefaRepositoryImpl;
import br.com.esig.teste.repository.TarefaRepository;

@Service
public class TarefaService {

	private TarefaRepository repo;
	private ApplicationEventPublisher publisher;
	private ConsultaTarefaRepositoryImpl consultaRepository;

	public TarefaService(TarefaRepository repo, ApplicationEventPublisher publisher,
			ConsultaTarefaRepositoryImpl consultaRepository) {
		super();
		this.repo = repo;
		this.publisher = publisher;
		this.consultaRepository = consultaRepository;
	}

	public Tarefa salvarTarefa(Tarefa tarefa, HttpServletResponse response) {

		Tarefa tarefaSalva = repo.save(tarefa);
		publisher.publishEvent(new RecursoEvento(this, response, tarefaSalva.getId()));

		return tarefaSalva;
	}

	public Tarefa editarTarefa(Long id, Tarefa tarefa) {
		tarefa.setId(id);
		tarefa = repo.save(tarefa);

		return tarefa;

	}

	public Tarefa atualizaTarefaEmAndamento(Tarefa tarefa, Boolean situacao) {

		tarefa.setConcluida(situacao);
		repo.save(tarefa);

		return tarefa;
	}

	public void deletarPessoa(Long id) {
		repo.deleteById(id);
	}

	public List<Tarefa> listarTarefas(Long id, String titulo, String descricao, String responsavel, Boolean concluida) {

		return consultaRepository.buscar(id, titulo, descricao, responsavel, concluida);

	}

	public Optional<Tarefa> buscarPorId(Long id) {
		Optional<Tarefa> tarefa = repo.findById(id);
		return tarefa;
	}
}

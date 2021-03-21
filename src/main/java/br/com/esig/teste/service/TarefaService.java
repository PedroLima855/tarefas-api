package br.com.esig.teste.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import br.com.esig.teste.model.Tarefa;
import br.com.esig.teste.repository.ConsultaTarefaRepositoryImpl;
import br.com.esig.teste.repository.TarefaRepository;

@Service
public class TarefaService {

	private TarefaRepository repo;
	private ConsultaTarefaRepositoryImpl consultaRepository;

	public TarefaService(TarefaRepository repo, ConsultaTarefaRepositoryImpl consultaRepository) {
		this.repo = repo;
		this.consultaRepository = consultaRepository;
	}

	public Tarefa salvarTarefa(Tarefa tarefa) {

		LocalDate date = tarefa.getDeadline();
		date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		tarefa.setDeadline(date);

		return repo.save(tarefa);
	}

	public Tarefa editarTarefa(Long id, Tarefa tarefa) {
		tarefa.setId(id);
		tarefa = repo.save(tarefa);

		return tarefa;

	}

	public Tarefa atualizaTarefaEmAndamento(Tarefa tarefa, String situacao) {

		if(!situacao.equals("Andamento")){

			if(!situacao.equals("Concluida")){

				throw new RuntimeException("Ops, a situação só pode ser Concluida ou Andamento");
			}
		}

		tarefa.setSituacao(situacao);
		repo.save(tarefa);

		return tarefa;
	}

	public void deletarPessoa(Long id) {
		repo.deleteById(id);
	}

	public List<Tarefa> listarTarefas(Long id, String titulo, String descricao, String responsavel, String situacao) {

		return consultaRepository.buscar(id, titulo, descricao, responsavel, situacao);

	}

	public Optional<Tarefa> buscarPorId(Long id) {
		Optional<Tarefa> tarefa = repo.findById(id);
		return tarefa;
	}
}

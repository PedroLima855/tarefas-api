package br.com.esig.teste.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.esig.teste.model.Tarefa;
import br.com.esig.teste.service.TarefaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("API REST Tarefas")
@RequestMapping("/tarefas")
public class TarefaResource {

	private TarefaService tarefaService;

	public TarefaResource(TarefaService tarefaService) {
		super();
		this.tarefaService = tarefaService;
	}

	@PostMapping("/salvar")
	@ApiOperation(value = "Salva uma tarefa")
	@ResponseStatus(HttpStatus.CREATED)
//	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA')")
	public Tarefa salvar(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) {
		return tarefaService.salvarTarefa(tarefa, response);
	}

	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualiza uma tarefa")
//	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA')")
	public ResponseEntity<Tarefa> atualizarTarefa(@Valid @RequestBody Tarefa tarefa, @PathVariable Long id) {

		Optional<Tarefa> retorno = tarefaService.buscarPorId(id);

		if (!retorno.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Tarefa tarefaEdit = tarefaService.editarTarefa(id, tarefa);
		return ResponseEntity.ok(tarefaEdit);
	}

	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deleta uma tarefa")
//	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA')")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {

		Optional<Tarefa> retorno = tarefaService.buscarPorId(id);

		if (!retorno.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		tarefaService.deletarPessoa(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/listar")
	@ApiOperation(value ="Consulta tarefas")
	@ResponseStatus(HttpStatus.OK)
//	@PreAuthorize("hasAuthority('USER')")
	public List<Tarefa> listarTarefas(@RequestParam(value = "numero", required = false) Long id,
			@RequestParam(value = "titulo", required = false) String titulo,
			@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "responsavel", required = false) String responsavel,
			@RequestParam(value = "concluida", required = false) Boolean concluida) {

		return tarefaService.listarTarefas(id, titulo, descricao, responsavel, concluida);

	}

	@PatchMapping("/finalizado/{id}")
	@ApiOperation(value = "Altera tarefa para concluido ou andamento")
//	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA')")
	public ResponseEntity<Void> atualizarTarefaEmAndamento(@PathVariable Long id, @RequestBody Boolean situacao) {

		Optional<Tarefa> retorno = tarefaService.buscarPorId(id);

		if (!retorno.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		tarefaService.atualizaTarefaEmAndamento(retorno.get(), situacao);

		return ResponseEntity.noContent().build();
	}

}
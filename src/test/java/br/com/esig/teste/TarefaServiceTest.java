package br.com.esig.teste;

import br.com.esig.teste.model.Prioridade;
import br.com.esig.teste.model.Tarefa;
import br.com.esig.teste.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TarefaServiceTest {

    @Autowired
    private TarefaService tarefaService;

    @Test
    public void salvarTarefaTest(){
        Tarefa tarefa =  new Tarefa();
        tarefa.setTitulo("Lavar louça");
        tarefa.setDescricao("deve lavar bem todos os pratos");
        tarefa.setPrioridade(Prioridade.MEDIA);
        tarefa.setResponsavel("Pedro");
        tarefa.setDeadline(LocalDate.now());;
        tarefaService.salvarTarefa(tarefa, null);

    }

    @Test
    public void atualizarTarefaTest(){
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(1L);
        tarefa.get().setResponsavel("Vanessa");
        tarefaService.salvarTarefa(tarefa.get(), null);
    }

    @Test
    public void deletarTarefaTest(){
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(6L);
        tarefaService.deletarPessoa(tarefa.get().getId());
    }

    @Test
    public void consultarTarefaTest(){
        List<Tarefa> tarefas = tarefaService.listarTarefas(1L, "Lavar", "deve lavar",
                "Vanessa", false);
    }

    @Test
    public void atualizarTarefaEmAndamentoTest(){
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(3L);
        tarefa.get().setConcluida(false);
        tarefaService.salvarTarefa(tarefa.get(), null);
    }
}

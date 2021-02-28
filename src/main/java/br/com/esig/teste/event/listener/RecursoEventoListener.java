package br.com.esig.teste.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.esig.teste.event.RecursoEvento;

public class RecursoEventoListener implements ApplicationListener<RecursoEvento> {

	@Override
	public void onApplicationEvent(RecursoEvento recursoEvento) {

		HttpServletResponse response = recursoEvento.getResponse();
		Long id = recursoEvento.getId();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}

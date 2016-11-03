package br.com.triadworks.bugtracker.service;

import java.util.Date;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.DataAtual;
import br.com.triadworks.bugtracker.util.DataAtualSemHora;

public class LoginListener {
	
	@Inject
	@DataAtual
	private Date dataAtual;
	
	@Inject
	@DataAtualSemHora
	private Date dataAtualSemHora;

	public void onLogin(@Observes Usuario usuario) {
		System.out.println("Usuário logou no sistema: " + usuario.getLogin());
		System.out.println("dataAtual" + dataAtual);
		System.out.println("dataAtualSemHora" + dataAtualSemHora);
	}
}

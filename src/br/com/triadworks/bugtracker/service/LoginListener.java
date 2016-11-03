package br.com.triadworks.bugtracker.service;

import javax.enterprise.event.Observes;

import br.com.triadworks.bugtracker.modelo.Usuario;

public class LoginListener {

	public void onLogin(@Observes Usuario usuario) {
		System.out.println("Usuário logou no sistema: " + usuario.getLogin());
	}
}

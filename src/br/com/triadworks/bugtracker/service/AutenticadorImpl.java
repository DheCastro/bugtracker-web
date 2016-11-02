package br.com.triadworks.bugtracker.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;

@ApplicationScoped
public class AutenticadorImpl implements Autenticador {
	
	@Inject
	private UsuarioDao dao;

	@Override
	public Usuario autentica(String login, String senha) {
		Usuario usuario = dao.buscaPor(login, senha);
		return usuario;
	}

}
package br.com.triadworks.bugtracker.service;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;

public class AutenticadorImpl implements Autenticador {

	@Override
	public Usuario autentica(String login, String senha) {
		UsuarioDao dao = new UsuarioDao();
		Usuario usuario = dao.buscaPor(login, senha);
		return usuario;
	}

}
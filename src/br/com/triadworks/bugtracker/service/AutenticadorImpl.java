package br.com.triadworks.bugtracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;

@Service
public class AutenticadorImpl implements Autenticador, UserDetailsService {
	
	@Autowired
	private UsuarioDao dao;

	@Override
	public Usuario autentica(String login, String senha) {
		Usuario usuario = dao.buscaPor(login, senha);
		return usuario;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = dao.buscaPorLogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return usuario;
	}

}
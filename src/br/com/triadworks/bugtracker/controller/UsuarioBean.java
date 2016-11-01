package br.com.triadworks.bugtracker.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;

@ManagedBean
public class UsuarioBean { 

	private Usuario usuario = new Usuario();

	public void salva() {
		UsuarioDao dao = new UsuarioDao();
		dao.adiciona(this.usuario);
		this.usuario = new Usuario(); // limpa os campos
	}
	
	public List<Usuario> getUsuarios() {
		UsuarioDao dao = new UsuarioDao();
		return dao.lista();
	}
	
	public void remove(Usuario usuario) {
		UsuarioDao dao = new UsuarioDao();
		dao.remove(usuario);
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
}

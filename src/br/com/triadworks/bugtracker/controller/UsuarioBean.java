package br.com.triadworks.bugtracker.controller;

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
	
	public Usuario getUsuario() {
		return this.usuario;
	}
}

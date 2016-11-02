package br.com.triadworks.bugtracker.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Named
@RequestScoped // javax.enterprise.context.RequestScoped
public class UsuarioBean { 

	@Inject
	private UsuarioDao dao;
	
	private Usuario usuario = new Usuario();

	public void salva() {
		if (this.usuario.getId() == null) {
			dao.adiciona(this.usuario);
		} else {
			dao.atualiza(this.usuario);
		}

		this.usuario = new Usuario(); // limpa os campos
		new FacesUtils().adicionaMensagemDeSucesso("Usuário adicionado com sucesso!");
	}
	
	public List<Usuario> getUsuarios() {
		return dao.lista();
	}
	
	public void remove(Usuario usuario) {
		dao.remove(usuario);
		new FacesUtils().adicionaMensagemDeSucesso("Usuário removido com sucesso!");
	}
	
	public void cancela() {
		this.usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

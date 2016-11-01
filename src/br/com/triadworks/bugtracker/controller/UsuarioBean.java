package br.com.triadworks.bugtracker.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

@ManagedBean
public class UsuarioBean { 

	private Usuario usuario = new Usuario();

	public void salva() {
		UsuarioDao dao = new UsuarioDao();

		if (this.usuario.getId() == null) {
			dao.adiciona(this.usuario);
		} else {
			dao.atualiza(this.usuario);
		}

		this.usuario = new Usuario(); // limpa os campos
		new FacesUtils().adicionaMensagemDeSucesso("Usuário adicionado com sucesso!");
	}
	
	public List<Usuario> getUsuarios() {
		UsuarioDao dao = new UsuarioDao();
		return dao.lista();
	}
	
	public void remove(Usuario usuario) {
		UsuarioDao dao = new UsuarioDao();
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

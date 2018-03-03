package br.com.triadworks.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Controller
@RequestScope // org.springframework.web.context.annotation.RequestScope;
public class UsuarioBean { 

	@Autowired
	private UsuarioDao dao;
	@Autowired
	private FacesUtils facesUtils;
	
	private Usuario usuario = new Usuario();

	@Transactional
	public void salva() {
		if (this.usuario.getId() == null) {
			dao.adiciona(this.usuario);
		} else {
			dao.atualiza(this.usuario);
		}

		this.usuario = new Usuario(); // limpa os campos
		facesUtils.adicionaMensagemDeSucesso("Usuário adicionado com sucesso!");
	}
	
	public List<Usuario> getUsuarios() {
		return dao.lista();
	}
	
	@Transactional
	public void remove(Usuario usuario) {
		dao.remove(usuario);
		facesUtils.adicionaMensagemDeSucesso("Usuário removido com sucesso!");
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

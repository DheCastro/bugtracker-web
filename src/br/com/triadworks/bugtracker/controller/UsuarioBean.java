package br.com.triadworks.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;

	public void salva() {
		
		// criptografa senha antes de gravar
		String senha = usuario.getSenha();
		usuario.setSenha(passwordEncoder.encode(senha));
		
		if (this.usuario.getId() == null) {
			dao.adiciona(this.usuario);
		} else {
			dao.atualiza(this.usuario);
		}

		this.usuario = new Usuario(); // limpa os campos
		facesUtils.adicionaMensagemDeSucesso("Usuário adicionado com sucesso!");
	}
	
	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			this.usuarios = dao.lista();
		}
		return this.usuarios;
	}
	
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

package br.com.triadworks.bugtracker.controller;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.service.Autenticador;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Model
public class LoginBean {

	private String login;
	private String senha;
	
	@Inject
	private UsuarioWeb usuarioWeb;
	@Inject
	private Autenticador autenticador;
	
	@Inject
	private FacesUtils facesUtils;
	
	@Inject
	private Event<Usuario> eventoDeLogin;
	
	public String logar() {
		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario); // preenche usuário na sessão
			eventoDeLogin.fire(usuario);
			return "/pages/usuarios?faces-redirect=true";
		}
		facesUtils.adicionaMensagemDeErro("Login ou senha inválido.");
		return null;
	}
	
	public String sair() {
		usuarioWeb.desloga();
		return "/login?faces-redirect=true";
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}

}

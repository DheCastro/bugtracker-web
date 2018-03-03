package br.com.triadworks.bugtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.service.Autenticador;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Controller
@RequestScope // org.springframework.web.context.annotation.RequestScope;
public class LoginBean {

	private String login;
	private String senha;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	@Autowired
	private Autenticador autenticador;
	
	@Autowired
	private FacesUtils facesUtils;
	
	public String logar() {
		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario); // preenche usuário na sessão
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

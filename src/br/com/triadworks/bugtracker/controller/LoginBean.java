package br.com.triadworks.bugtracker.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.service.Autenticador;
import br.com.triadworks.bugtracker.service.AutenticadorImpl;
import br.com.triadworks.bugtracker.util.FacesUtils;

@ManagedBean // javax.faces.bean.ManagedBean
@RequestScoped
public class LoginBean {

	private String login;
	private String senha;
	
	@ManagedProperty("#{usuarioWeb}")
	private UsuarioWeb usuarioWeb;
	
	public String logar() {
		Autenticador autenticador = new AutenticadorImpl();
		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario); // preenche usuário na sessão
			return "/pages/usuarios?faces-redirect=true";
		}
		new FacesUtils().adicionaMensagemDeErro("Login ou senha inválido.");
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

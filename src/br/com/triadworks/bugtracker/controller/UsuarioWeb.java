package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.triadworks.bugtracker.modelo.Usuario;

@Controller
@SessionScope // org.springframework.web.context.annotation.SessionScope
public class UsuarioWeb implements Serializable {

	private Usuario usuario;

	public void loga(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void desloga() {
		this.usuario = null;
	}
	
	public boolean isLogado() {
		return this.usuario != null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}

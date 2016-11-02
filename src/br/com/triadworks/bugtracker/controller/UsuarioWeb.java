package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.triadworks.bugtracker.modelo.Usuario;

@Named
@SessionScoped // javax.enterprise.context.SessionScoped
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

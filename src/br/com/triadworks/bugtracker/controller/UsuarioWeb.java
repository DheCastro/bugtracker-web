package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.triadworks.bugtracker.modelo.Usuario;

@ManagedBean // javax.faces.bean.ManagedBean
@SessionScoped
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

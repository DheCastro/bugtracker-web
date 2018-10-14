package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import br.com.triadworks.bugtracker.modelo.Usuario;

@Controller
@SessionScope // org.springframework.web.context.annotation.SessionScope
public class UsuarioWeb implements Serializable {

	public void loga(Usuario usuario) {
		// faz nada
	}
	
	public void desloga() {
		// faz nada
	}
	
	public boolean isLogado() {
		return this.getUsuario() != null;
	}
	
	public Usuario getUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return (Usuario) principal;
		}
		return null;
	}
}

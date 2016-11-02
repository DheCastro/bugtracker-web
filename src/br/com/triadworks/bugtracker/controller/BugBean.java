package br.com.triadworks.bugtracker.controller;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.interceptor.Transacional;
import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Status;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.FacesUtils;

@Named
@RequestScoped
public class BugBean {

	private Bug bug = new Bug();
	
	@Inject
	private BugDao bugDao;
	@Inject
	private UsuarioDao usuarioDao;
	
	public BugBean() {
		this.bug.setResponsavel(new Usuario());
	}
	
	@Transacional
	public void salva() {
		this.bugDao.adiciona(bug);
		this.bug = new Bug();
		new FacesUtils().adicionaMensagemDeSucesso("Bug adicionado com sucesso!");
	}
	
	public List<Status> getTodosOsStatus() {
		return Arrays.asList(Status.values());
	}
	
	public List<Usuario> getUsuarios() {
		return usuarioDao.lista();
	}

	public Bug getBug() {
		return bug;
	}
	public void setBug(Bug bug) {
		this.bug = bug;
	}
}

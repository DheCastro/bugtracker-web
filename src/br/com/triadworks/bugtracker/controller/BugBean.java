package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
@ViewScoped // javax.faces.view.ViewScoped
public class BugBean implements Serializable {

	private Bug bug = new Bug();
	private Usuario responsavel = new Usuario();
	
	private List<Usuario> usuarios;
	
	@Inject
	private BugDao bugDao;
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private FacesUtils facesUtils;
	
	@PostConstruct
	public void init() {
		this.usuarios = usuarioDao.lista();
	}
	
	public String carrega() {
		if (bug.getId() != null) {
			bug = bugDao.busca(bug.getId());
			return null;
		}
		facesUtils.adicionaMensagemDeErro("Bug inválido ou não encontrado.");
		return "/pages/dashboard";
	}
	
	@Transacional
	public void salva() {
		this.bugDao.adiciona(bug);
		this.bug = new Bug();
		facesUtils.adicionaMensagemDeSucesso("Bug adicionado com sucesso!");
	}
	
	public void adicionarResponsavel() {
		this.responsavel = usuarioDao.busca(responsavel.getId());
		this.bug.adiciona(responsavel);
		this.responsavel = new Usuario(); // limpa dados
	}
	
	public List<Status> getTodosOsStatus() {
		return Arrays.asList(Status.values());
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public Bug getBug() {
		return bug;
	}
	public void setBug(Bug bug) {
		this.bug = bug;
	}
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
}

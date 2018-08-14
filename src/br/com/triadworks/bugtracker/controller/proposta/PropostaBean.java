package br.com.triadworks.bugtracker.controller.proposta;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.triadworks.bugtracker.dao.proposta.PropostaDao;
import br.com.triadworks.bugtracker.modelo.proposta.Proposta;

@ManagedBean
@RequestScoped
public class PropostaBean {

	@ManagedProperty("#{propostaDao}")
	private PropostaDao dao;
	
	private List<Proposta> propostas;

	@PostConstruct
	public void init() {
		this.propostas = dao.lista();
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setDao(PropostaDao dao) {
		this.dao = dao;
	}
	
	
}

package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@Named
@ViewScoped // javax.faces.view.ViewScoped
public class DashboardBean implements Serializable {
	
	@Inject
	private BugDao dao;

	private List<Bug> bugs;
	
	@PostConstruct
	public void init() {
		this.bugs = dao.lista();
	}
	
	public List<Bug> getBugs() {
		return bugs;
	}
}

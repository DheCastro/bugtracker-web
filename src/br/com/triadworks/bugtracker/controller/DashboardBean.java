package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.triadworks.bugtracker.controller.datamodel.BugDataModel;
import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@Named
@ViewScoped // javax.faces.view.ViewScoped
public class DashboardBean implements Serializable {
	
	@Inject
	private BugDao dao;

	// outra forma de trabalhar com DataModel. Na p:dataTable use #{dashboardBean.dataModel}
	@Inject
	private BugDataModel dataModel;
	
	private List<Bug> bugs;
	
	@PostConstruct
	public void init() {
		this.bugs = dao.lista();
	}
	
	public List<Bug> getBugs() {
		return bugs;
	}
	
	public BugDataModel getDataModel() {
		return dataModel;
	}
}

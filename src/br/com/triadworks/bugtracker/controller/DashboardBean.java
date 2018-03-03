package br.com.triadworks.bugtracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.triadworks.bugtracker.controller.datamodel.BugDataModel;
import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@Controller
@RequestScope // TODO: mudar para View Scope
public class DashboardBean implements Serializable {
	
	@Autowired
	private BugDao dao;

	// outra forma de trabalhar com DataModel. Na p:dataTable use #{dashboardBean.dataModel}
	@Autowired
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

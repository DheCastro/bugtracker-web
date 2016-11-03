package br.com.triadworks.bugtracker.controller.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@Named
@ViewScoped // javax.faces.view.ViewScoped
public class BugDataModel extends LazyDataModel<Bug> {

	@Inject
	private BugDao dao;
	
	@PostConstruct
	public void init() {
		this.setRowCount(dao.contaTodos());
	}
	
	@Override
	public List<Bug> load(int inicio, int quantidade, String campoDeOrdenacao,
			SortOrder sentidoDeOrdenacao, Map<String, Object> filtros) {
		
		return dao.listaPaginada(inicio, quantidade);
	}
}

package br.com.triadworks.bugtracker.controller.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@Controller
@Scope("view")
public class BugDataModel extends LazyDataModel<Bug> {

	@Autowired
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

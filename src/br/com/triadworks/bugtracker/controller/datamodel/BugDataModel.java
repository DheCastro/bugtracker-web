package br.com.triadworks.bugtracker.controller.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;

@Controller
@RequestScope // TODO: mudar para View Scope
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

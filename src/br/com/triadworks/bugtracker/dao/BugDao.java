package br.com.triadworks.bugtracker.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Comentario;
import br.com.triadworks.bugtracker.util.Repository;

@Repository
public class BugDao implements Serializable {
	
	@Inject
	private EntityManager manager;

	public void adiciona(Bug bug) {
		manager.persist(bug);
	}

	public void atualiza(Bug bug) {
		manager.merge(bug);
	}

	public void remove(Bug bug) {
		manager.remove(busca(bug.getId()));
	}

	public Bug busca(Integer id) {
		return manager.find(Bug.class, id);
	}
	
	public List<Bug> lista() {
		return manager.createQuery("select b from Bug b", Bug.class)
				.getResultList();
	}

	public List<Bug> listaPaginada(int inicio, int quantidade) {
		return manager
				.createQuery("select b from Bug b", Bug.class)
				.setFirstResult(inicio)
				.setMaxResults(quantidade)
				.getResultList();
	}

	public int contaTodos() {
		Long count = manager
				.createQuery("select count(b) from Bug b", Long.class)
				.getSingleResult();
		return count.intValue();
	}
	
	public List<Bug> getBugsDoUsuario(Integer id) {
		return manager
				.createQuery("select b from Bug b where b.responsavel.id = :id", Bug.class)
				.setParameter("id", id)
				.getResultList();
	}
	
}

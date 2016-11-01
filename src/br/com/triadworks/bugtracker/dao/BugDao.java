package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Comentario;
import br.com.triadworks.bugtracker.util.JPAUtil;

public class BugDao {

	public List<Bug> lista() {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			return manager.createQuery("select b from Bug b", Bug.class)
					.getResultList();
		} finally {
			manager.close();
		}
	}

	public void salva(Bug bug) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(bug);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}

	public void atualiza(Bug bug) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.merge(bug);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}

	public void remove(Bug bug) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.remove(manager.merge(bug));
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}

	public Bug busca(Integer id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			return manager.find(Bug.class, id);
		} finally {
			manager.close();
		}
	}

	public List<Bug> listaPaginada(int inicio, int quantidade) {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			return manager
					.createQuery("select b from Bug b", Bug.class)
					.setFirstResult(inicio)
					.setMaxResults(quantidade)
					.getResultList();
		} finally {
			manager.close();
		}
	}

	public int contaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			Long count = manager
					.createQuery("select count(b) from Bug b", Long.class)
					.getSingleResult();
			return count.intValue();
		} finally {
			manager.close();
		}
	}
	
	public List<Bug> getBugsDoUsuario(Integer id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			return manager
					.createQuery("select b from Bug b where b.responsavel.id = :id", Bug.class)
					.setParameter("id", id)
					.getResultList();
		} finally {
			manager.close();
		}
	}
	
	public Bug buscaComComentarios(Integer id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			Bug bug = manager.find(Bug.class, id);
			if (bug != null)
				bug.getComentarios().size();
			return bug;
		} finally {
			manager.close();
		}
	}
	
	public void comenta(Integer id, Comentario comentario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			Bug bug = this.busca(id);
			bug.comenta(comentario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}
	
	public void fecha(Integer id, Comentario comentario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			Bug bug = this.busca(id);
			bug.fecha(comentario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}
	
}

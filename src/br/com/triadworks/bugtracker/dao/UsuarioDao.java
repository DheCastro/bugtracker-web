package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.JPAUtil;

public class UsuarioDao {

	public List<Usuario> lista() {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			return manager.createQuery("select u from Usuario u", Usuario.class)
					.getResultList();
		} finally {
			manager.close();
		}
	}

	public void adiciona(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(usuario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}

	public void atualiza(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.merge(usuario);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}

	public void remove(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.remove(manager.merge(usuario));
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			manager.close();
		}
	}

	public Usuario busca(Integer id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			return manager.find(Usuario.class, id);
		} finally {
			manager.close();
		}
	}

	public Usuario buscaPor(String login, String senha) {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			return manager
					.createQuery(
							"select u from Usuario u "
									+ "where u.login = :login and u.senha = :senha", Usuario.class)
					.setParameter("login", login)
					.setParameter("senha", senha)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			manager.close();
		}
	}

}

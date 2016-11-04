package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.JPAUtil;

public class UsuarioDao {

	public void adiciona(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager(); // abre conexao
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin(); // inicia transacao
			manager.persist(usuario);
			transaction.commit(); // comita transacao
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback(); // defaz transacao
			}
		} finally {
			manager.close(); // fecha conexao
		}
	}

	public void atualiza(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager(); // abre conexao
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
			manager.close(); // fecha conexao
		}
	}

	public void remove(Usuario usuario) {
		EntityManager manager = new JPAUtil().getEntityManager(); // abre conexao
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
			manager.close(); // fecha conexao
		}
	}

	public Usuario busca(Integer id) {
		EntityManager manager = new JPAUtil().getEntityManager(); // abre conexao
		try {
			return manager.find(Usuario.class, id);
		} finally {
			manager.close(); // fecha conexao
		}
	}
	
	public List<Usuario> lista() {
		EntityManager manager = new JPAUtil().getEntityManager(); // abre conexao
		try {
			return manager.createQuery("select u from Usuario u", Usuario.class)
					.getResultList();
		} finally {
			manager.close(); // fecha conexao
		}
	}

	public Usuario buscaPor(String login, String senha) {
		EntityManager manager = new JPAUtil().getEntityManager(); // abre conexao
		try {
			return manager
					.createQuery(
						"select u from Usuario u "
							+ "where u.login = :login "
							+ "  and u.senha = :senha", Usuario.class)
					.setParameter("login", login)
					.setParameter("senha", senha)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			manager.close(); // fecha conexao
		}
	}

}

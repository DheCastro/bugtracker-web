package br.com.triadworks.bugtracker.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.JPAUtil;

public class UsuarioDao implements Serializable {
	
	@Inject
	private EntityManager manager;

	public List<Usuario> lista() {
		return manager.createQuery("select u from Usuario u", Usuario.class)
				.getResultList();
	}

	public void adiciona(Usuario usuario) {
		manager.persist(usuario);
	}

	public void atualiza(Usuario usuario) {
		manager.merge(usuario);
	}

	public void remove(Usuario usuario) {
		manager.remove(manager.merge(usuario));
	}

	public Usuario busca(Integer id) {
		return manager.find(Usuario.class, id);
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
		}
	}

}

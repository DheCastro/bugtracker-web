package br.com.triadworks.bugtracker.dao.proposta;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.modelo.proposta.Proposta;

@Repository
public class PropostaDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void adiciona(Proposta proposta) {
		manager.persist(proposta);
	}

	@Transactional
	public Proposta atualiza(Proposta proposta) {
		return manager.merge(proposta);
	}

	@Transactional
	public void remove(Proposta proposta) {
		manager.remove(busca(proposta.getId()));
	}

	public Proposta busca(Integer id) {
		return manager.find(Proposta.class, id);
	}
	
	/**
	 * Busca propostas com seus respectivos itens
	 */
	public List<Proposta> lista() {
		/*
		 * Usando JOIN FETCH para trazer tudo num unico SELECT (precisa do
		 * distinct para evitar duplicidade)
		 */
		String jpql = "    select distinct p "
					+ "      from Proposta p "
					+ " left join fetch p.itens it";
		return manager
				.createQuery(jpql, Proposta.class)
				//.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false) // hibernate 5.2.2 em diante
				.getResultList();
	}

}

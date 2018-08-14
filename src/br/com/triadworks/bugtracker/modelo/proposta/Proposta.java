package br.com.triadworks.bugtracker.modelo.proposta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proposta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private StatusDeProposta status = StatusDeProposta.ABERTA;
	
	@OneToMany(mappedBy="proposta", cascade=CascadeType.ALL)
	private List<ItemDeProposta> itens = new ArrayList<>();
	
	
	/**
	 * Adiciona item na proposta
	 */
	public void adiciona(ItemDeProposta item) {
		
		// TODO: logica de negócio vai aqui...
		if (status == StatusDeProposta.FECHADA) {
			throw new PropostaFechadaException();
		}
		
		this.itens.add(item); // pai conhece filho
		item.setProposta(this); // filho conhece pai
	}
	
	/**
	 * Remove item da proposta
	 */
	public void remove(ItemDeProposta item) {
		
		// TODO: logica de negócio vai aqui...
		if (status == StatusDeProposta.FECHADA) {
			throw new PropostaFechadaException();
		}
		
		this.itens.remove(item); // remove filho do pai
		item.setProposta(null); // remove pai do filho
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<ItemDeProposta> getItens() {
		return itens;
	}
	public void setItens(List<ItemDeProposta> itens) {
		this.itens = itens;
	}
	public StatusDeProposta getStatus() {
		return status;
	}
	public void setStatus(StatusDeProposta status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposta other = (Proposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

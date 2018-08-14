package br.com.triadworks.bugtracker.controller.proposta;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.triadworks.bugtracker.dao.proposta.PropostaDao;
import br.com.triadworks.bugtracker.modelo.proposta.ItemDeProposta;
import br.com.triadworks.bugtracker.modelo.proposta.Proposta;
import br.com.triadworks.bugtracker.modelo.proposta.PropostaFechadaException;
import br.com.triadworks.bugtracker.modelo.proposta.StatusDeProposta;
import br.com.triadworks.bugtracker.util.FacesUtils;

@ManagedBean
@ViewScoped // escopo de tela para manter managed bean em memoria
public class PropostaEdicaoBean {

	/**
	 * Representa o ID da proposta que será obtido via parâmetro de URL
	 */
	private Integer propostaId;
	/**
	 * Representa a proposta carregada
	 */
	private Proposta proposta;
	
	private ItemDeProposta novoItem = new ItemDeProposta();
	
	@ManagedProperty("#{propostaDao}")
	private PropostaDao dao;
	
	@ManagedProperty("#{facesUtils}")
	private FacesUtils facesUtils;
	
	/**
	 * Logica para carregar proposta por <code>id</code>. O <code>id</code> vem
	 * como parâmetro de URL numa requisição GET.
	 */
	public String carrega() {
		
		// se quiser validar os parametros
		if (propostaId == null) {
			facesUtils.adicionaMensagemDeErro("Código da proposta não informado");
			return "/pages/proposta/proposta.xhtml";
		}
		
		// busca proposta e valida se necessario
		this.proposta = dao.busca(propostaId);
		if (this.proposta == null) {
			facesUtils.adicionaMensagemDeErro("Proposta inválida ou não encontrada");
			return "/pages/proposta/proposta.xhtml";
		}
		
		// continua fluxo e abre pagina de edição
		return null;
	}
	
	/**
	 * Adiciona novo item a proposta
	 */
	public void adicionaNovoItem() {
		try {
			// TODO: executa logica de validação e pré-carregamento de infos...
			
			this.proposta.adiciona(novoItem); // adiciona item
			this.novoItem = new ItemDeProposta(); // limpa formulario de item
			
		} catch (PropostaFechadaException e) {
			facesUtils.adicionaMensagemDeErro(e.getMessage()); // trata erro
		}
	}
	
	/**
	 * Salva proposta modificada no banco de dados
	 */
	public void salva() {
		// TODO: executa logica de negócio aqui...
		
		this.proposta = dao.atualiza(this.proposta); // atualiza tabela no banco com proposta em memoria
		facesUtils.adicionaMensagemDeSucesso("Proposta atualizada com sucesso!");
	}
	
	public List<StatusDeProposta> getTodosOsStatus() {
		return Arrays.asList(StatusDeProposta.values());
	}

	public Integer getPropostaId() {
		return propostaId;
	}
	public void setPropostaId(Integer propostaId) {
		this.propostaId = propostaId;
	}
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	public PropostaDao getDao() {
		return dao;
	}
	public void setDao(PropostaDao dao) {
		this.dao = dao;
	}
	public FacesUtils getFacesUtils() {
		return facesUtils;
	}
	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}
	public ItemDeProposta getNovoItem() {
		return novoItem;
	}
	public void setNovoItem(ItemDeProposta novoItem) {
		this.novoItem = novoItem;
	}
	
}

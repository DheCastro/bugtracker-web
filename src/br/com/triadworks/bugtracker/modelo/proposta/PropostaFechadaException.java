package br.com.triadworks.bugtracker.modelo.proposta;

public class PropostaFechadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropostaFechadaException() {
		super("Não é possível editar proposta fechada");
	}
	
}

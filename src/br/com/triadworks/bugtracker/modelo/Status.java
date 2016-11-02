package br.com.triadworks.bugtracker.modelo;

public enum Status {

	ABERTO("Aberto"),
	FECHADO("Fechado");
	
	private String descricao;
	
	private Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

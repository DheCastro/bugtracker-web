package br.com.triadworks.bugtracker.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped // javax.enterprise.context.RequestScoped
public class FacesUtils {

	private FacesContext facesContext;

	public FacesUtils() {
		this.facesContext = FacesContext.getCurrentInstance();
	}

	public void adicionaMensagemDeErro(String mensagem) {
		FacesMessage facesMessage = 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
	}

	public void adicionaMensagemDeSucesso(String mensagem) {
		FacesMessage facesMessage = 
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
	}
}

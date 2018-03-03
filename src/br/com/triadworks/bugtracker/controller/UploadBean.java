package br.com.triadworks.bugtracker.controller;

import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestScope // org.springframework.web.context.annotation.RequestScope;
public class UploadBean {
	
	private Part arquivo;

	public void upload() {
		try {
			String conteudo = new Scanner(arquivo.getInputStream())
					.useDelimiter("\\A").next();

			System.out.println(conteudo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void valida(FacesContext context, UIComponent component, Object value) {

		Part file = (Part) value;
		if (!"text/plain".equals(file.getContentType())) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Tipo de arquivo inválido", "O arquivo deve ser do tipo texto.");
			throw new ValidatorException(msg);
		}
	}

	public Part getArquivo() {
		return arquivo;
	}
	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}
}

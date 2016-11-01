package br.com.triadworks.bugtracker.controller.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("senhaForte")
public class SenhaForteValidator implements Validator {

	@Override
	public void validate(FacesContext ctx, UIComponent component, Object value)
			throws ValidatorException {
		
		String valor = value.toString();
		if (!valor.contains("@")) {

			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("O campo senha não é forte o bastante");
			msg.setDetail("A senha deve ter conter o caractere '@'.");

			throw new ValidatorException(msg);
		}
	}

}

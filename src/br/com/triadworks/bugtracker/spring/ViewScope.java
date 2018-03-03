package br.com.triadworks.bugtracker.spring;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Implementação simples baseada no artigo do Cagatay Civici
 * <ul>
 * 	<li>https://cagataycivici.wordpress.com/2010/02/17/port-jsf-2-0s-viewscope-to-spring-3-0/</li>
 * </ul>
 * 
 * Entendendo a necessidade desse escopo:
 * <ul>
 * 	<li>https://jira.spring.io/browse/SPR-6543</li>
 * 	<li>http://www.baeldung.com/spring-custom-scope</li>
 * </ul>
 * 
 * Algumas implementações mais rebuscadas e provavelmente sem memory leaks:
 * <ul>
 * 	<li>https://cagataycivici.wordpress.com/2010/02/17/port-jsf-2-0s-viewscope-to-spring-3-0/</li>
 * 	<li>https://github.com/jneat/spring-jsf/blob/master/src/main/java/com/github/jneat/jsf/ViewScope.java</li>
 * 	<li>https://github.com/michail-nikolaev/primefaces-spring-scopes/blob/master/primefaces-scopes-test/src/main/java/org/nkey/primefaces/scopes/test/spring/scope/ViewScope.java</li>
 * 	<li>https://github.com/algaworks/artigo-integracao-jsf-spring/blob/master/src/main/java/com/algaworks/jsfspring/ViewScope.java</li>
 * </ul>
 * 
 * @author rponte
 */
public class ViewScope implements Scope {

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		if (!viewMap.containsKey(name)) {
			Object object = objectFactory.getObject();
			viewMap.put(name, object);
		}
		return viewMap.get(name);
	}

	@Override
	public Object remove(String name) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		return viewMap.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}
}

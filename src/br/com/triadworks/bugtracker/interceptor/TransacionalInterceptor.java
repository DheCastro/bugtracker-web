package br.com.triadworks.bugtracker.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transacional // nossa anotação customizada
public class TransacionalInterceptor {

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object intercepta(InvocationContext ctx) throws Exception {

		System.out.println("Abrindo a transação");
		manager.getTransaction().begin(); // inicia transação
		
		Object resultado = ctx.proceed(); // invoca método e retorna resultado
		
		manager.getTransaction().commit(); // comita transação
		System.out.println("Commitando a transação");

		return resultado;
	}
}

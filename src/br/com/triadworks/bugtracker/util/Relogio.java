package br.com.triadworks.bugtracker.util;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.inject.Produces;

public class Relogio {

	@Produces @DataAtual
	public Date hoje() {
		return new Date();
	}

	@Produces @DataAtualSemHora
	public Date hojeSemHora() {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}
}

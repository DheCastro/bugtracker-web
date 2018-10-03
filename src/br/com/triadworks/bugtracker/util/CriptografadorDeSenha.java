package br.com.triadworks.bugtracker.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografadorDeSenha {

	public static void main(String[] args) {
		String senha = new BCryptPasswordEncoder().encode("123");
		System.out.println(senha);
	}
}

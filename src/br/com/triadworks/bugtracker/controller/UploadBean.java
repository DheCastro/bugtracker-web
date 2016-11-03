package br.com.triadworks.bugtracker.controller;

import java.util.Scanner;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
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

	public Part getArquivo() {
		return arquivo;
	}
	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}
}

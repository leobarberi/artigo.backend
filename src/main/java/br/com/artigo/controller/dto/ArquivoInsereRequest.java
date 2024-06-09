package br.com.artigo.controller.dto;

import lombok.Data;

@Data
public class ArquivoInsereRequest {
	
	private String nomeArquivo;
	private byte[] arquivo;
	
}

package br.com.artigo.repository.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="arquivos")
@Data
public class Arquivo {
	
	private String id;
	
	private String nomeArquivo;
	
	private byte[] arquivo;
}
 
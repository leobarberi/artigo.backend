package br.com.artigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.artigo.controller.dto.ArquivoInsereRequest;
import br.com.artigo.controller.dto.ArquivoRequest;
import br.com.artigo.repository.dto.Arquivo;
import br.com.artigo.repository.jpa.ArtigoRepository;

@Service
public class ArtigoService {
	
	@Autowired
	private ArtigoRepository artigoRepository;
	
	public List<Arquivo> buscaTodosArquivos() {
		
		List<Arquivo> arquivos = artigoRepository.findAll();
		return arquivos;
	
	}
	
	public Arquivo buscaArquivosByArquivo(ArquivoRequest arquivoRequest) {
		Arquivo arquivo = artigoRepository.findByNomeArquivo(arquivoRequest.getNomeArquivo());
		return arquivo;
	}
	
	public Arquivo buscaArquivosByNomeArquivo(String nomeArquivo) {
		Arquivo arquivo = artigoRepository.findByNomeArquivo(nomeArquivo);
		return arquivo;
	}
	
	public Arquivo buscaArquivosById(Long id) {
		Arquivo arquivo = artigoRepository.findById(id).get();
		return arquivo;
	}
	
	public Arquivo buscaArquivosById(String id) {
		return artigoRepository.findById(new Long(id)).get();
	}
	
	public void insereArquivo(ArquivoInsereRequest arquivoInsereRequest) {
		
		Arquivo arquivo = new Arquivo();
		arquivo.setArquivo(arquivoInsereRequest.getArquivo());
		arquivo.setNomeArquivo(arquivoInsereRequest.getNomeArquivo());
		
		artigoRepository.save(arquivo);
		
	}
	
	public void excluiArquivo(String nomeArquivo) {
		
		Arquivo arquivo = buscaArquivosByNomeArquivo(nomeArquivo);
		artigoRepository.delete(arquivo);
		
	}
	
	public Arquivo getFileById(Long id) {
        return artigoRepository.findById(id).orElse(null);
    }
}

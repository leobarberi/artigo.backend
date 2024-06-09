package br.com.artigo.repository.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.artigo.repository.dto.Arquivo;

@Repository
public interface ArtigoRepository extends MongoRepository<Arquivo, Long>{
	
	public Arquivo findByNomeArquivo(String nomeArquivo);

}

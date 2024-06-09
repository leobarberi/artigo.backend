package br.com.artigo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.artigo.controller.dto.ArquivoInsereRequest;
import br.com.artigo.controller.dto.ArquivoRequest;
import br.com.artigo.repository.dto.Arquivo;
import br.com.artigo.service.ArtigoService;

@RestController
@RequestMapping("/artigo")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtigoController {
	
	@Autowired
	public ArtigoService artigoService;
	
	@GetMapping()
	public ResponseEntity<List<Arquivo>> buscaTodosArquivos() {
		return ResponseEntity.ok(artigoService.buscaTodosArquivos());
	}
	
	@GetMapping("/buscaArquivosById/{id}")
	public ResponseEntity<Arquivo> buscaArquivosById(@PathVariable("id") String id) {
		return ResponseEntity.ok(artigoService.buscaArquivosById(id));
	}
	
	@GetMapping("/buscaArquivosByNomeArquivo")
	public ResponseEntity<Arquivo> buscaArquivosByNomeArquivo(@RequestBody ArquivoRequest arquivoRequest) {
		return ResponseEntity.ok(artigoService.buscaArquivosByArquivo(arquivoRequest));
	}
	
	@PostMapping("/insereArquivo")
	public void insereArquivo(@RequestBody ArquivoInsereRequest arquivoInsereRequest) {
		artigoService.insereArquivo(arquivoInsereRequest);
	}
	
	@DeleteMapping("/{nomeArquivo}")
	public void excluiArquivo(@PathVariable String nomeArquivo) {
		artigoService.excluiArquivo(nomeArquivo);
	}
	
    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    	ArquivoInsereRequest newFile = new ArquivoInsereRequest();
        newFile.setNomeArquivo(file.getOriginalFilename());
        newFile.setArquivo(file.getBytes());
        artigoService.insereArquivo(newFile);
    }
    
    @GetMapping("/{nomeArquivo}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String nomeArquivo) {
    	
    	Arquivo fileEntity = artigoService.buscaArquivosByNomeArquivo(nomeArquivo);
        if (fileEntity != null) {
            ByteArrayResource resource = new ByteArrayResource(fileEntity.getArquivo());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getNomeArquivo() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	/*
    	Arquivo fileEntity = artigoService.buscaArquivosByNomeArquivo(nomeArquivo);
        if (fileEntity != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getNomeArquivo() + "\"")
                    .body(fileEntity.getArquivo().getBytes());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        */
    	
    }
    
    @GetMapping("/{id}")
    public Arquivo buscaArquivosById(Long id) {
		Arquivo arquivo = artigoService.buscaArquivosById(id);
		return arquivo;
	}
    
}

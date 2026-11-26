
package com.cloudnine.pdfoffice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Davi Soares (CloudNine-dav)
 */
@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadArquivos(@RequestParam("arquivos") List<MultipartFile> arquivos) {
        List<String> nomesArquivos = new ArrayList<>();

        for (MultipartFile arquivo : arquivos) {
            if (!arquivo.isEmpty()) {
                String nome = arquivo.getOriginalFilename();
                long tamanho = arquivo.getSize();

                log.info("Arquivo: {} | Tamanho: {} bytes", nome, tamanho);
                nomesArquivos.add(nome);
            }
        }

        return ResponseEntity.ok(nomesArquivos);
    }
}

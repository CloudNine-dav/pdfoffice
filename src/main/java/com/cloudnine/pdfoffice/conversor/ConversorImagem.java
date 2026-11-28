package com.cloudnine.pdfoffice.conversor;

import com.cloudnine.pdfoffice.model.Resultado;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.nio.file.Path;

public class ConversorImagem implements Conversor {

    @Override
    public boolean suporta(String nomeArquivo) {
        if (nomeArquivo == null) {
            return false;
        }
        String nome = nomeArquivo.toLowerCase();
        return nome.endsWith(".png") || nome.endsWith(".jpg") || nome.endsWith(".jpeg");
    }

    @Override
    public Resultado converter(Path origem, Path destino) {
        try (PDDocument documento = new PDDocument()) {
            PDImageXObject imagem = PDImageXObject.createFromFile(origem.toString(), documento);
            PDPage pagina = new PDPage(new PDRectangle(imagem.getWidth(), imagem.getHeight()));
            documento.addPage(pagina);

            try (PDPageContentStream conteudo = new PDPageContentStream(documento, pagina)) {
                conteudo.drawImage(imagem, 0, 0);
            }
            documento.save(destino.toFile());

            return new Resultado(true, "Convertido com sucesso", destino);
        } catch (IOException e) {
            return new Resultado(false, "Erro ao converter: " + e.getMessage(), null);
        }
    }
}
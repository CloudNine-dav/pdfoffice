package com.cloudnine.pdfoffice.conversor;

import com.cloudnine.pdfoffice.model.Resultado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConversorImagemTest {

    private final ConversorImagem conversor = new ConversorImagem();

    @TempDir
    Path pasta;

    @Test
    void converteImagemParaPdf() throws IOException {
        Path imagem = pasta.resolve("foto.png");
        ImageIO.write(new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB), "png", imagem.toFile());
        Path pdf = pasta.resolve("saida.pdf");

        Resultado resultado = conversor.converter(imagem, pdf);

        assertTrue(resultado.sucesso());
        assertTrue(Files.exists(pdf));
    }

    @Test
    void suportaApenasImagens() {
        assertTrue(conversor.suporta("foto.PNG"));
        assertFalse(conversor.suporta("texto.txt"));
        assertFalse(conversor.suporta(null));
    }

    @Test
    void falhaQuandoArquivoNaoExiste() {
        Resultado resultado = conversor.converter(pasta.resolve("nao-existe.png"), pasta.resolve("x.pdf"));

        assertFalse(resultado.sucesso());
    }
}
package com.cloudnine.pdfoffice.conversor;

import com.cloudnine.pdfoffice.model.Resultado;

import java.nio.file.Path;

public interface Conversor {

    boolean suporta(String nomeArquivo);

    Resultado converter(Path origem, Path destino);
}
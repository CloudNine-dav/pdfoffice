package com.cloudnine.pdfoffice.model;
import java.nio.file.Path;

public record Resultado(boolean sucesso, String mensagem, Path arquivo) {
}


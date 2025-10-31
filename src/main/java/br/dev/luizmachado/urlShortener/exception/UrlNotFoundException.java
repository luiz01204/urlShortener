package br.dev.luizmachado.urlShortener.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String id) {
        super("URL com o ID '" + id + "' não foi encontrada.");
    }
}

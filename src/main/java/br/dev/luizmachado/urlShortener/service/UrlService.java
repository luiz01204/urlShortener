package br.dev.luizmachado.urlShortener.service;

import br.dev.luizmachado.urlShortener.dto.UrlRequest;
import br.dev.luizmachado.urlShortener.entities.UrlEntity;
import br.dev.luizmachado.urlShortener.exception.UrlNotFoundException;
import br.dev.luizmachado.urlShortener.repositories.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UrlService {

    @Autowired
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    public UrlEntity save(UrlRequest urlRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphabetic(5, 10);
        } while (urlRepository.existsById(id));

        UrlEntity newUrl = new UrlEntity(id, urlRequest.url(), LocalDateTime.now());
        return urlRepository.save(newUrl);
    }

    public UrlEntity getById(String id) {
        return urlRepository.findById(id)
                .orElseThrow(() -> new UrlNotFoundException(id));
    }
}

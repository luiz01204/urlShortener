package br.dev.luizmachado.urlShortener.controller;

import br.dev.luizmachado.urlShortener.dto.UrlRequest;
import br.dev.luizmachado.urlShortener.dto.UrlResponse;
import br.dev.luizmachado.urlShortener.entities.UrlEntity;
import br.dev.luizmachado.urlShortener.repositories.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponse> shortUrl(@RequestBody UrlRequest request, HttpServletRequest http){

        String id;
        do {
            id = RandomStringUtils.randomAlphabetic(5,10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now()));
        var redirecrUrl = http.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new UrlResponse(redirecrUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getUrl(@PathVariable String id){
        var url = urlRepository.findById(id);

        if (url.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}

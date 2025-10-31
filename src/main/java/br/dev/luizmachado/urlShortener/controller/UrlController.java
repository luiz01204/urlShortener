package br.dev.luizmachado.urlShortener.controller;

import br.dev.luizmachado.urlShortener.dto.UrlRequest;
import br.dev.luizmachado.urlShortener.dto.UrlResponse;
import br.dev.luizmachado.urlShortener.entities.UrlEntity;
import br.dev.luizmachado.urlShortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponse> shortUrl(@RequestBody UrlRequest request, HttpServletRequest http) {
        UrlEntity newUrl = urlService.save(request);
        String redirectUrl = http.getRequestURL().toString().replace("shorten-url", newUrl.getId());
        return ResponseEntity.ok(new UrlResponse(redirectUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String id) {
        UrlEntity url = urlService.getById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getFullUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}


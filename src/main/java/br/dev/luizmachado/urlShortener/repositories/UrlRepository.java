package br.dev.luizmachado.urlShortener.repositories;

import br.dev.luizmachado.urlShortener.entities.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}

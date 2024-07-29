package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.domain.Url;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    @Query("from Url as u where u.longUrl = :key")
    Optional<Url> findfByLongUrl(String key);

    @Override
    Collection<Url> findAll();

    @Query("from Url as u where u.shortUrl = :key")
    Optional<Url> findfByShortUrl(String key);
}

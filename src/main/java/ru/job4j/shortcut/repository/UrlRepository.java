package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.domain.Url;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    @Query("from Url as u where u.longUrl = :key")
    Optional<Url> findByLongUrl(String key);

    @Override
    Collection<Url> findAll();

    @Query("from Url as u where u.shortUrl = :key")
    Optional<Url> findByShortUrl(String key);

    @Transactional
    @Modifying
    @Query("UPDATE Url u SET u.count = u.count + 1 WHERE u.shortUrl = :shortUrl")
    void incrementCount(@Param("shortUrl") String shortUrl);
}

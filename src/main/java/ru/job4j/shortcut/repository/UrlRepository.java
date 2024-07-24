package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.domain.Url;

import java.util.Collection;
@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    @Query("from Url as u where u.longUrl = :key")
    Url findfByLongUrl(String key);

    @Override
    Collection<Url> findAll();

    @Query("from Url as u where u.shortUrl = :key")
    Url findfByShortUrl(String key);
}

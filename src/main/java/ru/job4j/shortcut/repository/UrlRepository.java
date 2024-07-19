package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.domain.Url;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    @Query("from Url as u where u.longUrl = :key")
    Url findfByLongUrl(String key);

    @Query("from Url as u where u.shortUrl = :key")
    Url findfByShortUrl(String key);
}

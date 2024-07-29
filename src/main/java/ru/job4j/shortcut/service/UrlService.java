package ru.job4j.shortcut.service;

import ru.job4j.shortcut.domain.Url;

import java.util.Collection;
import java.util.Optional;

public interface UrlService {

    Collection<Url> findAll();

    Optional<Url> findByShortUrl(String shortUrl);

    Optional<Url> findByLongUrl(String longUrl);

}

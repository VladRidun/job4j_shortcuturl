package ru.job4j.shortcut.service;

import ru.job4j.shortcut.domain.Url;

import java.util.Collection;
import java.util.Optional;

public interface UrlService {

    Collection<Url> findAll();

    boolean ifNotExistBySite(String site);

    Optional<Url> findByShortUrl(String shortUrl);
}

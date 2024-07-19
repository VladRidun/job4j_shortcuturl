package ru.job4j.shortcut.service;

import ru.job4j.shortcut.domain.Url;

import java.util.Optional;

public interface UrlService {

    boolean ifNotExistBySite(String site);

    Optional<Url> findByShortUrl(String shortUrl);
}

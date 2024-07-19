package ru.job4j.shortcut.service;

import ru.job4j.shortcut.domain.Site;

import java.util.List;
import java.util.Optional;

public interface SiteService {
    List<Site> findAll();

    Optional<Site> findById(int id);

    Optional<Site> save(Site site);

    boolean ifNotExistBySite(String site);

    boolean update(Site site);

    boolean delete(Site site);

}

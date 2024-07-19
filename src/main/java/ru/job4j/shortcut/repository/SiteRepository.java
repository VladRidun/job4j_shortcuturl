package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.domain.Site;

import java.util.List;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    @Override
    List<Site> findAll();

    public Site findBySite(String site);

    public Site findByLogin(String login);

}

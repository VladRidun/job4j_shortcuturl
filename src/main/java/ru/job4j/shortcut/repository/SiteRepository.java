package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.domain.Site;

import java.util.List;

@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {
    @Override
    List<Site> findAll();

    Site findBySite(String site);

    Site findByLogin(String login);

}

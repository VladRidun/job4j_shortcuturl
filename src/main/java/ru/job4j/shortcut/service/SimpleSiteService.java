package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.repository.SiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService, UserDetailsService {
    private SiteRepository sites;
    private BCryptPasswordEncoder encoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSiteService.class);

    public List<String> registration(String site) {
        List<String> resultList = new ArrayList<>();
        Site newSite = new Site();
        if (ifNotExistBySite(site)) {
            try {
                String login = RandomStringUtils.randomAlphanumeric(7);
                String password = RandomStringUtils.randomAlphanumeric(7);
                newSite.setSite(site);
                resultList.add(login);
                newSite.setLogin(login);
                resultList.add(password);
                newSite.setPassword(encoder.encode(password));
                sites.save(newSite);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return resultList;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Site site = sites.findByLogin(username);
        if (site == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(site.getLogin(), site.getPassword(), emptyList());
    }

    @Override
    public List<Site> findAll() {
        return null;
    }

    @Override
    public Optional<Site> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Site> save(Site site) {
        return Optional.of(sites.save(site));
    }

    public boolean ifNotExistBySite(String site) {
        return sites.findBySite(site) == null;
    }

    @Override
    public boolean update(Site site) {
        var maybeSite = findById(site.getId());
        maybeSite.ifPresent(s -> sites.save(s));
        return maybeSite.isPresent();
    }

    @Override
    public boolean delete(Site site) {
        var maybeSite = findById(site.getId());
        maybeSite.ifPresent(s -> sites.delete(s));
        return maybeSite.isPresent();
    }
}
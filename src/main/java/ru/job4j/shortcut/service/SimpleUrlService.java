package ru.job4j.shortcut.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.domain.Url;
import ru.job4j.shortcut.repository.SiteRepository;
import ru.job4j.shortcut.repository.UrlRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimpleUrlService implements UrlService {

    private final UrlRepository urlRepository;

    private final SiteRepository siteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUrlService.class);

    @Override
    public Optional<Url> findByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    @Override
    public Optional<Url> findByLongUrl(String longUrl) {
        return urlRepository.findByLongUrl(longUrl);
    }

    public Optional<Url> convert(String longUrl) {
        String domainName = longUrl.replaceAll("http(s)?://|/.*", "");
        Site site = siteRepository.findBySite(domainName);
        var urlMayBe = findByLongUrl(longUrl);
        if (urlMayBe.isEmpty()) {
            Url newUrl = new Url();
            newUrl.setLongUrl(longUrl);
            newUrl.setShortUrl(RandomStringUtils.randomAlphanumeric(7));
            newUrl.setSite(site);
            urlRepository.save(newUrl);
        }
        return urlMayBe;
    }

    public Optional<Url> redirect(String shortUrl) {
        var optionalUrl = findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            urlRepository.incrementCount(shortUrl);
        }
        return optionalUrl;
    }

    public List<Url> findAll() {
        return urlRepository.findAll().stream().toList();
    }
}
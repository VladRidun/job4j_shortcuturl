package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Site;
import ru.job4j.shortcut.domain.Url;
import ru.job4j.shortcut.repository.SiteRepository;
import ru.job4j.shortcut.repository.UrlRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUrlService implements UrlService {
    private UrlRepository urlRepository;
    private SiteRepository siteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUrlService.class);

    public boolean ifNotExistBySite(String url) {
        return urlRepository.findfByLongUrl(url) == null;
    }

    @Override
    public Optional<Url> findByShortUrl(String shortUrl) {
        return Optional.of(urlRepository.findfByShortUrl(shortUrl));
    }

    public String convert(String longUrl) {
        String domainName =  longUrl.replaceAll("http(s)?://|/.*", "");
        Site site = siteRepository.findBySite(domainName);
        Url newUrl = new Url();
        String shortUrl;
        if (ifNotExistBySite(longUrl)) {
            shortUrl = RandomStringUtils.randomAlphanumeric(7);
            newUrl.setLongUrl(longUrl);
            newUrl.setShortUrl(shortUrl);
            newUrl.setSite(site);
            try {
                urlRepository.save(newUrl);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        Url resultUrl = urlRepository.findfByLongUrl(longUrl);
        shortUrl = resultUrl.getShortUrl();
        return shortUrl;
    }

    public Optional<String> redirect(String shortUrl) {
        if(findByShortUrl(shortUrl).isPresent()) {
            Url url = findByShortUrl(shortUrl).get();
            return Optional.of(url.getLongUrl());
        }
        else {
            return Optional.empty();
        }
    }

}

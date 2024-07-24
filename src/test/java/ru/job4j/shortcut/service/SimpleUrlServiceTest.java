package ru.job4j.shortcut.service;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import ru.job4j.shortcut.domain.Url;
import ru.job4j.shortcut.repository.UrlRepository;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
public class SimpleUrlServiceTest {
    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private SimpleUrlService simpleUrlService;

    @Test
    public void countForDifferentSites()  {
        int expectedCounterValueForSiteA = 1;
        int expectedCounterValueForSiteB = 1;

        String longUrl1 = "https://mail.ru/path?query=string#fragment";
        String shortUrl1 = "ABC123";
        Url url1 = Url.of().id(1).longUrl(longUrl1).shortUrl(shortUrl1).build();

        String longUrl2 = "https://rp5.ru/path?query=string#fragment";
        String shortUrl2 = "DEF456";
        Url url2 = Url.of().id(2).longUrl(longUrl2).shortUrl(shortUrl2).build();

        urlRepository.save(url1);
        urlRepository.save(url2);

        simpleUrlService.redirect("ABC123");
        simpleUrlService.redirect("DEF456");

        assertEquals(expectedCounterValueForSiteA, simpleUrlService.findByShortUrl("ABC123").get().getCount());
        assertEquals(expectedCounterValueForSiteB, simpleUrlService.findByShortUrl("DEF456").get().getCount());
    }
}
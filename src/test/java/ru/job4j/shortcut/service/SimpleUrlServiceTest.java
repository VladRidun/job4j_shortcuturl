package ru.job4j.shortcut.service;

import org.junit.Before;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.shortcut.domain.Url;
import ru.job4j.shortcut.repository.UrlRepository;

@SpringBootTest
public class SimpleUrlServiceTest {
    @Mock
    private UrlRepository urlRepository;
    @InjectMocks
    private SimpleUrlService simpleUrlService;
    private Url url1;
    private Url url2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        String longUrl1 = "longUrl1";
        String shortUrl1 = "shortUrl1";
        url1 = Url.of().id(1).longUrl(longUrl1).shortUrl(shortUrl1).build();

        String longUrl2 = "longUrl2";
        String shortUrl2 = "shortUrl2";
        url2 = Url.of().id(2).longUrl(longUrl2).shortUrl(shortUrl2).build();
    }
}
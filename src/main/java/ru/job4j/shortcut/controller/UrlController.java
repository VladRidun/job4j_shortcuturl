package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.domain.Url;
import ru.job4j.shortcut.service.SimpleUrlService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/urls")
@AllArgsConstructor
public class UrlController {
    private final SimpleUrlService urlService;

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody Map<String, String> body) {
        String url = body.get("url");
        String shortUrl = urlService.convert(url).get().getShortUrl();
        var bodyAnswer = new HashMap<>() {{
            put("code:", shortUrl);
        }}.toString();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Job4jshortcut", "job4j")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(bodyAnswer.length())
                .body(bodyAnswer);
    }

    @GetMapping("/redirect/{url}")
    public ResponseEntity<?> redirect(@PathVariable("url") String url) {
        var entity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var redirectUrl = urlService.redirect(url);
        if (redirectUrl.isPresent()) {
            entity = ResponseEntity.status(HttpStatus.valueOf(302))
                    .header("HTTP CODE", "302 REDIRECT URL = ".concat(redirectUrl.get().getLongUrl())).build();
        }
        return entity;
    }

    @GetMapping("/statistic")
    public ResponseEntity<?> count() {
        List<Url> urls = urlService.findAll();
        var bodyAnswer = new HashMap<>();
        if (!urls.isEmpty()) {
            for (Url url : urls
            ) {
                bodyAnswer.put("url:", url.getLongUrl());
                bodyAnswer.put("total:", url.getCount());
            }
        } else {
            bodyAnswer.put("statistic: ", " is null");
        }
        var contentResult = bodyAnswer.toString();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Job4jCustomHeader", "job4j")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(contentResult.length())
                .body(contentResult);
    }
}

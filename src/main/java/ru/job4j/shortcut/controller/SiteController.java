package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.service.SimpleSiteService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sites")
@AllArgsConstructor
public class SiteController {
    private final SimpleSiteService siteService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Map<String, String> body) {
        String site = body.get("site");
        var bodyAnswer = new HashMap<>();
        if (siteService.ifNotExistBySite(site)) {
            var regSiteResult = siteService.registration(site);
            bodyAnswer.put("registration:", true);
            bodyAnswer.put("login:", regSiteResult.get(0));
            bodyAnswer.put("password:", regSiteResult.get(1));
        } else {
            bodyAnswer.put("registration:", false);
        }
        var contentResult = bodyAnswer.toString();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Job4jCustomHeader", "job4j")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(contentResult.length())
                .body(contentResult);
    }
}

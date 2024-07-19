package ru.job4j.shortcut.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull(message = "Id must be non null")
    private int id;
    private String site;
    @NotBlank(message = "Login must be not empty")
    private String login;

    private String password;

    public Site(String login, String password, boolean registration) {
        this.login = login;
        this.password = password;
    }
}

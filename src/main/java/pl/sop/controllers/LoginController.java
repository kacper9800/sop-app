/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dao.entitiy.User;

import java.util.Date;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTime))  // Data ważności od
                .setExpiration(new Date(currentTime + 30000)) // Data ważności do
                .signWith(SignatureAlgorithm.HS512, user.getLogin())
                .compact();
    }

}

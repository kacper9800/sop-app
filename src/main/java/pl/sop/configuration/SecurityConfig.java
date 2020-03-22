/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("MODERATOR")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails, admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/test").permitAll()
                .antMatchers(HttpMethod.POST, "/api/test").hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/api/test").hasRole("ADMIN")
                .antMatchers("/api/test",
                        "/api/login",
                        "/api/registration").permitAll() //Dostęp bez uprawnień
                .anyRequest().hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable(); // csrf
    }
}

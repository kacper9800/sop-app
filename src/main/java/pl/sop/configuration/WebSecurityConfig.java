///*
// * System Obsługi Praktyk
// * Kacper Rzymkiewicz #2020
// */
//
package pl.sop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(User.builder()
//                .username("test")
//                .password(getPasswordEncoder().encode("test"))
//                .roles("ADMIN"));
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user").authenticated()
                .antMatchers("/colleges").hasRole("ADMIN")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }


}

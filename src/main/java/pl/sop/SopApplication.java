package pl.sop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import pl.sop.security.JWTFilter;

import java.util.Collections;

@SpringBootApplication
public class SopApplication {
    public static void main(String[] args) {
        SpringApplication.run(SopApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JWTFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/test"));
        return filterRegistrationBean;
    }
}

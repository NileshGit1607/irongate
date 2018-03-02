package com.irongate.web;

import com.irongate.web.filter.JWTAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {" com.irongate.services", "com.irongate.web"
        , "com.irongate.dao"})
public class Application {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JWTAuthFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

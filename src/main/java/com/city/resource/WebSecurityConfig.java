
package com.city.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .contentTypeOptions()
                .xssProtection()
                .cacheControl()
                .httpStrictTransportSecurity()
                .frameOptions();
            /*    .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy"," " +
                        "default-src 'self' *.mapbox.com; " +
                        "script-src 'self' www.google-analytics.com https://www.google-analytics.com *.mapbox.com https://*.mapbox.com 'unsafe-inline'; " +
                        "img-src 'self' *.recreation.gov *.mapbox.com https://*.mapbox.com https://www.google-analytics.com/ data:; " +
                        "style-src 'self' *.mapbox.com https://*.mapbox.com;"));
                        */
    }
}
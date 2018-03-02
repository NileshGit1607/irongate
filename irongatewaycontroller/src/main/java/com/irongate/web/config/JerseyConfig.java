package com.irongate.web.config;

import com.irongate.web.rs.AuthService;
import com.irongate.web.rs.GenericService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(AuthService.class);
        register(GenericService.class);
    }
}
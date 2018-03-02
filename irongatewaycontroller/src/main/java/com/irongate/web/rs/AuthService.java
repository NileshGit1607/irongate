package com.irongate.web.rs;

import com.irongate.entities.user.User;
import com.irongate.services.application.AuthenticationService;
import com.irongate.web.response.ResponseBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/auth")
public class AuthService {

    @Autowired
    private AuthenticationService authenticationService;

    @GET
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@QueryParam("username") String username, @QueryParam("password") String password) {

        if (username == null || password == null)
            return ResponseBuilder.buildErrorResponse("Username or Password is missing");

        User user = authenticationService.authenticate(username, password);

        if (user != null) {
            String token = Jwts.builder().setSubject(user.getUsername())
                    .claim("roles", user.getRole()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

            return ResponseBuilder.buildSucessResponse(token);

        }
        return ResponseBuilder.buildErrorResponse("Username or Password is incorrect");
    }
}

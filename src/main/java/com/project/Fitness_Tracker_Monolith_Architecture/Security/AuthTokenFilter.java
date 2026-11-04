package com.project.Fitness_Tracker_Monolith_Architecture.Security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("AuthTokenFilter called");

        try {
            String jwt = parseJwt(request);

            System.out.println("JWT: " + jwt);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

                System.out.println("TOKEN_HERE: " + jwt);
                String userid = jwtUtils.getUserIdfromtoken(jwt);
                Claims claims = jwtUtils.getAllClaims(jwt);
                List<String> roles = claims.get("roles",List.class);
                System.out.println("Role:"+roles);
                List<GrantedAuthority> authorities = List.of();
                if(roles!=null) {
                    authorities = roles.stream()
                            .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role))
                            // String->GrantedAuthority and then String->grantedAuthority
                            .toList();
                }
             //   System.out.println("USERID: " + userid);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userid,
                                null,
                                authorities
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                System.out.println(
                        "AUTHENTICATION: " +
                                SecurityContextHolder.getContext().getAuthentication()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
    private String parseJwt(HttpServletRequest request){
        String jwt = jwtUtils.getJwtFromHeader(request);
        return jwt;
    }
}

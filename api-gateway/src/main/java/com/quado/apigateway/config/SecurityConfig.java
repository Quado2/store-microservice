package com.quado.apigateway.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

//@Configuration
//@EnableWebFluxSecurity
public class SecurityConfig {

//  @Bean
//  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
//    serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
//        .authorizeExchange(exchange -> exchange.pathMatchers("/api/**")
//          .permitAll()
//            .anyExchange()
//            .authenticated())
//        .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//
//    return serverHttpSecurity.build();
//
//  }

}

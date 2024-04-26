/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo;



import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author andro
 */
@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public SessionLocaleResolver localeResolver() {

        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;

    }

    public LocaleChangeInterceptor localeChangeInterceptor() {

        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());

    }

    @Bean
    public UserDetailsService user() {
        UserDetails admin = User.builder().username("juan").password("{noop}123").roles("ADMIN").build();//Con esto creamos un usario y definimos sus caracteristicas
        UserDetails vendedor = User.builder().username("rebeca").password("{noop}456").roles("USER", "VENDEDOR").build();//Con esto creamos un usario y definimos sus caracteristicas
        UserDetails usuario = User.builder().username("pedro").password("{noop}789").roles("USER").build();//Con esto creamos un usario y definimos sus caracteristicas
        return new InMemoryUserDetailsManager(admin, vendedor, usuario);

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index.html");
        registro.addViewController("/index").setViewName("index");
         registro.addViewController("/Ventas2").setViewName("Ventas");
        registro.addViewController("/login").setViewName("login");
        registro.addViewController("/registro").setViewName("/registro");
        registro.addViewController("/contacto").setViewName("contacto");
         registro.addViewController("/Ubicacion").setViewName("Ubicacion.html");


    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/","/templates", "/index", "/js/**", "/webjars/**","/css/**","/assets/**","/assets/img/**","/registro/**","/Carrito/**")
                .permitAll()
                .requestMatchers("/index","/nosotros","/Menu","/Ubicacion","/categoria", "/Carrito/listado","/carrito/**","carrito/guardar","/Ventas2","/facturar/carrito")
                .hasAnyRole("VENDEDOR","USER")
                .requestMatchers("/**","/css/**","/templates","/js/**","/webjars/**")// Permite acceso a todas las URLs
                .hasRole("ADMIN")

        )
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}

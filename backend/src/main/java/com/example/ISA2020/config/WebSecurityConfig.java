package com.example.ISA2020.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.ISA2020.security.TokenUtils;
import com.example.ISA2020.security.auth.RestAuthenticationEntryPoint;
import com.example.ISA2020.security.auth.TokenAuthenticationFilter;
import com.example.ISA2020.service.Impl.UserServiceImpl;

import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Hesiranje lozinke korisnika pomocu BCrypt funkcije.
 	// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
    // Kod punjenja podacima u data.sql napisati u komentaru lozinku jer pamti njen hes, a ne original!!!
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Autowired 
	private UserServiceImpl jwtUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private TokenUtils tokenUtils;
    
    // Registrujemo authentication manager koji ce da uradi autentifikaciju korisnika za nas
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
		 
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	//stateless komuikacija klijent server
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            
            //401 greska za neautorizovane zahteve, moze i nazad na login stranicu
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
            
            //ovim putanjama mogu svi korisnici da pristupe
            .authorizeRequests()
            					.antMatchers("/api/**").permitAll() //OVO IZMENITI DA BUDE SAMO HOME I RESURSI KOJE SME!
            
            					//svaki zahtev mora biti autorizovan					
            					.anyRequest().authenticated().and()
            
            //ubacivanje CORS filtera u lanac filtera
            .cors().and()
        	
            //ubacivanje filtera za proveru tokena u lanac filtera
            .addFilterBefore(
        		new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
        		BasicAuthenticationFilter.class
        	);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/api/**");
        web.ignoring().antMatchers(HttpMethod.PUT, "/api/auth");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/noAuth/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/file/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/file/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/rabbitmq/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/rabbitmq/**");
    }
    
    @Bean
    public GrpcAuthenticationReader grpcAuthenticationReader() {
        return new BasicGrpcAuthenticationReader();
    }

}

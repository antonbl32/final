package it.free.final_spring.security;

import it.free.final_spring.entity.Permissions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsService userService;

    public ConfigSecurity(@Qualifier("userDetailsServiceImpl") UserDetailsService userService) {
        this.userService = userService;
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").hasAuthority(Permissions.USERS_READ.getPermission())
                .antMatchers("/noteadd").hasAuthority(Permissions.USERS_READ.getPermission())
                .antMatchers("/note").hasAuthority(Permissions.USERS_READ.getPermission())
                .antMatchers("/all").hasAuthority(Permissions.USERS_READ.getPermission())
                .antMatchers("/add").hasAuthority(Permissions.USERS_WRITE.getPermission())
                .antMatchers("/users").hasAuthority(Permissions.USERS_READ.getPermission())
                .antMatchers("/monitor/**").hasAuthority(Permissions.USERS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/").hasAuthority(Permissions.USERS_WRITE.getPermission())
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}

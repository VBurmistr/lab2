package nc.apps.config;

import nc.apps.dto.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/book/update/**",
                        "/book/remove/**",
                        "/book/add/",
                        "/language/add/",
                        "/author/add/",
                        "/category/add/",
                        "/publisher/add/").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET,
                        "/addauthor",
                        "/addcategory",
                        "/addlanguage",
                        "/addnewbook",
                        "/addpublisher",
                        "/editbook/**").hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        UserDetails user = userBuilder
                .username("volodymyr1")
                .password("password")
                .roles(Role.USER.name())
                .build();

        UserDetails admin = userBuilder
                .username("volodymyr2")
                .password("password")
                .roles(Role.USER.name(),
                        Role.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}

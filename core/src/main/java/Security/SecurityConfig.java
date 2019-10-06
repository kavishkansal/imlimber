package Security;

import Exceptions.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder()
                .encode("admin")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws UnAuthorizedException {
        try {
            http.authorizeRequests()
                    .antMatchers("/securityNone").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic()
                    .authenticationEntryPoint(authenticationEntryPoint);

//            http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
        } catch (Exception e) {
            throw new UnAuthorizedException(e.getMessage(), e);
        }

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

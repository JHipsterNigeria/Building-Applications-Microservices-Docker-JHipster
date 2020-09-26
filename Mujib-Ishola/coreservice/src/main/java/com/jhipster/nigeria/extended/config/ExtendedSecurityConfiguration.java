package com.jhipster.nigeria.extended.config;

import com.jhipster.nigeria.config.SecurityConfiguration;
import com.jhipster.nigeria.config.oauth2.OAuth2Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@Configuration
@EnableResourceServer
public class ExtendedSecurityConfiguration extends SecurityConfiguration {

    private final Logger log = LoggerFactory.getLogger(ExtendedSecurityConfiguration.class);

    private final OAuth2Properties oAuth2Properties;

    public ExtendedSecurityConfiguration(OAuth2Properties oAuth2Properties, OAuth2Properties oAuth2Properties1) {
        super(oAuth2Properties);
        this.oAuth2Properties = oAuth2Properties1;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        log.info("Initializing extending SecurityConfiguration");
        super.configure(http);
        http.authorizeRequests()
            .antMatchers("/api/v1/register").permitAll()
            .antMatchers("/api/v1/reset-password/init").permitAll()
            .antMatchers("/api/v1/ext/**").permitAll()
            .antMatchers("/api/v1/customer/newsletter/**").permitAll()
            .antMatchers("/api/v1/transaction/receipt/request").permitAll()
            .antMatchers("/ext/topup/notification/**").permitAll()
            .antMatchers("/api/v1/activate").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

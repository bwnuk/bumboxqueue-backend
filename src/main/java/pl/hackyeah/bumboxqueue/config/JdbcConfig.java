package pl.hackyeah.bumboxqueue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {
    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUsername;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    @Value("${spring.datasource.driver}")
    private String mysqlDriver;


    @Bean
    public JdbcTemplate jdbcTemplate() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlDriver);
        dataSource.setUrl(mysqlUsername);
        dataSource.setUsername(mysqlUsername);
        dataSource.setPassword(mysqlPassword);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate;
    }
}

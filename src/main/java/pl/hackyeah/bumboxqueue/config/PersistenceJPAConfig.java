package pl.hackyeah.bumboxqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    return transactionManager;
  }
}
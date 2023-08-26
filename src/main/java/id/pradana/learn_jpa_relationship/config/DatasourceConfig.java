package id.pradana.learn_jpa_relationship.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("server")
public class DatasourceConfig {

  @Value("${MYSQL_URL}")
  private String url;

  @Value("${MYSQLUSER}")
  private String username;

  @Value("${MYSQLPASSWORD}")
  private String password;

  @Bean
  public DataSource dataSource() {
    DataSourceBuilder builder = DataSourceBuilder.create();
    builder.driverClassName("com.mysql.cj.jdbc.Driver");
    builder.url("jdbc:" + url);
    builder.username(username);
    builder.password(password);
    return builder.build();
  }
}

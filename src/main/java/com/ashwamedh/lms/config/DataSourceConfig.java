package com.ashwamedh.lms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${DB_URL:jdbc:h2:file:./lms_db;AUTO_SERVER=TRUE}")
    private String dbUrl;

    @Value("${DB_USERNAME:sa}")
    private String username;

    @Value("${DB_PASSWORD:password}")
    private String password;

    @Value("${DB_DRIVER:org.h2.Driver}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        // Render provides connectionString starting with postgres://, but JDBC needs jdbc:postgresql://
        if (dbUrl.startsWith("postgres://")) {
            dbUrl = dbUrl.replaceFirst("postgres://", "jdbc:postgresql://");
        }
        
        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }
}

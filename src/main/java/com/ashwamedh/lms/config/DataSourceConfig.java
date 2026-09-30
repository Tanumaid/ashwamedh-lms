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
        // Render provides connectionString starting with postgres://user:pass@host/db
        // JDBC needs jdbc:postgresql://host/db (credentials are passed separately)
        if (dbUrl.startsWith("postgres://")) {
            try {
                java.net.URI uri = new java.net.URI(dbUrl);
                dbUrl = "jdbc:postgresql://" + uri.getHost() + ":" + (uri.getPort() == -1 ? 5432 : uri.getPort()) + uri.getPath();
            } catch (Exception e) {
                dbUrl = dbUrl.replaceFirst("postgres://", "jdbc:postgresql://");
            }
        }
        
        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }
}

package com.mailanalyzer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(excludeName = {"org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    // 1. CORS Engelini en yüksek öncelikle kaldıran filtre
    @Bean
    public FilterRegistrationBean<CorsFilter> customCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*"); 
        
        source.registerCorsConfiguration("/**", config);
        
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); 
        
        return bean;
    }

    // 2. Şifre alanı dahil edilerek düzeltilmiş test kullanıcısı ekleme bloğu
    // 2. Şifre alanı dahil edilerek düzeltilmiş test kullanıcısı ekleme bloğu
    @Bean
    CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                // Kendi test hesabın
                jdbcTemplate.update(
                    "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?) ON CONFLICT (email) DO NOTHING",
                    "berat@gmail.com", "Berat", "123456", "USER"
                );
                
                // Jüri / Ekstra Test Hesapları
                jdbcTemplate.update(
                    "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?) ON CONFLICT (email) DO NOTHING",
                    "test@analyzer.com", "Test User", "123456", "USER"
                );
                jdbcTemplate.update(
                    "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?) ON CONFLICT (email) DO NOTHING",
                    "admin@analyzer.com", "Admin User", "123456", "ADMIN"
                );
                jdbcTemplate.update(
                    "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?) ON CONFLICT (email) DO NOTHING",
                    "demo@analyzer.com", "Demo User", "123456", "USER"
                );
                
                System.out.println("--> Test kullanicilari basariyla eklendi / kontrol edildi!");
            } catch (Exception e) {
                System.out.println("--> VERITABANI HATASI: " + e.getMessage());
            }
        };
    }
}
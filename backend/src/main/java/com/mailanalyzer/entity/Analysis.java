package com.mailanalyzer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "analyses")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Bire-Çok (One-To-Many) İlişkisi: Bir analizin mutlaka bir kullanıcısı olmalıdır.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "content_type", nullable = false, length = 20)
    private String contentType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "risk_level", nullable = false, length = 20)
    private String riskLevel;

    // PostgreSQL'deki JSONB yapısını Java'ya tanıttığımız bölüm
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "risk_reasons", columnDefinition = "jsonb")
    private String riskReasons;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
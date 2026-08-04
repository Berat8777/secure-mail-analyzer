# Veritabanı Şeması (Database Schema)

Bu doküman, Secure Mail Analyzer projesinin PostgreSQL veritabanı yapısını açıklar. Sistemde veri bütünlüğünü sağlamak için ilişkisel veritabanı modeli kullanılmıştır.

## 1. `users` Tablosu
Sisteme giriş yapan kullanıcıların ve yetki rollerinin tutulduğu ana tablodur.

| Sütun Adı  | Veri Tipi | Özellikler                  | Açıklama                                  |
| :---       | :---      | :---                        | :---                                      |
| `id`       | BIGINT    | Primary Key, Auto Increment | Kullanıcının benzersiz kimliği            |
| `email`    | VARCHAR   | Unique, Not Null            | Giriş için kullanılan e-posta adresi      |
| `username` | VARCHAR   | Not Null                    | Kullanıcının görünen adı                  |
| `password` | VARCHAR   | Not Null                    | Güvenlik için hashlenmiş şifre            |
| `role`     | VARCHAR   | Default: 'USER'             | Yetkilendirme rolü (Örn: USER, ADMIN)     |

## 2. `analyses` Tablosu (Analiz Geçmişi)
Kullanıcıların gerçekleştirdiği mail ve link güvenlik analizlerinin sonuçlarını saklar.

| Sütun Adı         | Veri Tipi | Özellikler                  | Açıklama                                      |
| :---              | :---      | :---                        | :---                                          |
| `id`              | BIGINT    | Primary Key, Auto Increment | Analizin benzersiz kimliği                    |
| `user_email`      | VARCHAR   | Foreign Key                 | Analizi yapan kullanıcı (users.email ile eşleşir)|
| `content`         | TEXT      | Not Null                    | Analiz edilen şüpheli metin veya link         |
| `type`            | VARCHAR   | Not Null                    | İçerik tipi (Örn: MAIL, LINK)                 |
| `risk_level`      | VARCHAR   | Not Null                    | Risk seviyesi (LOW, MEDIUM, HIGH)             |
| `analysis_result` | TEXT      | Not Null                    | Yapay zeka (LLM) tarafından üretilen rapor    |
| `created_at`      | TIMESTAMP | Default: CURRENT_TIMESTAMP  | Analizin yapıldığı tarih ve saat              |

> **Not:** Veritabanı başlangıç verileri (Seed Data) uygulamanın `CommandLineRunner` arayüzü üzerinden Spring Boot ayağa kalkarken otomatik olarak enjekte edilmektedir.
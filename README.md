# 🛡️ Secure Mail & URL Analyzer Platformu

Bu proje; yapay zeka (Google Gemini LLM) ve akıllı kural tabanlı hibrit motorlar kullanarak e-posta içeriklerini ve URL'leri oltalama (phishing), sosyal mühendislik ve zararlı bağlantı risklerine karşı analiz eden kurumsal düzeyde bir **Siber Güvenlik Analiz Platformudur**.

---

## 🚀 Proje Mimarisi ve Teknolojiler

Platform, modern mikroservis mimarisine uygun olarak 3 ana katmandan oluşmaktadır:

* **Backend:** Java 21 & Spring Boot (JPA/Hibernate, RESTful API, LLM Entegrasyonu)
* **Database:** PostgreSQL 15 (İlişkisel veri yönetimi ve analiz geçmişi kayıtları)
* **Frontend:** Angular / Modern Web Teknolojileri (Nginx üzerinde sunulan SPA arayüzü)
* **Orkestrasyon:** Docker & Docker Compose / Kubernetes Deployment ve Service konfigürasyonları

---

## 📌 Temel Özellikler

* **E-Posta Güvenlik Analizi:** Gelen metinleri aciliyet dili, sahte kurum bildirimleri, şifre/TC kimlik talepleri ve güvensiz bağlantılar açısından inceler.
* **URL (Bağlantı) Güvenliği:** Doğrudan linkleri tarayarak HTTP/HTTPS protokol durumunu, domain uzunluğunu ve marka taklidi (phishing) risklerini raporlar.
* **Geçmiş Analizler:** Yapılan tüm tarama geçmişini veritabanında saklar ve dinamik bir tabloda listeler.
* **Admin Dashboard & İstatistikler:** Sistem genelindeki toplam analiz sayılarını, risk dağılımlarını (Yüksek, Orta, Düşük) ve en sık karşılaşılan tehdit tiplerini istatistiksel kartlarla sunar.
* **Akıllı Yedek (Fallback) Güvenlik Motoru:** LLM servislerine ulaşılamadığı durumlarda devreye giren yerel kural tabanlı motor sayesinde sistem kesintisiz çalışmaya devam eder.

---

## ⚙️ Kurulum ve Çalıştırma (Docker ile)

Projeyi bilgisayarınızda ayağa kaldırmak için tek yapmanız gereken kök dizinde terminal açıp şu komutu çalıştırmaktır:

```powershell
docker-compose up --build -d

Frontend Arayüzü: http://localhost:4200

Backend API: http://localhost:8080

☸️ Kubernetes (K8s) Dağıtım Adımları
Projeyi bulut sunuculara veya cluster ortamına taşımak için hazırlanan deployment ve service YAML konfigürasyonları:

Bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
Geliştirici: Ali Berat Algün

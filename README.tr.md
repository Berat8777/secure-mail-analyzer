# 🛡️ Secure Mail & URL Analyzer Platform

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge\&logo=springboot)
![Angular](https://img.shields.io/badge/Angular-20-DD0031?style=for-the-badge\&logo=angular)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=for-the-badge\&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge\&logo=docker)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Ready-326CE5?style=for-the-badge\&logo=kubernetes)

</p>

Kurumsal düzeyde geliştirilen **Secure Mail & URL Analyzer**, yapay zeka (**Google Gemini LLM**) ve akıllı **kural tabanlı hibrit analiz motoru** kullanarak e-posta içeriklerini ve URL'leri oltalama (Phishing), sosyal mühendislik ve zararlı bağlantılara karşı analiz eden bir **Siber Güvenlik Analiz Platformudur**.

LLM servislerine ulaşılamadığı durumlarda sistem, yerel kural tabanlı analiz motoruna otomatik geçiş yaparak kesintisiz hizmet sunmaya devam eder.

---

# 🚀 Proje Mimarisi

```text
                     +----------------------+
                     |    Angular Frontend  |
                     +----------+-----------+
                                |
                                |
                     REST API (HTTP/JSON)
                                |
                                ▼
                  +----------------------------+
                  | Spring Boot Backend (Java) |
                  +------------+---------------+
                               |
            +------------------+------------------+
            |                                     |
            ▼                                     ▼
 +-----------------------+          +---------------------------+
 | Google Gemini LLM API |          | Rule-Based Security Engine|
 +-----------------------+          +---------------------------+
            |                                     |
            +------------------+------------------+
                               |
                               ▼
                     +------------------+
                     | PostgreSQL 15 DB |
                     +------------------+
```

---

# 🛠️ Kullanılan Teknolojiler

| Katman        | Teknoloji                                        |
| ------------- | ------------------------------------------------ |
| Backend       | Java 21, Spring Boot, Spring Data JPA, Hibernate |
| Frontend      | Angular, TypeScript                              |
| Database      | PostgreSQL 15                                    |
| AI            | Google Gemini LLM                                |
| Container     | Docker, Docker Compose                           |
| Orchestration | Kubernetes                                       |
| API           | RESTful API                                      |
| Build Tool    | Maven                                            |

---

# ✨ Temel Özellikler


## 📧 E-Posta Güvenlik Analizi
<img width="1896" height="855" alt="Ekran görüntüsü 2026-08-03 232941" src="https://github.com/user-attachments/assets/0948627d-b8fb-4f47-9d71-0eab907091df" />

* Aciliyet dili analizi
* Şüpheli kurum bildirimi tespiti
* Kimlik bilgisi (TC, şifre vb.) taleplerinin tespiti
* Sosyal mühendislik analizi
* Güvensiz bağlantı kontrolü
* Yapay zeka destekli risk değerlendirmesi

---

## 🔗 URL Güvenlik Analizi
<img width="1896" height="857" alt="Ekran görüntüsü 2026-08-03 232956" src="https://github.com/user-attachments/assets/63eea8da-d8e1-4fbb-b258-cbbab014c4df" />

* HTTP / HTTPS kontrolü
* Domain uzunluğu analizi
* Marka taklidi (Brand Impersonation)
* Şüpheli karakter kullanımı
* Phishing risk değerlendirmesi

---

## 📜 Geçmiş Analizler
<img width="1897" height="860" alt="Ekran görüntüsü 2026-08-03 233011" src="https://github.com/user-attachments/assets/7345c06d-2654-4ddc-b574-33f0864258f3" />
Sistem üzerinde gerçekleştirilen tüm e-posta ve URL tarama geçmişi PostgreSQL veritabanında güvenli bir şekilde saklanır. Kullanıcılar bu modül üzerinden:

* Geçmişte yapılan tüm analizlerin liste görünümüne ulaşabilir,
* Hedef içeriklerin tespit edilen **Risk Seviyelerini (Yüksek, Orta, Düşük)** ve risk puanlarını inceleyebilir,
* Yapay zeka veya kural motoru tarafından üretilen detaylı güvenlik açıklamalarını ve tespit edilen tehditleri geriye dönük olarak gözden geçirebilir.
---
## 📊Yönetici Paneli
<img width="1897" height="861" alt="Ekran görüntüsü 2026-08-03 233019" src="https://github.com/user-attachments/assets/80fc042c-6623-4e2d-b5d3-bb8502634b3d" />

* Toplam analiz sayısı
* Günlük analiz istatistikleri
* Risk dağılımı

  * 🟥 Yüksek
  * 🟨 Orta
  * 🟩 Düşük
* En sık karşılaşılan tehdit türleri
* Son yapılan analizler

görüntülenebilir.

---

# 🧠 Hibrit Güvenlik Motoru

Platform iki farklı analiz yöntemini birlikte kullanır.

### 1. Yapay Zeka Analizi

Google Gemini LLM;

* içerik analizi
* bağlam değerlendirmesi
* sosyal mühendislik tespiti
* risk puanlaması

işlemlerini gerçekleştirir.

### 2. Fallback Rule Engine

LLM servisine erişilemediğinde;

* Regex tabanlı kontroller
* Anahtar kelime analizi
* URL güvenlik kontrolleri
* Risk puanlama algoritması

devreye girerek sistemin çalışmasını sürdürmesini sağlar.

---

# 🗄️ Veritabanı

Platform PostgreSQL kullanmaktadır.

Saklanan veriler:

* Analiz edilen e-posta
* Analiz edilen URL
* Risk seviyesi
* Risk puanı
* Tespit edilen tehditler
* Analiz zamanı
* Yapay zeka cevabı

---

# 🐳 Docker ile Kurulum

Projeyi klonlayın.

```bash
git clone https://github.com/Berat8777/secure-mail-analyzer.git
```

Proje dizinine girin.

```bash
cd secure-mail-analyzer
```

Container'ları başlatın.

```bash
docker-compose up --build -d
```
## 🧪 Test Hesapları
Sistem, analiz geçmişini belirli kullanıcılara bağlamak üzere yapılandırılmıştır. Gerçek veri kullanımını engellemek amacıyla, projeyi test ederken arayüzdeki "E-Posta Adresi" alanına aşağıdaki hazır test hesaplarından birini girebilirsiniz:
* `test@analyzer.com`
* `admin@analyzer.com`
* `demo@analyzer.com`
---

## Uygulama Adresleri

| Servis      | Adres                 |
| ----------- | --------------------- |
| Frontend    | http://localhost:4200 |
| Backend API | http://localhost:8080 |

---

# ☸️ Kubernetes Dağıtımı

Projeyi Minikube veya Docker Desktop Kubernetes üzerinde ayağa kaldırmak için YAML dosyaları modüler olarak ayrılmıştır. Terminalde sırasıyla şu komutları çalıştırabilirsiniz:

```bash
# Tüm mikroservisleri ve veritabanını tek seferde ayağa kaldırmak için:
kubectl apply -f k8s/

# Veya ayrı ayrı başlatmak isterseniz:
kubectl apply -f k8s/database.yaml
kubectl apply -f k8s/backend.yaml
kubectl apply -f k8s/frontend.yaml

---

# 📁 Proje Yapısı

```text
Secure-Mail-URL-Analyzer/
│
├── backend/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── Dockerfile
│   └── package.json
│
├── k8s/
│   ├── deployment.yaml
│   └── service.yaml
│
├── docker-compose.yml
└── README.md
```

---

# 🔮 Gelecek Geliştirmeler

* JWT Authentication
* Rol tabanlı yetkilendirme
* Gerçek zamanlı bildirimler
* SIEM entegrasyonu
* PDF analiz raporu
* Dosya (Ek) güvenlik analizi
* VirusTotal entegrasyonu
* WHOIS ve DNS sorguları
* Çoklu LLM desteği (Gemini, OpenAI, Claude)

---

# 👨‍💻 Geliştirici

**Ali Berat Algün**

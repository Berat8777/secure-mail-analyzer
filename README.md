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

* Aciliyet dili analizi
* Şüpheli kurum bildirimi tespiti
* Kimlik bilgisi (TC, şifre vb.) taleplerinin tespiti
* Sosyal mühendislik analizi
* Güvensiz bağlantı kontrolü
* Yapay zeka destekli risk değerlendirmesi

---

## 🔗 URL Güvenlik Analizi

* HTTP / HTTPS kontrolü
* Domain uzunluğu analizi
* Marka taklidi (Brand Impersonation)
* Şüpheli karakter kullanımı
* Phishing risk değerlendirmesi

---

## 📊 Dashboard

Yönetici panelinde;

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
git clone <repository-url>
```

Proje dizinine girin.

```bash
cd Secure-Mail-URL-Analyzer
```

Container'ları başlatın.

```bash
docker-compose up --build -d
```

---

## Uygulama Adresleri

| Servis      | Adres                 |
| ----------- | --------------------- |
| Frontend    | http://localhost:4200 |
| Backend API | http://localhost:8080 |

---

# ☸️ Kubernetes Dağıtımı

```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

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

Cyber Security & Software Developer

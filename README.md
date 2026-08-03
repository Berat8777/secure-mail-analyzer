# 🛡️ Secure Mail & URL Analyzer Platform

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![Angular](https://img.shields.io/badge/Angular-20-DD0031?style=for-the-badge&logo=angular)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Ready-326CE5?style=for-the-badge&logo=kubernetes)

</p>

**Secure Mail & URL Analyzer** is an enterprise-grade **Cybersecurity Analysis Platform** that leverages **Artificial Intelligence (Google Gemini LLM)** together with an intelligent **rule-based hybrid analysis engine** to analyze emails and URLs against phishing attacks, social engineering attempts, and malicious links.

If the LLM service becomes unavailable, the platform automatically switches to its local rule-based analysis engine, ensuring uninterrupted service availability.

---

# 🚀 Project Architecture

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

# 🛠️ Technologies Used

| Layer | Technology |
|--------|------------|
| Backend | Java 21, Spring Boot, Spring Data JPA, Hibernate |
| Frontend | Angular, TypeScript |
| Database | PostgreSQL 15 |
| AI | Google Gemini LLM |
| Containerization | Docker, Docker Compose |
| Orchestration | Kubernetes |
| API | RESTful API |
| Build Tool | Maven |

---

# ✨ Core Features

## 📧 Email Security Analysis

* Urgency language detection
* Suspicious organization detection
* Credential request detection (ID numbers, passwords, etc.)
* Social engineering analysis
* Unsafe link detection
* AI-powered risk assessment

---

## 🔗 URL Security Analysis

* HTTP / HTTPS validation
* Domain length analysis
* Brand impersonation detection
* Suspicious character analysis
* Phishing risk assessment

---

## 📊 Dashboard

The administrator dashboard provides:

* Total number of analyses
* Daily analysis statistics
* Risk distribution

  * 🟥 High
  * 🟨 Medium
  * 🟩 Low

* Most common threat categories
* Recent analyses

---

# 🧠 Hybrid Security Engine

The platform combines two different analysis approaches.

### 1. Artificial Intelligence Analysis

Google Gemini LLM performs:

* Content analysis
* Context evaluation
* Social engineering detection
* Risk scoring

---

### 2. Fallback Rule Engine

If the LLM service is unavailable, the system automatically switches to the local analysis engine, which performs:

* Regex-based validation
* Keyword analysis
* URL security validation
* Risk scoring algorithms

This ensures uninterrupted platform availability.

---

# 🗄️ Database

The platform uses PostgreSQL.

Stored data includes:

* Analyzed email content
* Analyzed URL
* Risk level
* Risk score
* Detected threats
* Analysis timestamp
* AI-generated response

---

# 🐳 Docker Installation

Clone the repository.

```bash
git clone <repository-url>
```

Navigate to the project directory.

```bash
cd Secure-Mail-URL-Analyzer
```

Build and start the containers.

```bash
docker-compose up --build -d
```

---

## Application URLs

| Service | Address |
|---------|---------|
| Frontend | http://localhost:4200 |
| Backend API | http://localhost:8080 |

---

# ☸️ Kubernetes Deployment

```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

---

# 📁 Project Structure

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

# 🔮 Future Improvements

* JWT Authentication
* Role-Based Authorization
* Real-Time Notifications
* SIEM Integration
* PDF Analysis Reports
* Attachment Security Analysis
* VirusTotal Integration
* WHOIS & DNS Lookup
* Multi-LLM Support (Gemini, OpenAI, Claude)

---

# 👨‍💻 Developer

**Ali Berat Algün**

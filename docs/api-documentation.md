# API Dokümantasyonu (REST API)

Bu doküman, sistemin dış dünyaya açılan RESTful uç noktalarını (endpoints) listeler. CORS politikaları gereği tüm istekler `http://localhost:4200` adresine açıktır.

## 1. Yeni Analiz Oluşturma
Sisteme şüpheli bir e-posta veya link göndererek yapay zeka destekli analiz sonucunu döndürür.

* **URL:** `/api/analyze`
* **Method:** `POST`
* **Headers:** `Content-Type: application/json`

**İstek Gövdesi (Request Body):**
```json
{
  "email": "berat@gmail.com",
  "content": "Lütfen hesabınızı doğrulamak için acilen buraya tıklayın!",
  "type": "MAIL"
}
```

**Başarılı Yanıt (200 OK):**
```json
{
  "riskLevel": "HIGH",
  "explanation": "Bu mesaj aciliyet dili kullanmaktadır. 'Acilen buraya tıklayın' ifadesi oltalama (phishing) saldırılarında sıkça görülen bir sosyal mühendislik taktiğidir.",
  "timestamp": "2026-08-04T10:15:30Z"
}
```

## 2. Kullanıcı Geçmişini Getirme
Belirli bir kullanıcının daha önce yaptığı tüm analizleri tarihe göre sıralı olarak getirir.

* **URL:** `/api/analyze/history?email={user_email}`
* **Method:** `GET`

**Başarılı Yanıt (200 OK):**
```json
[
  {
    "id": 1,
    "type": "LINK",
    "content": "[http://secure-login-update-account.com](http://secure-login-update-account.com)",
    "riskLevel": "HIGH",
    "created_at": "2026-08-03T14:20:00Z"
  }
]
```

## 3. Admin İstatistikleri
Admin dashboard üzerinde gösterilen toplam analiz sayısı ve risk dağılımlarını döndürür.

* **URL:** `/api/analyze/stats`
* **Method:** `GET`

**Başarılı Yanıt (200 OK):**
```json
{
  "totalAnalyses": 150,
  "highRiskCount": 45,
  "mediumRiskCount": 30,
  "lowRiskCount": 75,
  "mostCommonRiskType": "Phishing Link"
}
```
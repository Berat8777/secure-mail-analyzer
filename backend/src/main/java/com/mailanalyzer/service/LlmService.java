package com.mailanalyzer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LlmService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public LlmService() {
        this.restTemplate = new RestTemplate();
    }

    public String analyzeContent(String content, String contentType) {
        String url = apiUrl + "?key=" + apiKey;

        String prompt = "Sen kıdemli bir siber güvenlik ve oltalama (phishing) analisti uzmanısın.\n"
                + "Aşağıdaki " + contentType + " içeriğini oltalama, sosyal mühendislik, marka taklidi ve aciliyet dili açısından incele.\n\n"
                + "KRİTİK KURAL:\n"
                + "- Eğer metin hesap kapatma tehdidi, sahte giriş/doğrulama bağlantısı, şifre isteme veya aciliyet baskısı içeriyorsa, 'riskLevel' KESİNLİKLE 'HIGH' olmalıdır.\n\n"
                + "İçerik: " + content + "\n\n"
                + "Lütfen SADECE aşağıdaki JSON formatında cevap ver. Markdown işaretleri (```json vb.) KULLANMA:\n"
                + "{\n"
                + "  \"riskLevel\": \"LOW, MEDIUM veya HIGH\",\n"
                + "  \"riskReasons\": [\"Sebep 1\", \"Sebep 2\"]\n"
                + "}";

        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);

        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("parts", List.of(part));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(contentMap));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            Map<String, Object> responseBody = response.getBody();
            
            if (responseBody != null && responseBody.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
                if (candidates != null && !candidates.isEmpty()) {
                    Map<String, Object> contentObj = (Map<String, Object>) candidates.get(0).get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) contentObj.get("parts");
                    String rawText = (String) parts.get(0).get("text");
                    
                    // Markdown temizliği (API bazen ```json ... ``` dönebiliyor)
                    return rawText.replaceAll("```json", "").replaceAll("```", "").trim();
                }
            }
            throw new RuntimeException("API boş veya geçersiz yanıt döndü.");

        } catch (Exception e) {
            System.err.println("Gemini API Hatası (Güvenli Mod Devrede): " + e.getMessage());
            
            // --- AKILLI YEDEK (FALLBACK) MOTORU ---
            String lower = content.toLowerCase();
            boolean isUrl = lower.startsWith("http://") || lower.startsWith("https://") || lower.contains("www.");

            if (isUrl) {
                // URL Analizi İçin Özel Tehdit Açıklamaları
                boolean isHttp = lower.startsWith("http://");
                boolean isSuspicious = lower.length() > 30 || lower.contains("support") || lower.contains("verify") || lower.contains("update") || lower.contains("reset");
                
                if (isHttp || isSuspicious) {
                    return "{\n  \"riskLevel\": \"HIGH\",\n  \"riskReasons\": [\"Şüpheli domain ve marka taklidi (phishing) şüphesi\", \"Güvensiz HTTP protokolü kullanımı veya uzun/karmaşık URL yapısı\"]\n}";
                } else {
                    return "{\n  \"riskLevel\": \"LOW\",\n  \"riskReasons\": [\"URL yapısı olağan ve güvenli görünmektedir\"]\n}";
                }
            } else {
                // E-Posta / Metin Analizi İçin Tehdit Açıklamaları
                boolean isHighRisk = lower.contains("bloke") || lower.contains("şifre") || lower.contains("12 saat") 
                                  || lower.contains("tc kimlik") || lower.contains("http://") || lower.contains("acil");

                if (isHighRisk) {
                    return "{\n  \"riskLevel\": \"HIGH\",\n  \"riskReasons\": [\"Aciliyet baskısı ve tehdit dili algılandı\", \"Kritik bilgi veya güvensiz bağlantı talebi var\"]\n}";
                } else {
                    return "{\n  \"riskLevel\": \"LOW\",\n  \"riskReasons\": [\"Belirgin bir siber güvenlik tehdidi bulunamadı\"]\n}";
                }
            }
        }
    }
}
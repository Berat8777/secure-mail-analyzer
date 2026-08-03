package com.mailanalyzer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailanalyzer.entity.Analysis;
import com.mailanalyzer.entity.User;
import com.mailanalyzer.service.AnalysisService;
import com.mailanalyzer.service.LlmService;
import com.mailanalyzer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;
    private final UserService userService;
    private final LlmService llmService;

    private final ObjectMapper objectMapper = new ObjectMapper(); 

    public AnalysisController(AnalysisService analysisService, UserService userService, LlmService llmService) {
        this.analysisService = analysisService;
        this.userService = userService;
        this.llmService = llmService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAnalysis(@RequestBody Map<String, Object> requestData) {
        String userEmail = (String) requestData.get("email");
        
        if (userEmail == null || userEmail.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("E-posta adresi zorunludur.");
        }

        Optional<User> userOptional = userService.findByEmail(userEmail.trim());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı!");
        }

        String contentType = (String) requestData.get("contentType");
        String content = (String) requestData.get("content");
        
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Analiz metni boş olamaz.");
        }

        // 1. LLM'e metni gönder ve sonucu al
        String llmResponse = llmService.analyzeContent(content, contentType != null ? contentType : "EMAIL");
        
        String riskLevel = "MEDIUM";
        String riskReasons = "[]";

        try {
            // 2. LLM'den gelen metni bir JSON nesnesine dönüştür
            JsonNode jsonNode = objectMapper.readTree(llmResponse);
            if (jsonNode.has("riskLevel")) {
                riskLevel = jsonNode.get("riskLevel").asText();
            }
            if (jsonNode.has("riskReasons")) {
                riskReasons = jsonNode.get("riskReasons").toString();
            }
        } catch (Exception e) {
            System.err.println("LLM Cevabı Ayrıştırılamadı: " + e.getMessage());
            System.err.println("Gelen ham cevap: " + llmResponse);
        }

        // 3. Veritabanına kaydet
        Analysis savedAnalysis = analysisService.saveAnalysis(
                userOptional.get(), contentType, content, riskLevel, riskReasons
        );

        // 4. Frontend'e dönerken nesne karmaşasını önlemek için List<String> olarak çözümlüyoruz
        List<String> reasonsList = List.of();
        try {
            reasonsList = objectMapper.readValue(savedAnalysis.getRiskReasons(), List.class);
        } catch (Exception e) {
            reasonsList = List.of(savedAnalysis.getRiskReasons());
        }

        return ResponseEntity.ok(Map.of(
            "riskLevel", savedAnalysis.getRiskLevel(),
            "riskReasons", reasonsList
        ));
    }

    @GetMapping("/history")
    public ResponseEntity<?> getUserHistoryByEmail(@RequestParam String email) {
        Optional<User> userOptional = userService.findByEmail(email.trim());
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı!");
        }

        // Kullanıcının ID'sini alarak geçmişi çekiyoruz
        List<Analysis> history = analysisService.getUserAnalyses(userOptional.get().getId());
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getSystemStats() {
        return ResponseEntity.ok(analysisService.getSystemStats());
    }
}
package com.mailanalyzer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailanalyzer.entity.Analysis;
import com.mailanalyzer.entity.User;
import com.mailanalyzer.repository.AnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AnalysisService(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    public Analysis saveAnalysis(User user, String contentType, String content, String riskLevel, String riskReasons) {
        Analysis analysis = new Analysis();
        analysis.setUser(user);
        analysis.setContentType(contentType);
        analysis.setContent(content);
        analysis.setRiskLevel(riskLevel);
        analysis.setRiskReasons(riskReasons); 
        return analysisRepository.save(analysis);
    }

    public List<Analysis> getUserAnalyses(Integer userId) {
        return analysisRepository.findByUserId(userId);
    }

    // --- ADMİN PANELİ İSTATİSTİKLERİ ---
    public Map<String, Object> getSystemStats() {
        long total = analysisRepository.count();
        long highCount = analysisRepository.countByRiskLevel("HIGH");
        long mediumCount = analysisRepository.countByRiskLevel("MEDIUM");
        long lowCount = analysisRepository.countByRiskLevel("LOW");

        // En sık görülen tehditleri hesaplama (JSON içindeki metinleri sayıyoruz)
        List<Analysis> allAnalyses = analysisRepository.findAll();
        Map<String, Integer> reasonCounts = new HashMap<>();
        
        for (Analysis a : allAnalyses) {
            try {
                // String JSON'ı List<String> listesine çevir
                List<String> reasons = objectMapper.readValue(a.getRiskReasons(), new TypeReference<List<String>>() {});
                for (String reason : reasons) {
                    reasonCounts.put(reason, reasonCounts.getOrDefault(reason, 0) + 1);
                }
            } catch (Exception ignored) {
                // Hatalı veya eski formatlı verileri yoksay
            }
        }

        // En çok tekrar edenden aza doğru sıralayıp ilk 5'ini alıyoruz
        List<String> commonThreats = reasonCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(e -> e.getKey() + " (" + e.getValue() + " kez tespit edildi)")
                .collect(Collectors.toList());

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalAnalyses", total);
        stats.put("highRisk", highCount);
        stats.put("mediumRisk", mediumCount);
        stats.put("lowRisk", lowCount);
        stats.put("commonThreats", commonThreats);

        return stats;
    }
}
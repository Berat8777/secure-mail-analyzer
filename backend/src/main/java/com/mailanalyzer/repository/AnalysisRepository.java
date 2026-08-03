package com.mailanalyzer.repository;

import com.mailanalyzer.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Integer> {
    
    List<Analysis> findByUserId(Integer userId);
    
    long countByRiskLevel(String riskLevel);
}
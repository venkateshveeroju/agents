package com.ai.model;

import java.util.List;

public record CodeReviewSpec(
        boolean passed,
        List<String> compilationIssues,
        List<String> architectureIssues,
        List<String> securityIssues,
        List<String> requirementViolations,
        List<String> codeQualityIssues,
        List<String> recommendedChanges
) {
}
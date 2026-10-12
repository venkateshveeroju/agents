package com.ai.model;

public record CodeReviewRequest(
        RequirementSpec requirementSpec,
        ArchitectureSpec architectureSpec,
        CodeSpec codeSpec
) {
}
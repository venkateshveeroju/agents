package com.ai.model;

public record CodeFixRequest(
        ArchitectureSpec architectureSpec,
        CodeSpec codeSpec,
        CodeReviewSpec codeReviewSpec
) {
}

package com.ai.model;

import com.ai.model.CodeReviewSpec;
import com.ai.model.CodeSpec;

public record SdlcResult(
        boolean successful,
        int iterations,
        CodeSpec code,
        CodeReviewSpec finalReview
) {
}
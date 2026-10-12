package com.ai.controller;

import com.ai.agent.CodeReviewAgent;
import com.ai.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class CodeReviewController {

    private final CodeReviewAgent codeReviewAgent;

    public CodeReviewController(CodeReviewAgent codeReviewAgent) {
        this.codeReviewAgent = codeReviewAgent;
    }

    @PostMapping
    public CodeReviewSpec review(
            @RequestBody CodeReviewRequest request) {

        return codeReviewAgent.review(
                request.requirementSpec(),
                request.architectureSpec(),
                request.codeSpec()
        );
    }
}
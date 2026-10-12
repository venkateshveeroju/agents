package com.ai.controller;

import com.ai.agent.DevelopmentAgent;
import com.ai.model.ArchitectureSpec;
import com.ai.model.CodeFixRequest;
import com.ai.model.CodeSpec;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/development")
public class DevelopmentController {

    private final DevelopmentAgent developmentAgent;

    public DevelopmentController(
            DevelopmentAgent developmentAgent) {
        this.developmentAgent = developmentAgent;
    }

    @PostMapping("/generate")
    public CodeSpec generate(
            @RequestBody ArchitectureSpec architectureSpec) {

        return developmentAgent.generateCode(architectureSpec);
    }
    @PostMapping("/fix")
    public CodeSpec fix(
            @RequestBody CodeFixRequest request) {

        return developmentAgent.fixCode(
                request.architectureSpec(),
                request.codeSpec(),
                request.codeReviewSpec()
        );
    }
}
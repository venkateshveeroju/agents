package com.ai.controller;

import com.ai.SdlcAgent;
import com.ai.model.ArchitectureSpec;
import com.ai.model.CodeSpec;
import com.ai.model.RequirementSpec;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sdlc")
public class SdlcController {

    private final SdlcAgent sdlcAgent;

    public SdlcController(SdlcAgent sdlcAgent) {
        this.sdlcAgent = sdlcAgent;
    }

    @PostMapping("/generate")
    public CodeSpec generate(
            @RequestBody SdlcRequest request) {

        return sdlcAgent.generateAndReview(
                request.requirementSpec(),
                request.architectureSpec()
        );
    }
}
package com.ai.controller;

import com.ai.agent.ArchitectureAgent;
import com.ai.model.ArchitectureSpec;
import com.ai.model.RequirementSpec;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/architecture")
public class ArchitectureController {

    private final ArchitectureAgent architectureAgent;

    public ArchitectureController(
            ArchitectureAgent architectureAgent) {
        this.architectureAgent = architectureAgent;
    }

    @PostMapping("/design")
    public ArchitectureSpec design(
            @RequestBody RequirementSpec requirementSpec) {

        return architectureAgent.design(requirementSpec);
    }
}
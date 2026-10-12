package com.ai.controller;

import com.ai.agent.RequirementAgent;
import com.ai.model.RequirementSpec;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requirements")
public class RequirementController {

    private final RequirementAgent requirementAgent;

    public RequirementController(RequirementAgent requirementAgent) {
        this.requirementAgent = requirementAgent;
    }

    @PostMapping("/analyze")
    public RequirementSpec analyze(
            @RequestBody String requirement) {

        return requirementAgent.analyze(requirement);
    }
}
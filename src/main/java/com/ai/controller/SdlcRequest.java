package com.ai.controller;


import com.ai.model.ArchitectureSpec;
import com.ai.model.RequirementSpec;

public record SdlcRequest(
        RequirementSpec requirementSpec,
        ArchitectureSpec architectureSpec
) {
}

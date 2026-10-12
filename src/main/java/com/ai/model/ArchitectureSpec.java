package com.ai.model;

import java.util.List;

public record ArchitectureSpec(
        String architectureStyle,
        List<String> components,
        List<String> technologies,
        List<String> apis,
        List<String> databaseDesign,
        List<String> securityConsiderations,
        List<String> assumptions
) {
}
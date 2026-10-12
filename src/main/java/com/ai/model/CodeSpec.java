package com.ai.model;

import java.util.List;

public record CodeSpec(
        String moduleName,
        List<GeneratedFile> files,
        List<String> dependencies,
        List<String> implementationNotes
) {
}
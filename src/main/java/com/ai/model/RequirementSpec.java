package com.ai.model;

import java.util.List;

public record RequirementSpec(

        String title,

        List<String> functionalRequirements,

        List<String> acceptanceCriteria,

        List<String> businessRules,

        List<String> ambiguities

) {
}

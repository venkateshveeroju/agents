package com.ai;

import com.ai.agent.CodeReviewAgent;
import com.ai.agent.DevelopmentAgent;
import com.ai.config.SdlcAgentProperties;
import com.ai.model.ArchitectureSpec;
import com.ai.model.CodeReviewSpec;
import com.ai.model.CodeSpec;
import com.ai.model.RequirementSpec;
import org.springframework.stereotype.Service;

@Service
public class SdlcAgent {

    private final DevelopmentAgent developmentAgent;
    private final CodeReviewAgent codeReviewAgent;
    private final SdlcAgentProperties properties;

    public SdlcAgent(
            DevelopmentAgent developmentAgent,
            CodeReviewAgent codeReviewAgent,
            SdlcAgentProperties properties) {

        this.developmentAgent = developmentAgent;
        this.codeReviewAgent = codeReviewAgent;
        this.properties = properties;
    }

    public CodeSpec generateAndReview(
            RequirementSpec requirementSpec,
            ArchitectureSpec architectureSpec) {

        CodeSpec codeSpec =
                developmentAgent.generateCode(architectureSpec);

        for (int iteration = 1;
             iteration <= properties.maxIterations();
             iteration++) {

            CodeReviewSpec review =
                    codeReviewAgent.review(
                            requirementSpec,
                            architectureSpec,
                            codeSpec);

            if (review.passed()) {
                return codeSpec;
            }

            codeSpec =
                    developmentAgent.fixCode(
                            architectureSpec,
                            codeSpec,
                            review);
        }

        return codeSpec;
    }
}
package com.ai.agent;

import com.ai.model.RequirementSpec;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RequirementAgent {

    private final ChatClient chatClient;

    public RequirementAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public RequirementSpec analyze(String requirement) {

        return chatClient
                .prompt()
                .system("""
                        You are a senior software requirements analyst.

                        Your job is to analyze software requirements.

                        Identify:
                        - The main requirement
                        - Functional requirements
                        - Acceptance criteria
                        - Business rules
                        - Ambiguities

                        Do not invent requirements that are not present.
                        If information is missing or unclear, put it
                        in the ambiguities field.

                        Return the result as the requested structured format.
                        """)
                .user(requirement)
                .call()
                .entity(
                        RequirementSpec.class

                );
    }
}
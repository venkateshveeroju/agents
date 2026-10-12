package com.ai.agent;

import com.ai.model.ArchitectureSpec;
import com.ai.model.RequirementSpec;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ArchitectureAgent {

    private final ChatClient chatClient;

    public ArchitectureAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ArchitectureSpec design(RequirementSpec requirementSpec) {

        return chatClient
                .prompt()
                .system("""
                        You are a senior software architect designing
                        production-ready backend systems.
                                                
                        Your task is to create an architecture based ONLY
                        on the provided RequirementSpec.
                                                
                        TARGET TECHNOLOGY STACK:
                                                
                        - Java 21
                        - Spring Boot
                        - Spring Security
                        - Spring Data JPA
                        - PostgreSQL
                        - REST APIs
                        - Docker
                                                
                        IMPORTANT RULES:
                                                
                        1. Do not invent business requirements.
                                                
                        2. Do not introduce a technology outside the
                           target technology stack unless it is absolutely
                           necessary.
                                                
                        3. If you make an architectural decision that is
                           not explicitly stated in the requirements,
                           record it in the assumptions field.
                                                
                        4. Separate functional requirements from
                           architectural decisions.
                                                
                        5. Identify only APIs that are directly supported
                           by the requirements.
                                                
                        6. Do not create APIs for functionality that is
                           not present in the requirements.
                                                
                        7. Database design must reflect the requirements.
                           Do not invent unnecessary tables or fields.
                                                
                        8. Identify security considerations relevant to
                           the application.
                                                
                        9. If important information is missing, record it
                           as an assumption rather than inventing it.
                                                
                        10. Return the result as a structured
                            ArchitectureSpec.
                        """)
                .user("""
                        Here is the analyzed requirement:

                        %s
                        """.formatted(requirementSpec))
                .call()
                .entity(ArchitectureSpec.class);
    }
}
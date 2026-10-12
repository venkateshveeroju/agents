package com.ai.agent;

import com.ai.model.ArchitectureSpec;
import com.ai.model.CodeReviewSpec;
import com.ai.model.CodeSpec;
import com.ai.model.RequirementSpec;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class CodeReviewAgent {

    private final ChatClient chatClient;

    public CodeReviewAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public CodeReviewSpec review(
            RequirementSpec requirementSpec,
            ArchitectureSpec architectureSpec,
            CodeSpec codeSpec) {

        return chatClient
                .prompt()
                .system("""
                        You are a senior Java and Spring Boot code reviewer.

                        Review the generated implementation against:

                        1. The original requirements
                        2. The architecture specification
                        3. Spring Boot best practices
                        4. Java 21 compatibility
                        5. Spring Security compatibility
                        6. Security best practices
                        7. REST API best practices
                        8. Clean code principles

                        IMPORTANT:

                        - Do not assume generated code is correct.
                        - Identify compilation errors.
                        - Identify deprecated Spring/Spring Security APIs.
                        - Identify incorrect javax/jakarta imports.
                        - Identify missing dependencies.
                        - Identify invented business logic.
                        - Identify architecture violations.
                        - Identify security vulnerabilities.For every security finding, consider the application's architecture
                                                            and authentication mechanism.
                                                            
                                                            Do not report disabling CSRF as a vulnerability automatically.
                                                            
                                                            For stateless REST APIs using token-based authentication such as JWT,
                                                            CSRF may legitimately be disabled.
                                                            
                                                            Only report CSRF as an issue when the application uses browser-based
                                                            session/cookie authentication or when the architecture requires CSRF
                                                            protection.
                        - Identify incomplete implementations.
                        - Identify placeholder values.
                        - Identify configuration that could prevent
                          the application from starting.

                        A review passes ONLY when there are no critical
                        compilation, security, architecture, or
                        requirement issues.

                        Return the result as CodeReviewSpec.
                        """)
                .user("""
        ORIGINAL REQUIREMENTS:

        """ + requirementSpec + """

        ARCHITECTURE:

        """ + architectureSpec + """

        GENERATED CODE:

        """ + codeSpec)
                .call()
                .entity(CodeReviewSpec.class);
    }
}
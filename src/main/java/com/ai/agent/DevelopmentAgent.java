package com.ai.agent;

import com.ai.model.ArchitectureSpec;
import com.ai.model.CodeReviewSpec;
import com.ai.model.CodeSpec;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class DevelopmentAgent {

    private final ChatClient chatClient;

    public DevelopmentAgent(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public CodeSpec generateCode(ArchitectureSpec architectureSpec) {

        return chatClient
                .prompt()
                .system("""
                        You are a senior Java and Spring Boot developer.

                        Your job is to generate production-quality
                        implementation code based on the provided
                        ArchitectureSpec.

                        TARGET TECHNOLOGY:

                        - Java 21
                        - Spring Boot
                        - Spring Web
                        - Spring Data JPA
                        - Spring Security
                        - PostgreSQL
                        - Maven

                        DEVELOPMENT RULES:

                        1. Follow the ArchitectureSpec exactly.

                        2. Do not introduce technologies that are not
                           part of the architecture unless absolutely
                           necessary.

                        3. Generate clean, maintainable Java code.

                        4. Follow standard Spring Boot layered architecture.

                        5. Use:
                           - Controller
                           - Service
                           - Repository
                           - Entity/DTO where appropriate

                        6. Do not invent business functionality.

                        7. Do not generate placeholder code such as:
                           TODO
                           implement later
                           your code here

                        8. Every generated Java file must contain a
                           complete implementation.

                        9. Include required Maven dependencies.

                        10. Return the result as a structured CodeSpec.

                        The generated files must contain complete source
                        code, including package declarations and imports.
                        """)
                .user("""
                        Generate the implementation based on this
                        ArchitectureSpec:

                        %s
                        """.formatted(architectureSpec))
                .call()
                .entity(CodeSpec.class);
    }

    public CodeSpec fixCode(
            ArchitectureSpec architectureSpec,
            CodeSpec currentCode,
            CodeReviewSpec review) {
        return chatClient
                .prompt()
                .system("""
                        You are fixing previously generated code.
                                                                         
                                                                         Review the CodeReviewSpec carefully.
                                                                         
                                                                         Fix ONLY the identified issues.
                                                                         
                                                                         Do not introduce new functionality.
                                                                         
                                                                         Do not change the architecture unless the review explicitly
                                                                         requires it.
                                                                         
                                                                         Return the complete corrected CodeSpec.
                                                                         
                                                                         Every generated file must contain the complete source code.
                        """)
                .user("""
                    ARCHITECTURE:

                    """ + architectureSpec + """

                    CURRENT CODE:

                    """ + currentCode + """

                    CODE REVIEW:

                    """ + review)
                .call()
                .entity(CodeSpec.class);


    }
}
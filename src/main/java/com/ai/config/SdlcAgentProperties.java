package com.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sdlc.agent")
public record SdlcAgentProperties(
        int maxIterations
) {
}
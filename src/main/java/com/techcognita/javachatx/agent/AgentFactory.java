package com.techcognita.javachatx.agent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory for creating different types of agents.
 * Demonstrates how the framework can be extended with new agent types.
 */
public class AgentFactory {

    private static final AtomicInteger agentCounter = new AtomicInteger(0);

    /**
     * Create a new EchoAgent with a unique ID.
     * 
     * @return A new EchoAgent instance
     */
    public static EchoAgent createEchoAgent() {
        return new EchoAgent("echo-" + agentCounter.incrementAndGet());
    }

    /**
     * Create a new EchoAgent with a specified ID.
     * 
     * @param id The ID for the new agent
     * @return A new EchoAgent instance
     */
    public static EchoAgent createEchoAgent(String id) {
        return new EchoAgent(id);
    }

    // TODO: Add methods for creating other types of agents as they are implemented
}
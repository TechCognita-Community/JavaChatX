# 💬 JavaChatX

[![Build](https://github.com/TechCognita-Community/JavaChatX/actions/workflows/java-ci.yml/badge.svg)](https://github.com/TechCognita-Community/JavaChatX/actions)
[![GitHub issues](https://img.shields.io/github/issues/TechCognita-Community/JavaChatX)](https://github.com/TechCognita-Community/JavaChatX/issues)
[![GitHub stars](https://img.shields.io/github/stars/TechCognita-Community/JavaChatX)](https://github.com/TechCognita-Community/JavaChatX/stargazers)
[![GitHub license](https://img.shields.io/github/license/TechCognita-Community/JavaChatX)](https://github.com/TechCognita-Community/JavaChatX/blob/main/LICENSE)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=TechCognita-Community_JavaChatX&metric=alert_status)](https://sonarcloud.io/dashboard?id=TechCognita-Community_JavaChatX)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=TechCognita-Community_JavaChatX&metric=coverage)](https://sonarcloud.io/dashboard?id=TechCognita-Community_JavaChatX)

Open-source AI Agent Development Framework built in Java.  
Build custom agents, integrate with APIs (OpenAI, Gemini, Hugging Face), manage context, and automate workflows.

## Core Features

- Modular agent framework
- Chat engine
- API and plugin system
- Context memory management
- CLI/REST/Web Dashboard (upcoming)

## Quick Start

```java
AgentConfig config = AgentConfig.builder()
  .name("SupportAgent")
  .model("gpt-4")
  .build();

OpenAIAdapter openai = new OpenAIAdapter("your-api-key");
Agent agent = new Agent(config, openai);
String response = agent.chat("Hello! Can you help me?");
System.out.println(response);
```

## 🚀 Project Overview

JavaChatX is a modular framework that allows developers to build, train, and deploy intelligent conversational agents that can communicate, reason, and automate tasks through a unified chat interface.

## 🧠 Tech Stack

- **Language**: Java 17+
- **Build Tool**: Maven
- **Dependencies**: Gson (JSON processing), SLF4J/Logback (Logging), JUnit 5 (Testing)

## 🎯 Core Features

- **Modular AI Agent Architecture**: Create custom agents with specific capabilities
- **Built-in Chat Engine**: Handle message processing and session management
- **API-Ready Design**: Integrate with OpenAI, Gemini, Hugging Face, and other AI services
- **Context Memory Management**: Maintain conversation state and history
- **Extensible Plugin System**: Add new capabilities through plugins
- **CLI + Web Dashboard**: Command-line interface and web-based management (upcoming)

## 📁 Project Structure

```
JavaChatX/
├── .github/
│   ├── ISSUE_TEMPLATE/
│   │   ├── bug_report.md
│   │   └── feature_request.md
│   ├── PULL_REQUEST_TEMPLATE.md
│   └── workflows/
│       └── java-ci.yml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── techcognita/
│   │   │           └── javachatx/
│   │   │               ├── agent/         # AI agent implementations
│   │   │               ├── api/           # API connectors for external services
│   │   │               ├── chat/          # Chat engine components
│   │   │               ├── context/       # Context and memory management
│   │   │               ├── plugin/        # Plugin system
│   │   │               └── JavaChatX.java # Main framework coordinator
│   │   └── resources/
│   │       └── config.yaml
│   └── test/
│       └── java/...
├── docs/
│   ├── README.md
│   ├── ARCHITECTURE.md
│   ├── AGENT_GUIDE.md
│   ├── PLUGIN_DEVELOPMENT.md
│   └── API_INTEGRATION.md
├── examples/
│   ├── basic-agent/
│   ├── context-agent/
│   ├── multi-agent/
│   ├── custom-plugin/
├── .gitignore
├── LICENSE
├── CHANGELOG.md
├── CODE_OF_CONDUCT.md
└── pom.xml (or build.gradle)
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Building the Project

```bash
mvn clean install
```

### Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.techcognita.javachatx.JavaChatXApplication"
```

### Code Quality Checks

The project uses several code quality tools:

- **Checkstyle**: Ensures code follows standard Java conventions
- **JaCoCo**: Measures test coverage
- **SonarCloud**: Provides comprehensive code quality analysis

To run all code quality checks:

```bash
mvn verify
```

## 🤝 Contributing

- Fork repo, create feature branch, submit PR.
- Add new agents, plugins, or API adapters.
- Improve docs, examples, or tests.

## 🌍 Vision

To create a robust, community-driven Java framework that simplifies the development of conversational AI agents — empowering students and developers to explore AI automation and intelligent chat systems with ease.

## 📄 License

MIT License (c) 2025 TechCognita Community

## 📞 Contact & Community

- Discord: *coming soon*
- Twitter: @TechCognita
- Email: community@techcognita.com
- Maintainer: Atharv Shinde ([GitHub](https://github.com/atharvshinde))
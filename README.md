# 💬 JavaChatX

[![Build](https://github.com/TechCognita-Community/JavaChatX/actions/workflows/java-ci.yml/badge.svg)](https://github.com/TechCognita-Community/JavaChatX/actions)
[![GitHub issues](https://img.shields.io/github/issues/TechCognita-Community/JavaChatX)](https://github.com/TechCognita-Community/JavaChatX/issues)
[![GitHub stars](https://img.shields.io/github/stars/TechCognita-Community/JavaChatX)](https://github.com/TechCognita-Community/JavaChatX/stargazers)
[![GitHub license](https://img.shields.io/github/license/TechCognita-Community/JavaChatX)](https://github.com/TechCognita-Community/JavaChatX/blob/main/LICENSE)
[![Security](https://github.com/TechCognita-Community/JavaChatX/actions/workflows/security-scan.yml/badge.svg)](https://github.com/TechCognita-Community/JavaChatX/actions/workflows/security-scan.yml)
[![Documentation](https://github.com/TechCognita-Community/JavaChatX/actions/workflows/docs-deployment.yml/badge.svg)](https://github.com/TechCognita-Community/JavaChatX/actions/workflows/docs-deployment.yml)

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
│       ├── java-ci.yml
│       ├── security-scan.yml
│       ├── docs-deployment.yml
│       ├── code-format.yml
│       ├── performance-test.yml
│       ├── dependency-update.yml
│       └── release.yml
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

### Step 1: Verify Prerequisites

Ensure you have the following installed:

- **Java 17+**: Check your version with `java -version`
- **Maven 3.6+**: Verify with `mvn -version`

#### Quick Install (if needed):
- **Java**: Download from [adoptium.net](https://adoptium.net/) or use your package manager
- **Maven**: Install via [maven.apache.org](https://maven.apache.org/download.cgi) or `brew install maven` (macOS)

### Step 2: Clone the Repository

```bash
git clone https://github.com/TechCognita-Community/JavaChatX.git
cd JavaChatX
```

### Step 3: Build the Project

Build and install dependencies:

```bash
mvn clean install
```

This will:
- Download dependencies
- Compile the source code
- Run unit tests
- Install the project to your local Maven repository

**Expected output**: You should see `BUILD SUCCESS` at the end.

### Step 4: Run Example Applications

Try out the examples to see JavaChatX in action:

```bash
# Run the basic agent example
mvn exec:java -Dexec.mainClass="com.techcognita.javachatx.examples.basic.BasicAgentExample"

# Run the context agent example
mvn exec:java -Dexec.mainClass="com.techcognita.javachatx.examples.context.ContextAgentExample"
```

### Step 5: Integrate into Your Project

Add JavaChatX as a dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.techcognita</groupId>
    <artifactId>javachatx</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Running Code Quality Checks

JavaChatX uses several code quality tools:

- **Checkstyle**: Ensures code follows standard Java conventions
- **JaCoCo**: Measures test coverage

To run all quality checks:

```bash
mvn verify
```

To run specific checks:

```bash
# Run Checkstyle
mvn checkstyle:check

# Generate test coverage report
mvn jacoco:report
# View report at target/site/jacoco/index.html
```

### Troubleshooting

**Build fails with "Compilation error"**
- Ensure Java 17+ is being used: `mvn -version` shows Java version
- Try: `mvn clean install -U` (forces dependency updates)

**Example doesn't run**
- Check that the example class exists in the `examples/` directory
- Verify the class name matches: `mvn exec:java -Dexec.mainClass="..."`

**Need more help?**
- Check the [documentation](docs/INDEX.md) for detailed guides
- See [examples](examples/) folder for more code samples
- Open an issue on [GitHub](https://github.com/TechCognita-Community/JavaChatX/issues)

## 🔄 Continuous Integration & Deployment

JavaChatX uses GitHub Actions for continuous integration and deployment:

- **Java CI**: Builds and tests the project on multiple Java versions
- **Security Scan**: Runs CodeQL analysis and dependency review
- **Documentation Deployment**: Automatically generates and deploys Javadoc to GitHub Pages
- **Code Formatting**: Ensures consistent code style with Google Java Format
- **Performance Testing**: Runs performance benchmarks
- **Dependency Updates**: Checks for outdated dependencies weekly
- **Release**: Automatically creates GitHub releases and packages JAR files

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
- Maintainer: Atharv Shinde ([GitHub](https://github.com/AtharvShinde2004))
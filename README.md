# Rock-Paper-Scissors Game

---

## Table of Contents
1. [Overview](#overview)
2. [How to Run](#how-to-run)
3. [Purpose](#purpose)
4. [Development Stages](#development-stages)
    - [Initial Implementation](#initial-implementation)
    - [Refactoring Improvements](#refactoring-improvements)
    - [Final Design](#final-design)
5. [Future Enhancements](#future-enhancements)
6. [Conclusion](#conclusion)

---

## Overview
This project explores the development of a Rock-Paper-Scissors game, evolving from a simple implementation into a modular, extensible, and testable design. By applying Object-Oriented Programming (OOP) principles, design patterns, and software engineering best practices, the project demonstrates clean architecture and scalable solutions for real-world applications.

---

## How to Run

### Requirements
- Maven
- Java Development Kit (JDK) 23 or higher.
- Terminal or IDE for Java applications.

### Steps
1. Clone the repository.
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the game:
   ```bash
   java -jar target/rock-paper-scissors-1.0-SNAPSHOT.jar
   ```

Follow the console prompts to play the game and view outcomes.

---

## Purpose

The purpose of this project was to practice coding in an Object-Oriented Programming (OOP) language and to explore how a basic, working implementation could be transformed into a more robust, extensible, and maintainable solution. By iteratively refining the design, this project allowed for hands-on experience with:

- **OOP Concepts:** Applying principles like encapsulation, inheritance, and polymorphism to improve structure and functionality.
- **Incremental Improvement:** Starting with a simple design and progressively enhancing it.
- **Design Patterns:** Experimenting with patterns like Strategy and State to enable scalability and adaptability.
- **Code Quality:** Improving modularity, readability, and maintainability through refactoring.
- **Testability:** Decoupling components to enable isolated and efficient testing.

This journey provided valuable insights into the development process, emphasizing the importance of iteration and thoughtful design.

---

## Development Stages

### Initial Implementation

**repo:** [refactor/000](https://github.com/lefpap/rock-paper-scissors/tree/refactor/000)

The first version of the game was a straightforward console application, which included:

- Basic input handling for players.
- Randomized computer choices.
- Rules to calculate round results.
- Scorekeeping and game termination once a winning threshold was reached.

**Limitations:**

- **Overcoupled Logic:** All logic was confined to a single class, hindering readability and maintenance.
- **Limited Testability:** The absence of abstraction prevented testing individual components.
- **Scalability Issues:** Adding new features required significant rewrites.

### Refactoring Improvements

**repo:** [refactor/001](https://github.com/lefpap/rock-paper-scissors/tree/refactor/001)

Key changes in the first refactor included:

1. **Configuration Separation:** Constants like player names and score thresholds were extracted to a `GameConfig` class.
2. **Encapsulation:** Grouped related logic into methods, improving clarity and reducing duplication.
3. **Enhanced Error Handling:** Introduced custom exceptions for better user feedback and validation.

These changes improved modularity but left scalability and testability as areas for further refinement.

### Final Design

**repo:** [refactor/002](https://github.com/lefpap/rock-paper-scissors/tree/refactor/002)

The final implementation incorporates significant architectural advancements, including:

#### 1. **Abstract Game Framework**
A generic `Game` class was introduced to standardize the game loop and enable reuse across different games. Key components include:

- **`init()`**: Initializes game-specific conditions.
- **`loop()`**: Encapsulates logic for each game iteration.
- **`isGameOver()`**: Defines termination conditions.

Hooks like `onBeforeLoop()` and `onEndOfEachLoop()` allow customization, making the framework adaptable to various game scenarios.

#### 2. **State Management with GameState**
The `GameState` class manages:

- Round history and outcomes.
- Player scores using a `Scoreboard`.
- Winner determination based on configurable thresholds.

Supporting classes like `Round` and `RoundResult` further isolate responsibilities for maintainable code.

#### 3. **Strategy Pattern for Player Choices**
The `Player` class leverages a `PlayerChoiceStrategy` interface for flexible decision-making, with implementations such as:

- **`InputChoiceStrategy`**: Handles console-based human input.
- **`RandomChoiceStrategy`**: Generates randomized computer choices.
- **`ForceChoiceStrategy`**: Provides fixed choices for testing.

This design simplifies adding new strategies, such as AI decision-making or network-based multiplayer input.

#### 4. **Improved Testability**
Refactoring enabled isolated unit tests for:

- Validating `GameState` logic.
- Simulating player behavior using mock strategies.
- Testing round results under various scenarios.

---

## Future Enhancements

Potential expansions for the game include:

- **AI Decision-Making:** Add rule-based or machine learning strategies.
- **Multiplayer Functionality:** Enable real-time networked gameplay.
- **Graphical User Interface (GUI):** Create a more engaging visual experience.
- **Extended Ruleset:** Add variations like "Rock-Paper-Scissors-Lizard-Spock."
- **Data Persistence:** Save game history and player statistics.
- **Customizable Rules:** Allow players to define their own rules and conditions.

---

## Conclusion
This project demonstrates how thoughtful application of OOP principles and design patterns can turn a simple concept into a robust, maintainable, and scalable application. Through iterative development and refactoring, it highlights the importance of modular design, testability, and extensibility. The final implementation serves as both a learning tool and a foundation for future projects.
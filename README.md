# Logic Gates Simulator

A JavaFX-based interactive application for simulating digital logic gates and building complex circuits.

![Java](https://img.shields.io/badge/Java-17-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-17.0.2-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.6-red)

## 📋 Features

- **7 Logic Gates**: AND, OR, NOT, NAND, NOR, XOR, and XNOR
- **Interactive Components**: Toggle switches for inputs and probes for outputs
- **Visual Wiring**: Connect components with color-coded wires (RED = true, YELLOW = false)
- **Real-time Simulation**: Watch circuit behavior update instantly
- **Drag & Drop Interface**: Easily position components on the canvas
- **Undo/Redo**: Revert or reapply changes to your circuit
- **Clear All**: Reset the entire workspace

## 🚀 Quick Start

### Prerequisites

- **Java 17** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.9.6** or higher ([Download](https://maven.apache.org/download.cgi))

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone https://github.com/tooharshh/LogicNGates.git
   cd LogicNGates/LogicGateSimulator
   ```

2. **Compile the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn javafx:run
   ```

## 📖 Usage Guide

### Building a Circuit

1. **Add Components**
   - Expand the tree menu on the left
   - Click on a gate type (AND, OR, NOT, etc.)
   - The gate appears on the canvas

2. **Add Inputs/Outputs**
   - Click "Add Logic Toggle" for input switches
   - Click "Add Logic Probe" to view outputs
   - Double-click toggles to change their state (ON/OFF)

3. **Connect Components**
   - Click "Add Wire"
   - Click on the first component's connection point
   - Click on the second component's connection point
   - A wire is created between them

4. **Use Undo/Redo**
   - Go to **Edit → Undo** to remove the last action
   - Go to **Edit → Redo** to restore what was undone

5. **Clear Workspace**
   - Go to **File → Clear All** to reset the entire circuit

### Wire Color Coding

- 🔴 **RED**: Signal is HIGH (true/1)
- 🟡 **YELLOW**: Signal is LOW (false/0)
- ⚫ **BLACK**: No signal/disconnected

## 🏗️ Project Structure

```
LogicGateSimulator/
├── src/
│   └── main/
│       ├── java/
│       │   └── project/logicgatesimulator/
│       │       ├── Main.java              # Application entry point
│       │       ├── Controller.java        # UI controller
│       │       ├── Component.java         # Abstract component class
│       │       ├── ANDgate.java          # AND gate logic
│       │       ├── ORgate.java           # OR gate logic
│       │       ├── NOTgate.java          # NOT gate logic
│       │       ├── NANDgate.java         # NAND gate logic
│       │       ├── NORgate.java          # NOR gate logic
│       │       ├── XORgate.java          # XOR gate logic
│       │       ├── XNORgate.java         # XNOR gate logic
│       │       ├── Toggle.java           # Input switch
│       │       ├── Probe.java            # Output indicator
│       │       ├── Wire.java             # Wire connections
│       │       ├── CircuitHistory.java   # Undo/redo manager
│       │       └── CircuitState.java     # State snapshot
│       └── resources/
│           └── project/logicgatesimulator/
│               ├── Start.fxml            # Start screen UI
│               ├── Simulator1.fxml       # Main simulator UI
│               └── Images/               # Gate images & icons
└── pom.xml                               # Maven configuration
```

## 🛠️ Technology Stack

- **Language**: Java 17
- **UI Framework**: JavaFX 17.0.2
- **Build Tool**: Maven 3.9.6
- **Architecture**: MVC (Model-View-Controller)

## 🎯 Key Components

### Logic Gates

Each gate extends the `Component` class and implements its specific logic:

- **AND**: Output is true only if all inputs are true
- **OR**: Output is true if any input is true
- **NOT**: Inverts the input
- **NAND**: Inverse of AND
- **NOR**: Inverse of OR
- **XOR**: Exclusive OR (true if inputs differ)
- **XNOR**: Inverse of XOR

### Circuit Management

- **CircuitHistory**: Manages undo/redo functionality using stack-based state management
- **CircuitState**: Captures and restores complete circuit snapshots
- **Wire**: Handles connections and signal propagation between components

## 🔧 Development

### Building from Source

```bash
# Clean previous builds
mvn clean

# Compile the project
mvn compile

# Run tests (if any)
mvn test

# Package as JAR
mvn package
```

### Maven Configuration

The project uses:
- JavaFX Maven Plugin for running JavaFX applications
- Java 17 as source/target compatibility
- Maven Compiler Plugin 3.11.0

## 📝 Notes

- **IDE Errors**: Red squiggly lines in VS Code/IntelliJ are cosmetic - Maven resolves JavaFX dependencies during compilation
- **FXML Version Warning**: Harmless warning about FXML version mismatch (created with JavaFX 19, running on 17)
- **Image Assets**: All component images are included in the repository

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📜 License

This project is based on the original work from [Afra107/Logic-Gates-Simulator](https://github.com/Afra107/Logic-Gates-Simulator).

## 👤 Author

**tooharshh**
- GitHub: [@tooharshh](https://github.com/tooharshh)

## 🙏 Acknowledgments

- Original project structure inspired by [Afra107's Logic Gates Simulator](https://github.com/Afra107/Logic-Gates-Simulator)
- JavaFX community for excellent documentation
- Maven for dependency management

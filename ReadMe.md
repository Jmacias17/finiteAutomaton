# 🤖 Finite Automaton Tester — CS 3350 Project

This is a Java-based program created for my **CS 3350 (Theory of Automata)** course at UTEP. The project allows users to define and simulate **finite automata** by entering custom states, symbols, transition rules, final states, and test strings. It's entirely interactive via console input, and outputs results both on-screen and to a `.txt` file.

---

## 📌 Project Overview

The program is built around two main files:

### 1. `finiteAutomaton.java`
This file defines a `finiteAutomaton` class which holds:
- Automaton configuration (states, symbols, transition table, final states)
- The test word to be checked
- The core method `emulateFiniteAutomaton()` to simulate the automaton and print step-by-step output
- A write-to-file mechanism to log results into `testResults.txt`

### 2. `runAutomaton.java`
This is the **main executable file** that:
- Prompts the user to create one or more finite automatons
- Collects and validates user input for:
  - State names
  - Symbol set
  - Transition table
  - Final state indicators
  - Test word
- Stores each created automaton in an ArrayList
- Asks the user which automatons to emulate
- Runs simulations and saves results

---

## 🎯 Features

- Interactive console input for flexible automaton creation
- Symbol and state duplication checks for error prevention
- Dynamic creation of transition tables
- Detailed step-by-step emulation output
- File output of all test results to `testResults.txt`

---

## 🧠 How It Works

1. User chooses how many automatons to create.
2. For each automaton, user enters:
   - Number and names of **states**
   - Number and names of **symbols**
   - **Final states** (true/false prompt per state)
   - The full **transition table**
   - A **word** to test against the automaton
3. After setup, user selects which automatons to run.
4. The emulator simulates the finite automaton from state 0 and logs transitions based on the input word.
5. If the word ends in a final state → ✅ *Accepted*. Else → ❌ *Rejected*.
6. All results are printed to the terminal and saved to `testResults.txt`.

---

## 💻 Example Output Snippet


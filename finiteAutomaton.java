import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
Author: Jesus Macias
Date: 9/15/2021
Course: CS 3350
Instructor: Vladik Kreinovich
Programming Homework 5

Description: This program is the class file of a finiteAutomaton, this class holds the method to emulate the automaton based on the set word.

Honesty Statement: I confirm that the work of this assignment is completely my own.
By turning in this assignment, I declare that I did not receive unauthorized assistance.
*
*/

public class finiteAutomaton {
    //Variables
    private int number;
    private String[] states;
    private String[] symbols;
    private int[][] stateChart;
    private boolean[] finalStates;
    private String word;
    private File testResults;

    //Constructors
    public finiteAutomaton() {
    }

    public finiteAutomaton(int number, String[] states, String[] symbols, int[][] stateChart, boolean[] finalStates, String word, File testResults) {
        this.number = number;
        this.states = states;
        this.symbols = symbols;
        this.stateChart = stateChart;
        this.finalStates = finalStates;
        this.word = word;
        this.testResults = testResults;
    }

    //Methods
    //The Finite Automaton Emulation Method
    public void emulateFiniteAutomaton(int N, int M, int[][] state, boolean[] finalStates){
        String line;
        int index = 0;
        String[] wordTest = this.word.split("");
        System.out.println(Arrays.toString(wordTest));
        if (this.states == null | this.symbols == null){
            System.out.println("Please make sure this Finite Automaton has declared String Arrays for States and Symbols and a String Word.");
        }else{
            try {
                //Writers
                FileWriter fw = new FileWriter(this.testResults, true);
                BufferedWriter bw = new BufferedWriter(fw);

                //Print Info of the Automaton
                line = "\nEmulating Finite Automaton #" + this.number + "\nThere is " + N + " States and " + M + " Symbols.\nStates: " + Arrays.toString(this.states) + "\nSymbols: " + Arrays.toString(this.symbols) + "\nState Chart: " + Arrays.deepToString(this.stateChart) + "\nFinal States: " + Arrays.toString(this.finalStates) + "\nThe word being tested: " + this.word + "\n";
                System.out.println(line);
                bw.write(line);

                //CurrState starts at 0.
                int currState = 0;

                //Goes through each value of the word and changes currState depending on the symbol.
                for (int i = 0; i < wordTest.length; i++) {
                    for (int j = 0; j < this.symbols.length; j++) {
                        //If the current index of the word is within String array symbols get the index of the next state else if current index isn't a valid symbol index = -1.
                        if (this.symbols[j].equals(wordTest[i])){
                            index = j;
                            break;
                        }else{
                            index = -1;
                        }
                    }
                    //If the symbol doesn't exist, break out loop and print error.
                    if (index == -1){
                        line = "ERROR: No valid symbols were found\n";
                        System.out.println(line);
                        bw.write(line);
                        break;
                    }
                    line = "Current State: " + states[currState] + " saw Symbol " + symbols[index] + " and is heading to State: " + states[state[currState][index]] + "\n";
                    System.out.println(line);
                    bw.write(line);
                    currState = state[currState][index];
                }
                //The Automaton emulated to the result of Accepted or Rejected.
                if (finalStates[currState] && index != -1){
                     line = "The word: " + this.word + " for this Automaton was Accepted\n";
                }else{
                    line = "The word: " + this.word + " for this Automaton was Rejected\n";
                }
                System.out.println(line);
                bw.write(line);
                bw.close();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //Getters and Setters
    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }

    public String[] getSymbols() {
        return symbols;
    }

    public void setSymbols(String[] symbols) {
        this.symbols = symbols;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public File getTestResults() {
        return testResults;
    }

    public void setTestResults(File testResults) {
        this.testResults = testResults;
    }

    public int[][] getStateChart() {
        return stateChart;
    }

    public void setStateChart(int[][] stateChart) {
        this.stateChart = stateChart;
    }

    public boolean[] getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(boolean[] finalStates) {
        this.finalStates = finalStates;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

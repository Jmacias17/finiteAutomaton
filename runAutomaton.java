import java.io.File;
import java.util.*;

/*
Author: Jesus Macias
Date: 9/15/2021
Course: CS 3350
Instructor: Vladik Kreinovich
Programming Homework 5

Description: This program asks the user to create a number of finite automatons and populate them with the following attributes:
    * String Array of States.
    * String Array of Symbols.
    * String of a word.
Within population for each automaton, the variables are requested as:
    * int of States.
    * int of Symbols.
    * int 2D Array of stateChart.
    * boolean Array of final states.
Once each automaton is constructed, with the request of user input the program will emulate each selected automaton and write tests results to .txt file.
This Emulation is done using a method emulateAutomaton within class finiteAutomaton which the method input requires the variables above.



Honesty Statement: I confirm that the work of this assignment is completely my own.
By turning in this assignment, I declare that I did not receive unauthorized assistance.
*
*/

public class runAutomaton {
    //Method to check if selection is valid numbers.
    public static boolean selectionCheck(String selection, int max) {
        if (selection == null){
            return false;
        }

        int selectedNum = Integer.parseInt(selection);
        if (selectedNum > max){
            return false;
        }
        return true;
    }

    //Method to check if any index in the array of strings is duplicate of another index in the same array.
    public static boolean dupeStringCheck(String[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j) {
                    if (array[i].equals(array[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Method to check if String is a Number.
    public static boolean isNumeric(String stringIn) {
        if (stringIn == null) {
            return false;
        }
        try {
            int test = Integer.parseInt(stringIn);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //Variables
        int check;
        int stateIn;
        int numOfStates;
        int numOfSymbols;
        int numAutomatons = 0;
        String line;
        String word;

        //Arrays
        ArrayList<finiteAutomaton> automatonList = new ArrayList<>();
        String[] states; //{s, c, e}
        String[] symbols; //{a, A, +, -}
        int[][] stateChart; //   0, 1, 2, 3
        //0 [3, 3, 2, 3]
        //1 [3, 2, 2, 2]
        //2 [3, 3, 3, 3]
        String[] selected;
        int selectedNum;

        //Scanner
        Scanner s = new Scanner((System.in));

        //Try to see if testResults.txt exist if not then create file named testResults.txt
        File testResults = new File("testResults.txt");
        try {
            //Option 1: File Exists.
            if (testResults.exists()) {
                System.out.println(testResults.getName() + " does exist!\n");

                //Option 2: Create File.
            } else {
                System.out.println("Creating Test Results File\n");
                testResults.createNewFile();
            }

            //Catch Exception e if error.
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Main Menu
        System.out.println("Welcome to the General Finite Automaton Tester");
        System.out.println("Created by Jesus Macias\n");

        //User Input of number of Automatons to be created.
        while (numAutomatons == 0) {
            System.out.println("How many Automatons are you going to make?");
            line = s.nextLine();

            //Checks if input is a numeric value.
            if (isNumeric(line)) {
                numAutomatons = Integer.parseInt(line);
            }
        }

        //Creates a finiteAutomaton based on the numAutomatons.
        for (int i = 0; i < numAutomatons; i++) {
            //For the current finiteAutomaton set values to default.
            numOfStates = 0;
            numOfSymbols = 0;
            check = 1;
            states = new String[0];
            symbols = new String[0];

            /* NumofStates Process. */
            while (numOfStates == 0) {
                System.out.println("\nHow many States for Automaton " + (i + 1) + "?\nMust be greater than 0");
                line = s.nextLine();

                //Checks to see if the input is a numerical value.
                if (isNumeric(line)) {
                    numOfStates = Integer.parseInt(line);
                }
            }

            while (states.length != numOfStates || check == 1) {
                /* (STP) States Process. */
                System.out.println("\nEnter the " + numOfStates + " States for Automaton " + (i + 1) + "\nSeparate by ',' but don't include the [] \nExample: [s, c, e]");
                line = s.nextLine();

                //(STP): Split line by ', ' to a String array called states.
                states = line.split(", ");

                //(STP): Checks to see if there is a correct number of States.
                if (states.length != numOfStates || states[numOfStates - 1].length() > 1) {
                    System.out.println("Incorrect Number of States, Try Again!");
                } else {
                    //(STP): Checks to see if there is any duplicates.
                    if (!dupeStringCheck(states)) {
                        System.out.println(Arrays.toString(states) + " are the states in Automaton " + (i + 1) + " with a length of " + states.length + "\n");
                        check = 0;
                    } else {
                        System.out.println("There was duplicate states, Try Again!");
                    }
                }
            }

            /*
            (FSP) Final State Boolean Process.
            (FSP): Goes through all states to see which are final.
            */
            boolean[] finalStates = new boolean[numOfStates];
            for (int j = 0; j < finalStates.length; j++) {
                System.out.println("Is State '" + states[j] + "' (Index: " + j + ") a Final State?");
                System.out.println("Enter yes or no");

                //Sets line to default "".
                line = "";

                //Checks if yes or no is entered while line equals default "".
                while (line.equals("")) {
                    line = s.nextLine();
                    if (line.equalsIgnoreCase("YES")) {
                        finalStates[j] = true;
                    } else if (line.equalsIgnoreCase("NO")) {
                        finalStates[j] = false;
                    } else {
                        System.out.println("Please enter yes or no");
                        line = "";
                    }
                }
            }

            /* NumOfSymbols Process. */
            while (numOfSymbols == 0) {
                System.out.println("\nHow many Symbols for Automaton " + (i + 1) + "?\nMust be greater than 0");
                line = s.nextLine();

                //Checks to see if the input is a numerical value.
                if (isNumeric(line)) {
                    numOfSymbols = Integer.parseInt(line);
                }
            }

            /* (SP) Symbol Process. */
            check = 1;
            while (symbols.length != numOfSymbols || check == 1) {
                System.out.println("\nEnter the " + numOfSymbols + " Symbols for Automaton " + (i + 1) + "\nSeparate by ',' but don't include the [] \nExample: [a, -, +]");
                line = s.nextLine();

                //(SP): Splits the Symbols to a String Array and prints result.
                symbols = line.split(", ");

                //(SP): Checks to see if there is a correct number of Symbols.
                if (symbols.length != numOfSymbols || symbols[numOfSymbols - 1].length() > 1) {
                    System.out.println("Incorrect Number of Symbols, Try Again!");
                } else {
                    //(SP): Checks to see if there is any duplicates.
                    if (!dupeStringCheck(symbols)) {
                        System.out.println(Arrays.toString(symbols) + " are the Symbols in Automaton " + (i + 1) + "\n");
                        check = 0;
                    } else {
                        System.out.println("There was duplicate symbols, Try Again!");
                    }
                }
            }

            /* (SCP) State Chart Process. */
            stateChart = new int[numOfStates][numOfSymbols];

            /*
            (SCP): Goes through each n and m of the 2D int array stateChart and asks the user to initialize,
            (SCP): Which state does state n go to if it sees symbol m.
            */
            for (int n = 0; n < numOfStates; n++) {
                for (int m = 0; m < numOfSymbols; m++) {
                    //While line equals "" then stay within loop (Check to make sure valid assignments.
                    line = "";
                    while (line.equals("")) {
                        //Asks user what state leads to what state depending on symbol
                        check = 0;
                        System.out.println("\nFinite Automaton " + (i + 1));
                        System.out.println("Current State: " + states[n]);
                        System.out.println("Current Symbol: " + symbols[m]);
                        System.out.print("\nWhich state do we go to if we are in State '" + states[n] + "' (Index: " + n + ") ");
                        System.out.println("and we see Symbol '" + symbols[m] + "' (Index: " + m + ")");
                        System.out.println("You may enter the state or the index of the state");
                        System.out.println("If 'Possible States' deals with #'s use the index of the States\n");
                        System.out.println("Possible States:");
                        System.out.println(Arrays.toString(states));

                        line = s.nextLine();

                        //Checks if the String line is an int.
                        if (isNumeric(line)) {
                            stateIn = Integer.parseInt(line);
                            /*
                            If the line is a valid index from the String array states, then assign the validIndex to the index of the statechart of the current n and m.
                             Ex: statechart[n][m] = validIndex.
                            */
                            if (stateIn < states.length && stateIn > -1) {
                                stateChart[n][m] = stateIn;
                                //Print to declare the assigned state and break out of loop.
                                System.out.println("State '" + states[n] + "' (Index: " + n + ") goes to State '" + states[stateIn] + "' (Index: " + stateIn + ") when we see the Symbol '" + symbols[m] + "' (Index: " + m + ")\n");
                                break;
                            }

                            //If the int input wasn't a valid index then error message and restart loop.
                            System.out.println(stateIn + " is not a valid state, Try Again!");
                            line = "";
                        } else {
                            //If the input was a String, check if it's a valid state by comparing to each value in the String array states.
                            for (int x = 0; x < numOfStates; x++) {
                                //If the line does equal a valid state then assign the validIndex to the index of the statechart of the current n and m.
                                if (line.equals(states[x])) {
                                    stateChart[n][m] = x;
                                    line = "State '" + states[n] + "' (Index: " + n + ") goes to State '" + states[x] + "' (Index: " + x + ") when we see the Symbol '" + symbols[m] + "' (Index: " + m + ")\n";
                                    System.out.println(line);
                                    check = 1;
                                    break;
                                }
                            }
                            //Checks if valid state was entered if not restart loop.
                            if (check == 0) {
                                System.out.println("Please enter a valid state");
                                line = "";
                            }
                        }
                    }
                }
            }

            /* Word Process. */
            System.out.println("\nEnter a word to test on the Automaton");
            word = s.nextLine();

            //Add Automaton to the Array List automatonList.
            automatonList.add(new finiteAutomaton(i, states, symbols, stateChart, finalStates, word, testResults));
        }

        //Ask user which Automatons would they like to emulate
        System.out.println("\n" + automatonList.size() + " Finite Automatons Generated");
        for (int list = 0; list < automatonList.size(); list++){
            System.out.println("\nAutomaton: " + list);
            System.out.println("States: " + Arrays.toString(automatonList.get(list).getStates()));
            System.out.println("Symbols: " + Arrays.toString(automatonList.get(list).getSymbols()));
            System.out.println("State Chart: " + Arrays.deepToString(automatonList.get(list).getStateChart()));
            System.out.println("Word: " + automatonList.get(list).getWord());
        }
        //Creates a Selected String array of max Automatons.
        selected = new String[automatonList.size()];

        //Checks if the input selection is valid.
        check = 1;
        while (check == 1) {
            System.out.println("\nPlease enter the Automatons you'd wish to emulate");
            System.out.println("Be sure to split the input selection with ', ' but don't include the [].");
            System.out.println("Example: [1, 2, 3, 4]");
            System.out.println("Results will be printed onto text file and prompt");
            line = s.nextLine();

            //Splits the selection into a String array named selected
             selected = line.split(", ");
             check = 0;
             System.out.println(Arrays.toString(selected));
             if (!dupeStringCheck(selected)){
                 for (int i = 0; i < selected.length; i++) {
                     if ((!isNumeric(selected[i])) || (!selectionCheck(selected[i], automatonList.size() - 1))){
                         check = 1;
                     }
                 }
             }else{
                 check = 1;
             }
        }

        //Run Emulate Automaton method for the selected choices.
        for (int run = 0; run < selected.length; run++){
            selectedNum = Integer.parseInt(selected[run]);
            automatonList.get(selectedNum).emulateFiniteAutomaton(automatonList.get(selectedNum).getStates().length,
                    automatonList.get(selectedNum).getSymbols().length,
                    automatonList.get(selectedNum).getStateChart(),
                    automatonList.get(selectedNum).getFinalStates());

        }
    }
}

package project.tm;

public class TuringMachine {

    String name;
    String[] states;
    char[] inputAlphabet;
    char[] stackAlphabet;
    char[] moves;
    String initialState;

    Tape[] tapes;
    Transition[] deltaFunctions;

    public TuringMachine(String title, String[] q, char[] s, char[] t, int tapes, char[] moves, String q0, Transition[] d) {

        this.name = title;

        this.states = q; // check

        this.inputAlphabet = s; // check

        this.stackAlphabet = t; // check

        this.moves = moves; // check

        this.deltaFunctions = d; // check



    } // TuringMachine

} // TuringMachine
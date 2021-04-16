package project.tm;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TuringMachine {

    String name;
    int numTapes;

    Set<String> states;
    Set<Character> inputAlphabet;
    Set<Character> stackAlphabet;
    Set<Character> moves;
    Set<Transition> deltaFunctions;
    String initialState;
    String currentState;

    Tape[] tapes;

    public TuringMachine(String title, String[] q, Character[] s, Character[] t, int tapes, Character[] moves, String q0, Transition[] d) {

        try {

            this.name = title;
            this.numTapes = tapes;

            this.states = new TreeSet<>();
            this.states.addAll(Arrays.<String>stream(q).collect(Collectors.toSet()));

            this.inputAlphabet = new TreeSet<>();
            this.inputAlphabet.addAll(Arrays.<Character>stream(s).collect(Collectors.toSet()));

            this.stackAlphabet = new TreeSet<>();
            this.stackAlphabet.addAll(Arrays.<Character>stream(t).collect(Collectors.toSet()));

            this.moves = new TreeSet<>();
            this.moves.addAll(Arrays.<Character>stream(moves).collect(Collectors.toSet()));

            this.deltaFunctions = Arrays.<Transition>stream(d).collect(Collectors.toSet());

            this.initialState = q0;
            this.currentState = q0;

            this.tapes = new Tape[numTapes];
            for (int i = 1; i < this.tapes.length; i++)
                this.tapes[i] = new Tape("Tape " + i);

        
        } catch (Exception e) {
            throw new TMException();

        } // try

    } // TuringMachine

    public boolean accepts(String input) {
        System.out.println(input + " is accepted.");
        return true;

    } // process

    public void simulate(String input) {
        throw new UnsupportedOperationException();

    } // process

    @Override
    public String toString() {

        String tm = "TM::" + name + "\n";
        tm += "Q = " + states + "\n";
        tm += "Σ = " + inputAlphabet + "\n";
        tm += "T = " + stackAlphabet + "\n";
        tm += "tapes = " + numTapes + "\n";
        tm += "moves = " + moves + "\n";
        tm += "q0 = " + initialState + "\n";

        for (Transition d : deltaFunctions)
            tm += "\n" + d;

        return tm;

    } // toString

} // TuringMachine
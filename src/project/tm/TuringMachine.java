package project.tm;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Set;

public class TuringMachine {

    String name;
    Set<String> states;
    Set<Character> inputAlphabet;
    Set<Character> stackAlphabet;
    Set<Character> moves;
    String initialState;

    Tape[] tapes;
    Set<Transition> deltaFunctions;

    public TuringMachine(String title, String[] q, Character[] s, Character[] t, int tapes, Character[] moves, String q0, Transition[] d) {

        try {

            this.name = title;

            this.states = Set.copyOf(Arrays.<String>stream(q).collect(Collectors.toSet()));
            this.inputAlphabet = Set.copyOf(Arrays.<Character>stream(s).collect(Collectors.toSet()));
            this.stackAlphabet = Set.copyOf(Arrays.<Character>stream(t).collect(Collectors.toSet()));
            this.moves = Set.copyOf(Arrays.<Character>stream(moves).collect(Collectors.toSet()));
            this.deltaFunctions = Set.copyOf(Arrays.<Transition>stream(d).collect(Collectors.toSet()));
        
        } catch (Exception e) {
            throw new RuntimeException();

        } // try

    } // TuringMachine

} // TuringMachine
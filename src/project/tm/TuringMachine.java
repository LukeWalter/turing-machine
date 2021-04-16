package project.tm;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
            if (q == null || q.length == 0) throw new Exception();
            for (String state : q) {
                if (this.states.add(state) == false) {
                   throw new Exception();
                
                } // if
            
            } // for
            
            this.inputAlphabet = new TreeSet<>();
            if (s == null || s.length == 0) throw new Exception();
            for (Character c : s) {
                if (this.inputAlphabet.add(c) == false) {
                   throw new Exception();
                
                } // if
            
            } // for

            this.stackAlphabet = new TreeSet<>();
            if (t == null || t.length == 0) throw new Exception();
            for (Character c : t) {
                if (this.stackAlphabet.add(c) == false) {
                   throw new Exception();
                
                } // if
            
            } // for
            if (!(this.stackAlphabet.containsAll(this.inputAlphabet) &&
                  this.stackAlphabet.contains('_')))
                throw new Exception();


            this.moves = new TreeSet<>();
            if (moves == null || moves.length == 0) throw new Exception();
            for (Character c : moves) {
                if (this.moves.add(c) == false) {
                   throw new Exception();
                
                } // if
            
            } // for


            this.deltaFunctions = new HashSet<>();
            if (d == null || d.length == 0) throw new Exception();
            for (Transition rf : d) {
                if (this.deltaFunctions.add(rf) == false) {
                   throw new Exception();
                
                } else {
                    if (rf.getTapes() != numTapes ||
                        !this.states.contains(rf.getInitialState()) || 
                        !this.states.contains(rf.getTargetState()))
                        throw new Exception();
                    
                    Character[] i = rf.getInputs();
                    Character[] o = rf.getOutputs();
                    Character[] m = rf.getDirs();

                    for (int n = 0; n < rf.getTapes(); n++) {
                        if (!this.stackAlphabet.contains(i[n]) ||
                            !this.stackAlphabet.contains(o[n]) || 
                            !this.moves.contains(m[n]))
                            throw new Exception();

                    } // for
                    
                } // if
            
            } // for

            if (!this.states.contains(q0)) throw new Exception();
            this.initialState = q0;
            this.currentState = q0;

            this.tapes = new Tape[numTapes];
        
        } catch (Exception e) {
            throw new TMException();

        } // try

    } // TuringMachine

    public boolean accepts(String input) {
        throw new UnsupportedOperationException();

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
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
            if (!this.states.contains("qa"))
                throw new Exception();
            
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
        
        for (int i = 0; i < input.length(); i++) {
            if (!inputAlphabet.contains(input.charAt(i)))
                throw new InvalidInputException();

        } // for
        
        tapes[0] = new Tape("Tape 1", input);
        for (int i = 1; i < tapes.length; i++) 
            tapes[i] = new Tape("Tape " + (i + 1));

        while (true) {
            
            if (currentState.equals("qa")) {
                currentState = initialState;
                return true;

            } else if (currentState.equals("qr")) {
                currentState = initialState;
                return false;

            } else {
                
                Transition[] d = deltaFunctions.stream()
                    .filter((f) -> { return f.getInitialState().equals(currentState); })
                    .filter((f) -> { 
                        Character[] in = f.getInputs(); 
                        for (int i = 0; i < in.length; i++) {
                            if (tapes[i].getCurrent() != in[i].charValue()) return false;

                        } // for
                        return true;
                    })
                    .toArray(Transition[]::new);

                if (d.length == 0) {
                    currentState = initialState;
                    return false;

                } else if (d.length > 1) {
                    currentState = initialState;
                    throw new NondeterminismException();
                
                } else {
                    
                    Character[] i = d[0].getInputs();
                    Character[] o = d[0].getOutputs();
                    Character[] m = d[0].getDirs();

                    for (int n = 0; n < tapes.length; n++) 
                        tapes[n].move(i[n], o[n], m[n]);

                    currentState = d[0].getTargetState();
                
                } // if

            } // if

        } // while

    } // process

    public void simulate(String input) {
        
        for (int i = 0; i < input.length(); i++) {
            if (!inputAlphabet.contains(input.charAt(i)))
                throw new InvalidInputException();

        } // for
        
        tapes[0] = new Tape("Tape 1", input);
        for (int i = 1; i < tapes.length; i++) 
            tapes[i] = new Tape("Tape " + (i + 1));

        System.out.println("Simulating TM \"" + name + "\" on input string \"" + input + "\"...\n");

        System.out.println("> Initial state: " + currentState);
        for (Tape t : tapes)
            System.out.println(t);

        while (true) {
            
            if (currentState.equals("qa")) {
                System.out.println("**Input string \"" + input + "\" is accepted by \"" + name + "\".**");
                currentState = initialState;
                return;

            } else if (currentState.equals("qr")) {
                System.out.println("**Input string \"" + input + "\" is rejected by \"" + name + "\".**");
                currentState = initialState;
                return;

            } else {
                
                Transition[] d = deltaFunctions.stream()
                    .filter((f) -> { return f.getInitialState().equals(currentState); })
                    .filter((f) -> { 
                        Character[] in = f.getInputs(); 
                        for (int i = 0; i < in.length; i++) {
                            if (tapes[i].getCurrent() != in[i].charValue()) return false;

                        } // for
                        return true;
                    })
                    .toArray(Transition[]::new);

                if (d.length == 0) {
                    System.out.println("**Input string \"" + input + "\" is rejected by \"" + name + ".**\"");
                    currentState = initialState;
                    return;

                } else if (d.length > 1) {
                    currentState = initialState;
                    throw new NondeterminismException();
                
                } else {
                    
                    System.out.println("Applying >> " + d[0] + "\n");
                    Character[] i = d[0].getInputs();
                    Character[] o = d[0].getOutputs();
                    Character[] m = d[0].getDirs();

                    for (int n = 0; n < tapes.length; n++) 
                        tapes[n].move(i[n], o[n], m[n]);

                    currentState = d[0].getTargetState();

                    System.out.println("> Current state: " + currentState);
                    for (Tape t : tapes)
                        System.out.println(t);
                
                } // if

            } // if

        } // while

    } // simulate

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
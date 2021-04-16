package test;

import java.util.Scanner;
import project.tm.*;

public final class TestDriver {

    public static void main(String[] args) {

        /*  Title of the TM  */
        String title = "{a^nb^n | n >= 0}";

        /** 
         *  Non-null set of states for the TM.
         *  Each state must have a unique name,
         *  and the set must contain a state
         *  named "qa", which is used as the
         *  accepting state for the TM. The
         *  "qr" state, while not required, is
         *  another special state that is used
         *  as the rejecting state when included.
         */
        String[] Q = {"q0", "q1", "q2", "q3", "q4", "qa", "qr"};
        

        /**
         *  Non-null input alphabet for the TM.
         *  This variable determines which 
         *  characters are allowed to be
         *  used in an input string. Each 
         *  character must be unique and 
         *  be a letter, number, or special
         *  character. '_' cannot be used
         *  here as it is reserved for the
         *  stack alphabet.
         */
        Character[] Σ = {'a', 'b'};
        

        /**
         *  Non-null stack alphabet for the TM.
         *  This variable determines which
         *  characters are allowed to be
         *  written to this TM's tapes. This
         *  set must contain every character
         *  from the input alphabet as well as
         *  the '_' symbol, which is used to
         *  denote an empty slot on the tape.
         *  Other characters may be added here
         *  as needed.
         */
        Character[] T = {'_', 'a', 'b', 'x'};


        /* Number of tapes for the TM */
        int tapes = 1;
        

        /**
         *  Non-null set of moves for the TM.
         *  This variable can store three
         *  different characters ('L', 'R',
         *  and 'S'), which represent the 
         *  actions of moving left on a tape,
         *  moving right on a tape, and 
         *  staying in place, respectively.
         */
        Character[] moves = {'L', 'R'};
        

        /**
         *  Initial state for the TM.
         *  This state must be included
         *  in Q.
         */
        String initialState = "q0";


        /**
         *  Set of all transition functions used
         *  by the TM. 
         * 
         *  General form for a transition function:
         * 
         *      initialState->targetState: readChar->writeChar, move;
         * 
         *  The initial and target states for the
         *  transition function must exist in Q,
         *  the characters that are read and
         *  written must exist in T, and the move must
         *  exist in moves.
         * 
         *  When using multiple tapes, the form will follow as such:
         * 
         *      2-tape | initialState->targetState: readChar->writeChar, move // readChar->writeChar, move;
         *      3-tape | initialState->targetState: readChar->writeChar, move // readChar->writeChar, move // readChar->writeChar, move;
         *      ...                                 (tape 1)                     (tape 2)                     (tape 3)
         * 
         */
        Transition[] d = {
            new Transition("q0->q1: a->_,R;", tapes),
            new Transition("q1->q1: a->a,R;", tapes),
            new Transition("q1->q1: x->x,R;", tapes),
            new Transition("q1->qr: _->_,R;", tapes),
            new Transition("q1->q2: b->x,R;", tapes),
            new Transition("q2->q2: b->b,R;", tapes),
            new Transition("q2->q3: _->_,L;", tapes),
            new Transition("q3->q3: a->a,L;", tapes),
            new Transition("q3->q3: b->b,L;", tapes),
            new Transition("q3->q3: x->x,L;", tapes),
            new Transition("q3->q4: _->_,R;", tapes),
            new Transition("q4->q4: x->x,R;", tapes),
            new Transition("q4->q1: a->x,R;", tapes),
            new Transition("q0->qr: b->_,R;", tapes),
            new Transition("q4->qr: b->_,R;", tapes),
            new Transition("q2->qr: a->a,R;", tapes),
            new Transition("q0->qa: _->_,R;", tapes),
            new Transition("q4->qa: _->_,R;", tapes)
        };


        TuringMachine tm = new TuringMachine(
            title, Q, Σ, T, tapes, 
            moves, initialState, d
        );

        Scanner sc = new Scanner(System.in);

        System.out.print("Choose an input string to simulate: ");
        tm.simulate(sc.nextLine());

        sc.close();

    } // main

} // TestDriver
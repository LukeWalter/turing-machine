package test;

import project.tm.*;

public final class TestDriver {

    public static void main(String[] args) {

        // Tape t = new Tape("Tape 1");
        // System.out.println(t);
        // t.move('_', 'a', 'R');
        // System.out.println(t);
        // t.move('_', 'a', 'R');
        // System.out.println(t);
        // t.move('_', 'a', 'R');
        // System.out.println(t);

        Transition trans = new Transition("q1->q2: _->_,R // _->_,R;", 2);

    } // main

} // TestDriver
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

        Transition d = new Transition("q1->q2: _->a,R // _->b,R // _->c,R // _->d,R;", 4);
        //                             012345678901234567890123456789
        //                             0         1         2         
    } // main

} // TestDriver
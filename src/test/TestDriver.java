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

        TuringMachine tm = new TuringMachine(
            "Test!", 
            new String[]{"q0", "q1", "q2"}, 
            new Character[]{'a', 'b', 'c', 'd'}, 
            new Character[]{'_', 'a', 'b', 'c', 'd'}, 
            4, 
            new Character[]{'L', 'R', 'S'}, 
            "q0", 
            new Transition[] {
                new Transition("q1->q2: _->a,R // _->b,R // _->c,R // _->d,R;", 4)

            }
        );

        System.out.println(tm);

    } // main

} // TestDriver
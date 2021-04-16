package test;

// import java.util.Scanner;
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
            "{a^nb^n | n >= 0}", 
            new String[]{"q0", "q1", "q2", "q3", "q4", "qa", "qr"}, 
            new Character[]{'a', 'b'}, 
            new Character[]{'_', 'a', 'b', 'x'}, 
            1, 
            new Character[]{'L', 'R'}, 
            "q0", 
            new Transition[] {
                new Transition("q0->q1: a->_,R;", 1),
                new Transition("q1->q1: a->a,R;", 1),
                new Transition("q1->q1: x->x,R;", 1),
                new Transition("q1->qr: _->_,R;", 1),
                new Transition("q1->q2: b->x,R;", 1),
                new Transition("q2->q2: b->b,R;", 1),
                new Transition("q2->q3: _->_,L;", 1),
                new Transition("q3->q3: a->a,L;", 1),
                new Transition("q3->q3: b->b,L;", 1),
                new Transition("q3->q3: x->x,L;", 1),
                new Transition("q3->q4: _->_,R;", 1),
                new Transition("q4->q4: x->x,R;", 1),
                new Transition("q4->q1: a->x,R;", 1),
                new Transition("q0->qr: b->_,R;", 1),
                new Transition("q4->qr: b->_,R;", 1),
                new Transition("q2->qr: a->a,R;", 1),
                new Transition("q0->qa: _->_,R;", 1),
                new Transition("q4->qa: _->_,R;", 1)

            }
        );

        System.out.println(tm);

        tm.simulate("");

        // Scanner sc = new Scanner(System.in);
        // tm.accepts(sc.nextLine());
        // sc.close();

    } // main

} // TestDriver
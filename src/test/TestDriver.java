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
            "Test!", 
            new String[]{"q0", "q1", "q2"}, 
            new Character[]{'a', 'b', 'c', 'd'}, 
            new Character[]{'_', 'a', 'b', 'c', 'd'}, 
            4, 
            new Character[]{'L', 'R', 'S'}, 
            "q0", 
            new Transition[] {
                new Transition("q1->q2: _->a,L // _->b,R // _->c,S // _->d,L;", 4),
                new Transition("q1->q2: a->_,R // b->_,S // c->_,L // d->_,R;", 4)

            }
        );

        System.out.println(tm);

        // Scanner sc = new Scanner(System.in);
        // tm.accepts(sc.nextLine());
        // sc.close();

    } // main

} // TestDriver
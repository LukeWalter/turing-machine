package test;

import project.tm.Tape;

public final class TestDriver {

    public static void main(String[] args) {

        Tape t = new Tape("Tape 1");
        System.out.println(t);
        t.move('_', 'a', 'R');
        System.out.println(t);
        t.move('_', 'a', 'R');
        System.out.println(t);
        t.move('_', 'a', 'R');
        System.out.println(t);

    } // main

} // TestDriver
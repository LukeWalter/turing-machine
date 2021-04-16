package project.tm;

public final class Transition {

    int tapes;

    String initialState;
    String targetState;
    char[] inputs;
    char[] outputs;
    char[] dirs;

    public Transition(String delta, int numTapes) {

        try {
            
            if (numTapes < 0) throw new Exception();
            this.tapes = numTapes;

            delta = delta.replace(" ", "");

            int i = delta.indexOf(":");
            if (i == -1) throw new Exception();

            String[] parser = new String[7 + ((numTapes - 1) * 6)];

            parser[0] = delta.substring(0, i + 1);     // "iState->tState:"
            parser[1] = delta.substring(i + 1, i + 2); // "input (tape 1)"
            parser[2] = delta.substring(i + 2, i + 4); // "->"
            parser[3] = delta.substring(i + 4, i + 5); // "output (tape 1)"
            parser[4] = delta.substring(i + 5, i + 6); // ","
            parser[5] = delta.substring(i + 6, i + 7); // "move direction"

            int t;
            for (t = 0; t < (numTapes - 1); t++) {
                
                parser[6 + t * 6] = delta.substring((i + 7) + (t * 6), (i + 9) + (t * 6));    // "//"
                parser[7 + t * 6] = delta.substring((i + 9) + (t * 6), (i + 10) + (t * 6));   // "input (tape 2+)"
                parser[8 + t * 6] = delta.substring((i + 10) + (t * 6), (i + 12) + (t * 6));  // "->"
                parser[9 + t * 6] = delta.substring((i + 12) + (t * 6), (i + 13) + (t * 6));  // "output (tape 2+)"
                parser[10 + t * 6] = delta.substring((i + 13) + (t * 6), (i + 14) + (t * 6)); // ","
                parser[11 + t * 6] = delta.substring((i + 14) + (t * 6), (i + 15) + (t * 6)); // "move direction"

            } // for

            parser[parser.length - 1] = delta.substring(delta.length() - 1, delta.length()); // ;

            if (delta.length() > ((i + 15) + (t * 6))) throw new Exception();

            for (String s : parser) {
                s.charAt(0);
                System.out.println(s);
            
            } // for

        } catch (Exception e) {
            MalformedTransitionException mte = new MalformedTransitionException();
            throw mte;

        } // try

        inputs = new char[numTapes];
        outputs = new char[numTapes];
        dirs = new char[numTapes];

    } // Constructor

} // Transition
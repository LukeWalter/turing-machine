package project.tm;

public final class Transition {

    int tapes;

    String initialState;
    String targetState;
    Character[] inputs;
    Character[] outputs;
    Character[] dirs;

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
                
                parser[6 + t * 6] = delta.substring((i + 7) + (t * 8), (i + 9) + (t * 8));    // "//"
                parser[7 + t * 6] = delta.substring((i + 9) + (t * 8), (i + 10) + (t * 8));   // "input (tape 2+)"
                parser[8 + t * 6] = delta.substring((i + 10) + (t * 8), (i + 12) + (t * 8));  // "->"
                parser[9 + t * 6] = delta.substring((i + 12) + (t * 8), (i + 13) + (t * 8));  // "output (tape 2+)"
                parser[10 + t * 6] = delta.substring((i + 13) + (t * 8), (i + 14) + (t * 8)); // ","
                parser[11 + t * 6] = delta.substring((i + 14) + (t * 8), (i + 15) + (t * 8)); // "move direction"

            } // for

            parser[parser.length - 1] = delta.substring(delta.length() - 1, delta.length()); // ;
            if (delta.length() > ((i + 15) + (t * 6))) throw new Exception();

            String[] states = parser[0].substring(0, i).split("->", 3);
            if (states.length != 2 || 
                states[0].equals("") || states[1].equals("") || 
                !states[0].matches("^[a-zA-Z0-9]*$") ||
                !states[1].matches("^[a-zA-Z0-9]*$")) 
                throw new Exception();

            if (!parser[parser.length - 1].equals(";")) 
                throw new Exception();

            for (int s = 1; s < parser.length - 1; s++) {

                if (s % 2 == 0) {
                    if ((s % 6 == 0 && !parser[s].equals("//")) || 
                        (s % 6 == 2 && !parser[s].equals("->")) ||
                        (s % 6 == 4 && !parser[s].equals(","))) 
                        throw new Exception(); 

                } else {
                    char x = parser[s].charAt(0);
                    if (parser[s].length() != 1 || 
                        !(x > 32 && x < 127) ||
                        (s % 6 == 5 &&
                        (x != 'L' && x != 'R' && x != 'S')))
                        throw new Exception();

                } // if

            } // for

            initialState = states[0];
            targetState = states[1];

            inputs = new Character[numTapes];
            inputs[0] = parser[1].charAt(0);
            for (int in = 1; in < inputs.length; in++) inputs[in] = parser[7 + (in - 1) * 6].charAt(0);

            outputs = new Character[numTapes];
            outputs[0] = parser[3].charAt(0);
            for (int out = 1; out < outputs.length; out++) outputs[out] = parser[9 + (out - 1) * 6].charAt(0);

            dirs = new Character[numTapes];
            dirs[0] = parser[5].charAt(0);
            for (int dir = 1; dir < dirs.length; dir++) dirs[dir] = parser[11 + (dir - 1) * 6].charAt(0);

        } catch (Exception e) {
            throw new MalformedTransitionException();

        } // try

    } // Constructor

    // public int getTapes { return tapes; }

    public int getTapes() { return tapes; }
    public String getInitialState() { return initialState; }
    public String getTargetState() { return targetState; }
    public Character[] getInputs() { return inputs; }
    public Character[] getOutputs() { return outputs; }
    public Character[] getDirs() { return dirs; }

    @Override
    public String toString() {

        String dt = "𝛿-transition(" + tapes + "-tape)::[" + initialState + "->" + targetState + ": ";

        for (int i = 0; i < tapes; i++) {
            dt += inputs[i] + "->" + outputs[i] + ", " + dirs[i] + " // ";

        } // for

        return dt.substring(0, dt.length() - 4) + ";]";

    } // toString

} // Transition
package project.tm;

public class Tape {

    protected String name;
    protected int head;
    protected char[] contents;

    public Tape(String name) {
        
        this.name = name;
        head = 0;
        
        contents = new char[2];
        contents[0] = '_';
        contents[1] = '_';

    } // Tape

    public Tape(String name, String input) {
        
        this.name = name;
        head = 0;
        
        int len = input.length();
        contents = new char[len + 1];
        
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (!(c > 32 && c < 127)) throw new InvalidInputException();
            contents[i] = c;
            
        } // for

        contents[len] = '_';

    } // Tape
    
    public void move(char input, char output, char dir) {

        if (input == contents[head]) {

            switch (dir) {
                
                case 'L':
                    contents[head] = output;
                    if (head > 0) head--;
                    break;

                case 'R':
                    contents[head] = output;
                    head++;
                    if (head >= contents.length) expand();
                    break;

                case 'S':
                    contents[head] = output;
                    break;

                default:
                    throw new IllegalArgumentException();

            } // switch

        } else {
            throw new IllegalArgumentException();

        } // if

    } // move

    private void expand() {

        char[] newContents = new char[contents.length + 1];

        for (int i = 0; i < contents.length; i++) {
            newContents[i] = contents[i];

        } // for

        newContents[newContents.length - 1] = '_';
        contents = newContents;

    } // expand

    @Override
    public String toString() {

        String tape = name + ": | ";
        int padding = tape.length();

        for (char c : contents) {
            tape += c + " | ";
        
        } // for

        String pointer = String.format("%" + (padding + head * 4) + "s%s" , " ", "^");

        return tape + "... |\n" + pointer;

    } // toString

} // Tape
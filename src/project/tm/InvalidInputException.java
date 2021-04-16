package project.tm;

public class InvalidInputException extends TMException { 
    
    public InvalidInputException() {
        super();
    
    } // Constructor
    
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    
    } // Constructor

} // TMException
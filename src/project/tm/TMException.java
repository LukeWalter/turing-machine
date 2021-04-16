package project.tm;

public class TMException extends RuntimeException { 
    
    public TMException() {
        super();
    
    } // Constructor

    public TMException(String errorMessage) {
        super(errorMessage);
    
    } // Constructor

} // TMException
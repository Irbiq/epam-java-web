package exception;


public class InterpreterException extends Exception {
    public InterpreterException() {
    }
    public InterpreterException(String message) {
        super(message);
    }
    public InterpreterException(Throwable ex) {
        super(ex);
    }
}

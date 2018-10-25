package net.exceptions;

public class LayerNotFoundException extends RuntimeException {
    public LayerNotFoundException() { super(); }
    public LayerNotFoundException(String message) { super(message); }
    public LayerNotFoundException(String message, Throwable cause) { super(message, cause); }
    public LayerNotFoundException(Throwable cause) { super(cause); }

}

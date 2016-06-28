package edt.core;

/**
 * Exception for the case where a non-existing filepath
 * is used to open a document.
 */
public class DocumentNotFoundException extends DocumentIOException {

    public DocumentNotFoundException() {
        super("Cannot find Document");
    }

    public DocumentNotFoundException(String message) {
        super("Cannot find Document: " + message);
    }
}

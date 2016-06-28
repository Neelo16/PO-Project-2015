package edt.core;

/**
 * General IO Exception for TextEditor operations.
 */
public class DocumentIOException extends TextEditorException {

    public DocumentIOException() {
        super("Exception during IO");
    }

    public DocumentIOException(String message) {
        super(message);
    }
}

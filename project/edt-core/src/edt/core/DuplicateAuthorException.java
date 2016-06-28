package edt.core;

/**
 * Exception for the case where a duplicate author is attempted 
 * to be added.
 */
public class DuplicateAuthorException extends TextEditorException {
    public DuplicateAuthorException() {
        super("Author already exists");
    }
}

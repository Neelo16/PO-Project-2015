package edt.core;

/**
 * Exception for invalid get operations for TextElements.
 */
public class NoSuchTextElementException extends TextEditorException {
    public NoSuchTextElementException() {
        super("Invalid get operation for a nonexistent TextElement");
    }
}
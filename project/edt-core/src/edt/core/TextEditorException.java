package edt.core;

/**
 * Basic Exception class for TextEditor operations. Should not
 * be instantiated.
 */
public abstract class TextEditorException extends Exception {

    /**
     * Create a new instance of TextEditorException.
     *
     * @param  message
     *             a description of the exception.
     *
     */
    public TextEditorException(String message) {
        super(message);
    }
}

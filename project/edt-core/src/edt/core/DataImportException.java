package edt.core;

/**
 * Exception for errors processing import files.
 */
public class DataImportException extends TextEditorException {
    public DataImportException() {
        super("Could not parse import file");
    }
}

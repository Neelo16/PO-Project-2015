package edt.core;

import java.util.Collection;

/**
 * A paragraph. Stores text.
 */
public class Paragraph extends TextElement {

    /** The paragraph's text. */
    private String _text;

    /**
     * @param text
     *             the paragraph's text.
     */
    public Paragraph(String text) {
        _text = text;
    }

    /**
     * Get the paragraph's text.
     *
     * @return the text.
     */
    public String getText() {
        return _text;
    }

    /**
     * Set the paragraph's text.
     *
     * @param text
     *            the paragraph's new text.
     */
    public void setText(String text) { 
        _text = text; 
    }

    /**
     * Get the paragraph's size (in bytes).
     * It is calculated by the number of
     * characters in the paragraph's text.
     *
     * @return the size.
     */
    public int getSize() { 
        return _text.length();
    }
    
    /**
     * Accept a visitor to perform
     * an operation on this Paragraph.
     *
     * @param visitor
     *            the visitor.
     *            
     * @return a Collection of Strings.
     */
    public Collection<String> accept(TextElementVisitor visitor) {
        return visitor.visitParagraph(this);
    }
}
package edt.core;

import java.io.Serializable;
import java.util.Collection;

/**
 * An abstract text element. It has a unique identifier (a string).
 * Subclasses must implement a method to calculate their own size 
 * and to show their content.
 */
public abstract class TextElement implements Serializable {

    /** The text element's unique identifier */
    private String _uid = "";

    /**
     * Get the text element's unique id.
     *
     * @return the uid or an empty String
     *             if it has none.
     */
    public String getUniqueId() { 
        return _uid; 
    }

    /**
     * Set the text element's unique id.
     *
     * @param uid
     *            the new unique id.
     */
    public void setUniqueId(String uid) { 
        _uid = uid; 
    }

    /**
     * Checks if the TextElement has a
     * unique id.
     *
     * @return true if the element has a uid;
     *              false otherwise.
     */
    public boolean hasUniqueId() {
        return !_uid.equals("");
    }

    /**
     * Removes the unique id from the
     * TextElement.
     * 
     */
    public void clearUniqueId() {
        _uid = "";
    }

    /**
     * Get the text element's size in bytes.
     *
     * @return the size.
     */
    public abstract int getSize();

    /**
     * Accept a visitor to perform
     * operations on this TextElement.
     *
     * @param visitor
     *            the visitor. 
     * 
     * @return a Collection of Strings.
     * 
     */
    public abstract Collection<String> accept(TextElementVisitor visitor);
}
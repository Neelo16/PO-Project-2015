package edt.core;
import java.io.Serializable;

/**
 * An author. It has a name and a contact.
 * Can be compared to other authors, using
 * their name as a key.
 */
public class Author implements Serializable, Comparable<Author> {

    /** The author's name. */
    private String _name;

    /** The author's email address. */
    private String _contact;

    /**
     *
     * @param  name
     *             the author's name.
     *
     * @param  contact
     *             the author's email address.
     * 
     */
    public Author(String name, String contact) {
        _name    = name;
        _contact = contact;
    }
    
    /**
     * Get the author's name.
     *
     * @return the name.
     */
    public String getName() { 
        return _name; 
    }

    /**
     * Get the author's email address.
     *
     * @return the address.
     */
    public String getContact() { 
        return _contact; 
    }

    /**
     * Set the author's name.
     *
     * @param name
     *            the new name.
     */
    public void setName(String name) { 
        _name = name; 
    }

    /**
     * Set the author's contact.
     *
     * @param contact
     *            the new email address.
     */
    public void setContact(String contact) { 
        _contact = contact; 
    }

    /**
     * Compares two authors.
     *
     * @param  other
     *             the author to be compared to this one.
     *
     * @return a negative value, zero, or a positive value,
     *           depending on the lexicographical comparison 
     *           between the two authors' names 
     *           (see java.lang.String#compareTo(java.lang.String))
     */
    @Override
    public int compareTo(Author other) {
        return _name.compareTo(other.getName());
    }
    
}
package edt.core;

import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A text document. It has authors, paragraphs and
 * subsections. Can be saved to disk.
 */
public class Document extends Section {

    /** The document's filepath. */
    private transient String _filepath = null;

    /** The document's authors. */
    private TreeSet<Author> _authors = new TreeSet<Author>(); 

    /** The document's named text elements. */
    private TreeMap<String,TextElement> _elements = 
                                new TreeMap<String,TextElement>();

    public Document() {
        super("");
    }

    /**
     * Sets the document's filepath.
     *
     * @param path
     *            the document's new filepath.
     */
    public void setFilepath(String path) {
        _filepath = path;
    }

    /**
     * Gets the document's filepath.
     *
     * @return the filepath.
     */
    public String getFilepath() { 
        return _filepath; 
    }

    /**
     * Checks if the document has a filepath.
     * 
     * @return true if the document has a filepath set; 
     *              false otherwise.
     */
    public boolean hasFilepath() { 
        return _filepath != null;
    }

    /**
     * Gets a text element.
     *
     * @param  uid
     *             the element's unique identifier.
     *
     * @return the element.
     *
     * @throws NoSuchTextElementException
     *             if there's no element with that uid.
     */
    public TextElement getTextElement(String uid) 
                        throws NoSuchTextElementException {
        TextElement element = _elements.get(uid);
        if (element == null)
            throw new NoSuchTextElementException();
        return element;
    }

    /**
     * Gets a Collection of the document's authors,
     * sorted alphabetically.
     * 
     * This Collection can not be modified.
     *
     * @return the collection.
     */
    public Collection<Author> getSortedAuthors() {
        return Collections.unmodifiableCollection(_authors);
    }

    /**
     * Returns the number of unique identifiers in
     * the document.
     *
     * @return the number of unique identifiers.
     */
    public int getNumberOfUniqueIds() { 
        return _elements.size(); 
    }

    /**
     * Creates a new author and adds it to
     * the document.
     *
     * @param name
     *            the new author's name. 
     *            
     * @param contact
     *            the new author's contact.
     *
     * @return true if the author was added 
     *              (if it was not a duplicate); 
     *              false otherwise.
     */
    public boolean addAuthor(String name, String contact) {
        Author autor = new Author(name, contact);
        return _authors.add(autor);
    }

    /**
     * Sets a TextElement's unique identifier and stores it
     * for later reference.
     *
     * @param  uid
     *             the TextElement's new uid.
     * 
     * @param  element
     *             the TextElement.
     *
     * @return true if the element had a previous uid; 
     *              false otherwise.
     */
    public boolean nameTextElement(String uid, TextElement element) {
        boolean hadPreviousUid = element.hasUniqueId();

        if (hadPreviousUid)
            _elements.remove(element.getUniqueId());

        element.setUniqueId(uid);

        if (!uid.equals("")) {
            TextElement previousElement = _elements.put(uid, element);

            if (previousElement != null)
                previousElement.clearUniqueId();
        }

        return hadPreviousUid;
    }

    /**
     * Remove a paragraph from the list
     * of named TextElements.
     *
     * @param paragraph
     *            the paragraph to remove.
     */
    public void removeParagraph(Paragraph paragraph) {
        _elements.remove(paragraph.getUniqueId());
    }

    /**
     * Remove a section from the list of
     * named TextElements, as well as all
     * of its subsections and paragraphs.
     *
     * @param section 
     *            the section to be removed.
     */
    public void removeSection(Section section){
        Collection<Section> subsections = section.getSubsections();
        Collection<Paragraph> paragraphs = section.getParagraphs();

        for(Section s: subsections)
            removeSection(s);

        for (Paragraph p: paragraphs)
            removeParagraph(p);

        _elements.remove(section.getUniqueId());
    }

    /**
     * Accept a visitor to perform
     * an operation on this Document.
     * 
     *
     * @param visitor
     *            the visitor.
     *            
     * @return a Collection of Strings.
     */
    public Collection<String> accept(TextElementVisitor visitor) {
        return visitor.visitDocument(this);
    }
}
package edt.core;

import java.util.Collection;

/**
 * Interface to be used to manipulate TextElements
 * without having to check for the specific type
 * of the Element.
 * Return type of the methods is a Collection of
 * Strings to allow the visitor
 * to get information from the TextElements and return
 * it to the caller so it can be ouputted.
 * 
 */
public interface TextElementVisitor {

    /**
     * Visit a document.
     *
     * @param document
     *            the document.
     *
     * @return a Collection of Strings.
     */
    public Collection<String> visitDocument(Document document);

    /**
     * Visit a section.
     *
     * @param section
     *            the section.
     *            
     * @return a Collection of Strings.
     */
    public Collection<String> visitSection(Section section);

    /**
     * Visit a paragraph.
     *
     * @param paragraph
     *            the paragraph.
     *            
     * @return a Collection of Strings.
     */
    public Collection<String> visitParagraph(Paragraph paragraph);
}
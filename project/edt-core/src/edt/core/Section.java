package edt.core;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import java.util.Collections;
import java.util.Collection;

/**
 * Sections have subsections and paragraphs.
 * Each section has a title.
 */
public class Section extends TextElement {
        
    /** Section title. */
    private String _title;

    /** The section's paragraphs. */
    private List<Paragraph> _paragraphs = new LinkedList<Paragraph>();

    /** The section's subsections. */
    private List<Section> _subsections  = new LinkedList<Section>();

    /**
     * @param title
     *          the section's title.
     */
    public Section(String title) {
        _title = title;
    }

    /**
     * Get the section's title.
     * 
     * @return the title.
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Change the section's title.
     * 
     * @param title
     *          the new title.
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Get one of the section's paragraphs.
     *
     * @param id
     *             the paragraph's order number.
     *
     * @return the paragraph.
     *
     * @throws NoSuchTextElementException 
     *             if there's no paragraph with that id.
     */
    public Paragraph getParagraph(int id) 
                    throws NoSuchTextElementException {
        try {
            return _paragraphs.get(id);
        }
        catch (IndexOutOfBoundsException e) {
            throw new NoSuchTextElementException();
        }
    }

    /**
     * Get one of the section's subsections.
     *
     * @param  id
     *             the section's order number.
     *
     * @return the section.
     *
     * @throws NoSuchTextElementException
     *             if there's no section with that id.
     */
    public Section getSubsection(int id) 
                    throws NoSuchTextElementException {
        try {
            return _subsections.get(id);
        }
        catch (IndexOutOfBoundsException e) {
            throw new NoSuchTextElementException();
        }
    }

    /**
     * Get a collection containing all of the
     * section's subsections.
     * 
     * This Collection can not be modified.
     *
     * @return the subsection collection.
     */
    public Collection<Section> getSubsections() {
        return Collections.unmodifiableCollection(_subsections);
    }

    /**
     * Get a collection containing all of the
     * section's subsections, and their subsections,
     * recursively, depth-first.
     *
     * @return the subsection collection.
     */
    public Collection<Section> getRecursiveSubsections() {
        Collection<Section> sections = new LinkedList<Section>();
        for (Section s : _subsections) {
            sections.add(s);
            sections.addAll(s.getRecursiveSubsections());
        }
        return sections;
    }

    /**
     * Get a collection containing all of the
     * section's paragraphs.
     * 
     * This Collection can not be modified.
     *
     * @return the paragraphs collection.
     */
    public Collection<Paragraph> getParagraphs() {
        return Collections.unmodifiableCollection(_paragraphs);
    }

    /**
     * Get the number of subsections.
     *
     * @return the number of subsections.
     */
    public int getNumberOfSubsections() {
        return _subsections.size(); 
    }

    /** 
     * Creates a new section and inserts it
     *  before another section.
     *  
     * @param id
     *          the section's order number.
     * 
     * @param title
     *          the new section's title. 
     *          
     * @return the new section.
     */
    public Section insertSection(int id, String title) {
        Section section = new Section(title);
        try {
            _subsections.add(id, section);
        }
        catch (IndexOutOfBoundsException e) {
            _subsections.add(section);
        }
        return section;
    }

    /**
     * Creates a new section and inserts it as the
     * last subsection.
     * 
     * @param title
     *          the new section's title.
     *          
     * @return the new section.
     */
    public Section insertSection(String title) {
        return insertSection(_subsections.size(), title);
    }

    /** 
     * Creates a new paragraph and inserts it
     *  before another paragraph.
     *  
     * @param id
     *          the paragraph's order number.
     *          
     * @param title
     *          the new paragraphs's title. 
     *          
     * @return the new paragraph.
     */
    public Paragraph insertParagraph(int id, String title) {
        Paragraph paragraph = new Paragraph(title);
        try {
            _paragraphs.add(id, paragraph);
        }
        catch (IndexOutOfBoundsException e) {
            _paragraphs.add(paragraph);
        }

        return paragraph;
    }

    /**
     * Creates a new paragraph and inserts it as the
     * last paragraph.
     * 
     * @param title
     *          the new paragraph's title.
     *          
     * @return the new paragraph.
     */
    public Paragraph insertParagraph(String title) {
        return insertParagraph(_paragraphs.size(), title);
    }

    /**
     * Removes a section from the list of 
     * subsections.
     *
     * @param  id
     *             the order number of the section
     *             to be removed.
     *
     * @throws NoSuchTextElementException
     *            if the section was not found.
     *
     * @return the removed Section.
     */
    public Section removeSubsection(int id) 
                throws NoSuchTextElementException { 
        try {
            Section section = getSubsection(id);
            _subsections.remove(id);
            return section;
        }
        catch (IndexOutOfBoundsException e) {
            throw new NoSuchTextElementException();
        }
    }

    /**
     * Removes a paragraph from the section's paragraphs.
     * 
     * @param  id
     *             the order number of the paragraph
     *             to be removed.
     *
     * @throws NoSuchTextElementException
     *             if the paragraph was not found.
     *
     * @return the removed paragraph.
     */
    public Paragraph removeParagraph(int id) 
                throws NoSuchTextElementException {
        try {
            Paragraph paragraph = getParagraph(id);
            _paragraphs.remove(id);
            return paragraph;
        }
        catch (IndexOutOfBoundsException e) {
            throw new NoSuchTextElementException();
        }
    }

    /**
     * Changes a paragraph's text.
     *
     * @param id
     *            the paragraph's order number.
     *
     * @param text
     *            the paragraph's new text.
     *
     * @throws  NoSuchTextElementException
     *              if the paragraph was not found.
     */
    public void changeParagraph(int id, String text) 
                throws NoSuchTextElementException {
        try {
            Paragraph paragraph = _paragraphs.get(id);
            paragraph.setText(text);
        }
        catch (IndexOutOfBoundsException e) {
            throw new NoSuchTextElementException();
        }
    }

    /**
     * Get the section's size (in bytes).
     * It is given by the sum of the size of
     * all subsections, paragraphs, and the
     * title of this section.
     *
     * @return the section's size.
     */
    public int getSize() {
        String title = getTitle();
        int    size  = title.length();

        for (Paragraph p : _paragraphs)
            size += p.getSize();

        for (Section s : _subsections)
            size += s.getSize();

        return size;
    }
    
    /**
     * Accept a visitor to perform
     * an operation on this Section.
     *
     * @param visitor
     *            the visitor.
     *            
     * @return a Collection of Strings.
     */
    public Collection<String> accept(TextElementVisitor visitor) {
        return visitor.visitSection(this);
    }
}
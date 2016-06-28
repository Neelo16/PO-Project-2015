package edt.textui;

import edt.core.TextElementVisitor;
import edt.core.Document;
import edt.core.Section;
import edt.core.Paragraph;

import java.util.Collection;
import java.util.ArrayList;

import edt.textui.main.Message;

/**
 * A Visitor used to show the content of a TextElement.
 * 
 */
public class ShowContentVisitor implements TextElementVisitor {

    /**
     * Gets the strings used to show the content
     * of a document.
     *
     * @param document
     *            the document.
     *
     * @return a Collection containing those Strings.
     */
    public Collection<String> visitDocument(Document document) {
        String title = document.getTitle();

        Collection<String>    content     = new ArrayList<String>();
        Collection<Section>   subsections = document.getSubsections();
        Collection<Paragraph> paragraphs  = document.getParagraphs();

        content.add("{" + title + "}");

        for (Paragraph p : paragraphs)
            content.addAll(p.accept(this));

        for (Section s : subsections)
            content.addAll(s.accept(this));

        return content;
    }

    /**
     * Gets the strings used to show the content
     * of a section.
     *
     * @param section
     *            the section.
     *
     * @return a Collection containing those Strings.
     */
    public Collection<String> visitSection(Section section) {
        String title = section.getTitle();
        String uid   = section.getUniqueId();

        Collection<String>    content     = new ArrayList<String>();
        Collection<Section>   subsections = section.getSubsections();
        Collection<Paragraph> paragraphs  = section.getParagraphs();
        
        content.add(Message.sectionIndexEntry(uid, title));

        for (Paragraph p : paragraphs)
            content.addAll(p.accept(this));

        for (Section s : subsections)
            content.addAll(s.accept(this));

        return content;
    }

    /**
     * Gets the strings used to show the content
     * of a paragraph.
     *
     * @param paragraph
     *            the paragraph.
     *
     * @return a Collection containing those Strings.
     */ 
    public Collection<String> visitParagraph(Paragraph paragraph) {
        Collection<String> text = new ArrayList<String>();
        text.add(paragraph.getText());
        return text;
    }
}
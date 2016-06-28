package edt.core;

import java.util.Collection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A Text Editor. Contains a document and can interact with it.
 */
public class Editor {

    /** The editor's open document. */
    private Document _document = new Document();

    /**
     * Get the open document.
     *
     * @return the document.
     */
    public Document getDocument() {
        return _document;
    }

    /**
     * Set the open document.
     *
     * @param document
     *            the new document.
     */
    public void setDocument(Document document) {
        _document = document;
    }

    /**
     * Change the document's associated filepath.
     *
     * @param path
     *            the new filepath.
     */
    public void setDocumentFilepath(String path) {
        _document.setFilepath(path);
    }

    /**
     * Check if the open document has an associated
     * filepath.
     *
     * @return true if the document has a path; false otherwise.
     */
    public boolean documentHasFilepath() {
        return _document.hasFilepath();
    }

    /**
     * Get all the open document's top level sections.
     * 
     * This Collection can not be modified.
     *
     * @return a Collection containing the top level sections.
     */
    public Collection<Section> getDocumentTopLevelSections() {
        return _document.getSubsections();
    }

    /**
     * Get the open document's title.
     *
     * @return the document's title.
     */
    public String getDocumentTitle() {
        return _document.getTitle();
    }

    /**
     * Get s collection containing the document's 
     * authors.
     * 
     * This Collection can not be modified.
     *
     * @return a Collection containing the document's
     *           authors, sorted alphabetically.
     */
    public Collection<Author> getDocumentAuthors() {
        return _document.getSortedAuthors();
    }

    /**
     * Get the number of top level sections in the
     * open document.
     *
     * @return the number of top level sections.
     */
    public int getNumberOfTopLevelSections() {
        return _document.getNumberOfSubsections();
    }

    /**
     * Get the number of unique identifiers
     * in the open document.
     *
     * @return the number of uids.
     */
    public int getNumberOfUniqueIds() {
        return _document.getNumberOfUniqueIds();
    }

    /**
     * Get the document's size (in bytes).
     * It is the sum of the sizes of all
     * top level sections and paragraphs.
     *
     * @return the document's size.
     */
    public int getDocumentSize() {
        return _document.getSize();
    }

    /**
     * Get a text element with a certain
     * unique identifier.
     *
     * @param uid
     *             the element's uid.
     *
     * @return the text element.
     *
     * @throws NoSuchTextElementException
     *             if there's no element with that uid.
     */
    public TextElement getTextElement(String uid) 
                        throws NoSuchTextElementException {
        return _document.getTextElement(uid);
    }

    /**
     * Adds an author to the open document.
     *
     * @param  name
     *             the new author's name.
     * @param  contact
     *             the new author's email address.
     *
     * @throws DuplicateAuthorException
     *             if there's already an author with the same name.
     */
    public void addAuthor(String name, String contact) 
                throws DuplicateAuthorException {
        if(!_document.addAuthor(name, contact))
            throw new DuplicateAuthorException();
    }

    /**
     * Read a formatted import file and populate
     * the document using the information in that
     * file.
     *
     * @param  importFile
     *             the path to the file.
     *
     * @throws DataImportException
     *             if there's any issues reading the file.
     */
    public void readImportFile(String importFile) 
                throws DataImportException {
        try {
            BufferedReader reader = new BufferedReader(
                                    new FileReader(importFile));

            String line = reader.readLine();
            _document.setTitle(line);
            line = reader.readLine();

            String[] authors = line.split("\\|");
            for(String a : authors){
                String[] fields = a.split("/");
                _document.addAuthor(fields[0], fields[1]);
            }

            Section currentSection = _document;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                switch(fields[0]){
                    case "SECTION":
                        currentSection = insertSection(fields);
                        break;
                    case "PARAGRAPH":
                        insertParagraph(fields, currentSection);
                        break;
                    default:
                        throw new DataImportException();
                }
            }

            reader.close();
        }
        catch (IOException e) {
            throw new DataImportException();
        }
    }

    /**
     * Insert a new section in the document,
     * from fields given by a formatted import file.
     *
     * @param fields
     *            the Strings read from the file.
     *
     * @return the new section.
     */
    private Section insertSection(String[] fields) {
        Section section = _document.insertSection(fields[2]);
        _document.nameTextElement(fields[1], section);
        return section;
    }

    /**
     * Insert a new paragraph in a section,
     * using arguments read from a formatted
     * import file.
     *
     * @param fields
     *            the Strings read from the file.
     *
     * @param section
     *            the Section in which to insert the paragraph.
     *
     * @return the new paragraph.
     */
    private Paragraph insertParagraph(String[] fields, 
                                      Section section) {
        return section.insertParagraph(fields[1]);
    }

    /**
     * Opens a new, empty document.
     *
     */
    public void newDocument() { 
        _document = new Document(); 
    }
    
    /**
     * Saves the currently open Document to disk,
     * using the Document's associated filepath.
     *
     * @throws IOException
     *             if there's an issue saving the document.
     */
    public void save() throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(
                                    new BufferedOutputStream(
                                    new FileOutputStream(
                                     _document.getFilepath())));
        stream.writeObject(_document);
        stream.close();
    }

    /**
     * Opens a new Document from a file and associates
     * the file to the document.
     *
     * @param  pathname
     *             the path to the file from which to
     *             read the document.
     *
     * @throws DocumentNotFoundException
     *             if the file does not exist.
     *
     * @throws IOException
     *             if there are any issues opening the document.
     */
    public void open(String pathname) 
                throws IOException, 
                       DocumentNotFoundException {
        try {
            ObjectInputStream stream = new ObjectInputStream(
                                       new BufferedInputStream(
                                       new FileInputStream(
                                                        pathname)));
            Document document = (Document) stream.readObject();
            _document = document;
            _document.setFilepath(pathname);
            stream.close();
        }
        catch (FileNotFoundException e) {
            throw new DocumentNotFoundException(e.getMessage());
        }
        catch (ClassNotFoundException e) { }
    }
}
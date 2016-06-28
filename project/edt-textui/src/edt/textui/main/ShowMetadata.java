/** @version $Id: ShowMetadata.java,v 1.7 2015/11/28 11:57:17 ist181201 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.util.Collection;

import edt.core.Editor;
import edt.core.Document;
import edt.core.Author;

/**
 * §2.1.2.
 */
public class ShowMetadata extends Command<Editor> {
    public ShowMetadata(Editor receiver) {
        super(MenuEntry.SHOW_METADATA, receiver);
    }

    @Override
    public final void execute() throws DialogException, IOException {
        String title = _receiver.getDocumentTitle();

        Collection<Author> authors = _receiver.getDocumentAuthors();

        int numTopLevelSections = 
                            _receiver.getNumberOfTopLevelSections();

        int size = _receiver.getDocumentSize();      
        int numUIds = _receiver.getNumberOfUniqueIds();

        IO.println(Message.documentTitle(title));

        for (Author a : authors) {
            String name    = a.getName();
            String contact = a.getContact();
            IO.println(Message.author(name, contact));
        }
        
        IO.println(Message.documentSections(numTopLevelSections));
        IO.println(Message.documentBytes(size));
        IO.println(Message.documentIdentifiers(numUIds));
    }

}

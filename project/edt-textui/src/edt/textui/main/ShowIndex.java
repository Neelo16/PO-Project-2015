/** @version $Id: ShowIndex.java,v 1.9 2015/11/27 16:44:50 ist181201 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.util.Collection;

import edt.core.Editor;
import edt.core.Document;
import edt.core.Section;

/**
 * §2.1.4.
 */
public class ShowIndex extends Command<Editor> {
	public ShowIndex(Editor receiver) {
		super(MenuEntry.SHOW_INDEX, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
        IO.println("{" + _receiver.getDocumentTitle() + "}");

        Collection<Section> topLevelSections = 
                            _receiver.getDocumentTopLevelSections();

        for(Section section : topLevelSections) {
            String uid = section.getUniqueId();
            String title = section.getTitle();
            
            IO.println(Message.sectionIndexEntry(uid, title));
        }
	}
}

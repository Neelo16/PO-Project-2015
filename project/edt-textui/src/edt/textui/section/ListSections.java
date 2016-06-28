/** @version $Id: ListSections.java,v 1.8 2015/11/28 12:24:03 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import java.util.Collection;

import edt.core.Section;
import edt.core.Document;

/**
 * §2.2.2.
 */
public class ListSections extends SectionCommand {
	public ListSections(Section receiver, Document secondReceiver) {
		super(MenuEntry.LIST_SECTIONS, receiver, secondReceiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
        Collection<Section> subsections = 
                                _receiver.getRecursiveSubsections();

        for (Section s : subsections) {
            String title = s.getTitle();
            String id    = s.getUniqueId();
            IO.println(Message.sectionIndexEntry(id, title));
        }
	}
}

/** @version $Id: SelectSection.java,v 1.6 2015/11/28 11:57:18 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;
import edt.core.NoSuchTextElementException;

/**
 * §2.2.4.
 */
public class SelectSection extends SectionCommand {
	public SelectSection(Section receiver, Document secondReceiver) {
		super(MenuEntry.SELECT_SECTION, receiver, secondReceiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
    int id = IO.readInteger(Message.requestSectionId());
    try {
        Section section = _receiver.getSubsection(id);
        
        IO.println(Message.newActiveSection(id));

        edt.textui.section.MenuBuilder.menuFor(section, 
                                               _secondReceiver);
    }
    catch (NoSuchTextElementException e) {
        IO.println(Message.noSuchSection(id));
    }
	}
}

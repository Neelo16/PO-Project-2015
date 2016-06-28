/** @version $Id: ChangeTitle.java,v 1.5 2015/11/28 12:24:03 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;

/**
 * §2.2.1.
 */
public class ChangeTitle extends SectionCommand {
	public ChangeTitle(Section receiver, Document secondReceiver) {
		super(MenuEntry.CHANGE_TITLE, receiver, secondReceiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
        String title = IO.readString(Message.requestSectionTitle());
        _receiver.setTitle(title);
	}
}

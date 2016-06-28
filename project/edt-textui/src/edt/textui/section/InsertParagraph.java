/** @version $Id: InsertParagraph.java,v 1.6 2015/11/28 12:24:03 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;

/**
 * §2.2.8.
 */
public class InsertParagraph extends SectionCommand {
	public InsertParagraph(Section receiver, Document secondReceiver) {
		super(MenuEntry.INSERT_PARAGRAPH, receiver, secondReceiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
        int id      = IO.readInteger(Message.requestParagraphId());
        String text = IO.readString(
                                Message.requestParagraphContent());
        _receiver.insertParagraph(id, text);
	}

}

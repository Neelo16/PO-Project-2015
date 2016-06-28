/** @version $Id: EditParagraph.java,v 1.6 2015/11/28 12:24:03 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;
import edt.core.NoSuchTextElementException;

/**
 * §2.2.10.
 */
public class EditParagraph extends SectionCommand {
	public EditParagraph(Section receiver, Document secondReceiver) {
		super(MenuEntry.EDIT_PARAGRAPH, receiver, secondReceiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
        int id      = IO.readInteger(Message.requestParagraphId());
        String text = IO.readString(
                                Message.requestParagraphContent());
        try {
            _receiver.changeParagraph(id, text);
        }
        catch (NoSuchTextElementException e) {
            IO.println(Message.noSuchParagraph(id));
        }
	}

}

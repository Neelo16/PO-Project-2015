/** @version $Id: RemoveParagraph.java,v 1.8 2015/11/28 11:57:18 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;
import edt.core.Paragraph;
import edt.core.NoSuchTextElementException;

/**
 * §2.2.11.
 */
public class RemoveParagraph extends SectionCommand {
	public RemoveParagraph(Section receiver, 
                         Document secondReceiver) {
		super(MenuEntry.REMOVE_PARAGRAPH, 
                  receiver, 
                  secondReceiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		int id = IO.readInteger(
                              Message.requestParagraphId());

		try {
			Paragraph paragraph = _receiver.removeParagraph(id);
                  _secondReceiver.removeParagraph(paragraph);
		}
		catch(NoSuchTextElementException e) {
			IO.println(Message.noSuchParagraph(id));
		}

	}

}

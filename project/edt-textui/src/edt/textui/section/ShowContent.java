/** @version $Id: ShowContent.java,v 1.8 2015/11/28 12:24:03 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.util.Collection;

import edt.textui.ShowContentVisitor;

import edt.core.Section;
import edt.core.Document;

/**
 * §2.2.3.
 */
public class ShowContent extends SectionCommand {
	public ShowContent(Section receiver, Document secondReceiver) {
		super(MenuEntry.SHOW_CONTENT, receiver, secondReceiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
        ShowContentVisitor visitor = new ShowContentVisitor();
        Collection<String> strings = _receiver.accept(visitor);

        for (String s : strings)
            IO.println(s);
	}
}

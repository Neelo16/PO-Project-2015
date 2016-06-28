/** @version $Id: Open.java,v 1.8 2015/11/27 16:44:50 ist181201 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Editor;
import edt.core.Document;
import edt.core.DocumentIOException;
import edt.core.DocumentNotFoundException;

/**
 * Open existing document.
 */
public class Open extends Command<Editor> {
	public Open(Editor receiver) {
		super(MenuEntry.OPEN, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		String pathname = IO.readString(Message.openFile());
		try {
			_receiver.open(pathname);
		}
		catch(DocumentNotFoundException e) {
			IO.println(Message.fileNotFound(pathname));
		}
	}
}

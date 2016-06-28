/** @version $Id: Save.java,v 1.7 2015/11/27 16:44:50 ist181201 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Editor;
import edt.core.DocumentIOException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
public class Save extends Command<Editor> {
	public Save(Editor receiver) {
		super(MenuEntry.SAVE, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		if(!_receiver.documentHasFilepath()) {
			String path = IO.readString(Message.newSaveAs());
			_receiver.setDocumentFilepath(path);
		}
		_receiver.save();
	}
}

/** @version $Id: AddAuthor.java,v 1.5 2015/11/28 11:57:17 ist181201 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Editor;
import edt.core.DuplicateAuthorException;

/**
 * §2.1.3.
 */
public class AddAuthor extends Command<Editor> {
    public AddAuthor(Editor receiver) {
        super(MenuEntry.ADD_AUTHOR, receiver);
    }

    @Override
    public final void execute() throws DialogException, IOException {
        String name  = IO.readString(Message.requestAuthorName());
        String email = IO.readString(Message.requestEmail());
        try {
            _receiver.addAuthor(name, email);
        }
        catch(DuplicateAuthorException e) {
            IO.println(Message.duplicateAuthor(name));
        }
    }
}

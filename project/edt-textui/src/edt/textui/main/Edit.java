/** @version $Id: Edit.java,v 1.7 2015/11/15 16:21:07 ist181201 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Editor;
import edt.core.Document;

/**
 * §2.3.1.
 */
public class Edit extends Command<Editor> {
  public Edit(Editor receiver) {
    super(MenuEntry.OPEN_DOCUMENT_EDITOR, receiver);
  }

  @Override
  public final void execute() throws DialogException, IOException {
    Document document = _receiver.getDocument();
    edt.textui.section.MenuBuilder.menuFor(document, document);
  }

}

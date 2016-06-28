/** @version $Id: InsertSection.java,v 1.5 2015/11/28 12:24:03 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;

/**
 * §2.2.5.
 */
public class InsertSection extends SectionCommand {
  public InsertSection(Section receiver, Document secondReceiver) {
    super(MenuEntry.INSERT_SECTION, receiver, secondReceiver);
  }

  @Override
  public final void execute() throws DialogException, IOException {
    int    id    = IO.readInteger(Message.requestSectionId());
    String title = IO.readString(Message.requestSectionTitle());
    _receiver.insertSection(id, title);
  }
}

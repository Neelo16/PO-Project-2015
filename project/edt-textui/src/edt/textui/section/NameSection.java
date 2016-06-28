/** @version $Id: NameSection.java,v 1.7 2015/11/28 11:57:18 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;
import edt.core.NoSuchTextElementException;

/**
 * §2.2.6.
 */
public class NameSection extends SectionCommand {
  public NameSection(Section receiver, Document secondReceiver) {
    super(MenuEntry.NAME_SECTION, receiver, secondReceiver);
  }

  @Override
  public final void execute() throws DialogException, IOException {
    int id     = IO.readInteger(Message.requestSectionId());
    String uid = IO.readString(Message.requestUniqueId());
    
    try {
        Section section = _receiver.getSubsection(id);
        if (_secondReceiver.nameTextElement(uid, section))
            IO.println(Message.sectionNameChanged());
    }
    catch (NoSuchTextElementException e) {
        IO.println(Message.noSuchSection(id));
    }
  }
}

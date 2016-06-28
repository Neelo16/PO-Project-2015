/** @version $Id: NameParagraph.java,v 1.5 2015/11/28 11:57:18 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Document;
import edt.core.NoSuchTextElementException;

/**
 * §2.2.9.
 */
public class NameParagraph extends SectionCommand {
  public NameParagraph(Section receiver, Document secondReceiver) {
    super(MenuEntry.NAME_PARAGRAPH, receiver, secondReceiver);
  }

  @Override
  public final void execute() throws DialogException, IOException {
    int id     = IO.readInteger(Message.requestParagraphId());
    String uid = IO.readString(Message.requestUniqueId());
    try {
        Paragraph paragraph = _receiver.getParagraph(id);
        if (_secondReceiver.nameTextElement(uid, paragraph)) 
            IO.println(Message.paragraphNameChanged());
    }
    catch (NoSuchTextElementException e) {
        IO.println(Message.noSuchParagraph(id));
    }
  }
}

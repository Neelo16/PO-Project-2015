/** @version $Id: RemoveSection.java,v 1.7 2015/11/27 16:44:50 ist181201 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;
import edt.core.NoSuchTextElementException;

/**
 * §2.2.7.
 */
public class RemoveSection extends SectionCommand {
  public RemoveSection(Section receiver, Document secondReceiver) {
    super(MenuEntry.REMOVE_SECTION, receiver, secondReceiver);
  }

  @Override
  public final void execute() throws DialogException, IOException {
  	int id = IO.readInteger(Message.requestSectionId());
  	try {
  		Section section = _receiver.removeSubsection(id);
      _secondReceiver.removeSection(section);
  	}
  	catch(NoSuchTextElementException e) {
  		IO.println(Message.noSuchSection(id));
  	}
  }
  
}

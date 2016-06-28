/** @version $Id: ShowTextElement.java,v 1.6 2015/11/24 19:07:06 ist181201 Exp $ */
package edt.textui.main;

import edt.textui.ShowContentVisitor;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.util.Collection;

import edt.core.Editor;
import edt.core.TextElement;
import edt.core.NoSuchTextElementException;

/**
 * §2.1.5.
 */
public class ShowTextElement extends Command<Editor> {
  public ShowTextElement(Editor receiver) {
    super(MenuEntry.SHOW_TEXT_ELEMENT, receiver);
  }

  @Override
  public final void execute() throws DialogException, IOException {
        String id = IO.readString(Message.requestElementId());
        try {
            TextElement element = _receiver.getTextElement(id);
            ShowContentVisitor visitor = new ShowContentVisitor();
            Collection<String> strings = element.accept(visitor);

            for (String s : strings)
              IO.println(s);
        }
        catch (NoSuchTextElementException e) {
            IO.println(Message.noSuchTextElement(id));
        }
    }
}

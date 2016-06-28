/** @version $Id: SectionCommand.java,v 1.7 2015/11/28 12:24:03 ist181201 Exp $ */
package edt.textui.section;

import ist.po.ui.Command;

import edt.core.Section;
import edt.core.Document;

/**
 * Superclass of all section-context commands.
 */
public abstract class SectionCommand extends Command<Section> {
  
  protected Document _secondReceiver = null;
  
	public SectionCommand(String title, 
                          Section receiver, 
                          Document secondReceiver) {
		super(title, receiver);
        _secondReceiver = secondReceiver;
	}
}

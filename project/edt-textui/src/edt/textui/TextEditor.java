/** @version $Id: TextEditor.java,v 1.8 2015/11/28 11:58:47 ist181201 Exp $ */
package edt.textui;

import static ist.po.ui.Dialog.IO;

import edt.core.Editor;
import edt.core.DataImportException;

/**
 * Class that starts the application's textual interface.
 */
public class TextEditor {
	public static void main(String[] args) {
		String datafile = System.getProperty("import");

		Editor editor   = new Editor();

		if (datafile != null) {
			try {
				editor.readImportFile(datafile);
			}
			catch(DataImportException e){
				return;
			}
		}

		edt.textui.main.MenuBuilder.menuFor(editor);
		IO.closeDown(); 
	}
}

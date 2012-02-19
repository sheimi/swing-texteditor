package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.LangObserver;
import me.sheimi.view.TextEdit;

/**
 * to set up a new file
 * @author sheimi
 *
 */
public class NewFileAction extends EditorAction implements LangObserver {

	public NewFileAction(TextEdit textEdit) {
		super(EditorActionManager.New_FILE_ACTION,
				EditorActionManager.New_FILE_ICON, textEdit);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		m_textEdit.newFile();
	}

}

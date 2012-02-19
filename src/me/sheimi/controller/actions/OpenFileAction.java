package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.LangObserver;
import me.sheimi.view.TextEdit;

/**
 * to open a file
 * @author sheimi
 *
 */
public class OpenFileAction extends EditorAction implements LangObserver {

	public OpenFileAction(TextEdit textEdit) {
		super(EditorActionManager.OPEN_FILE_ACTION,
				EditorActionManager.OPEN_FILE_ICON, textEdit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m_textEdit.openFile();
	}

}

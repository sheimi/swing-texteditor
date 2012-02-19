package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.LangObserver;
import me.sheimi.view.TextEdit;

/**
 * save the file
 * @author sheimi
 *
 */
public class SaveFileAction extends EditorAction implements LangObserver {

	public SaveFileAction(TextEdit textEdit) {
		super(EditorActionManager.SAVE_FILE_ACTION,
				EditorActionManager.SAVE_FILE_ICON, textEdit);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		m_textEdit.saveFile();
	}

}
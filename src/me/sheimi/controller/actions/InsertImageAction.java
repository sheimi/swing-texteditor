package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * to insert a image to the text
 * @author sheimi
 *
 */
public class InsertImageAction extends EditorAction {

	public InsertImageAction(TextEdit textEdit) {
		super(EditorActionManager.INSERT_IMAGE_ACTION,
				EditorActionManager.INSERT_IMAGE_ICON, textEdit);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m_textEdit.insertImage();
	}

}

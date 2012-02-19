package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.DefaultEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * copy the text of Action
 * @author sheimi
 *
 */
public class CopyAction extends EditorAction {

	Action action = new DefaultEditorKit.CopyAction();
	
	public CopyAction(TextEdit textEdit) {
		super(EditorActionManager.COPY_ACTION,
				EditorActionManager.COPY_ICON, textEdit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		action.actionPerformed(e);
	}
}

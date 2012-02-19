package me.sheimi.controller.actions;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * italic
 * @author sheimi
 *
 */
public class ItalicAction extends EditorAction {
	
	Action a;

	public ItalicAction(TextEdit textEdit) {
		super(EditorActionManager.ITALIC_ACTION,
				EditorActionManager.ITALIC_ICON, textEdit);
		a = new StyledEditorKit.ItalicAction();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		a.actionPerformed(e);
	}

}

package me.sheimi.controller.actions;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * underline a text
 * @author sheimi
 *
 */
public class UnderlineAction extends EditorAction {

	Action a;
	
	public UnderlineAction(TextEdit textEdit) {
		super(EditorActionManager.UNDERLINE_ACTION, EditorActionManager.UNDERLINE_ICON, textEdit);
		a = new StyledEditorKit.UnderlineAction();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
		a.actionPerformed(e);
	}

}

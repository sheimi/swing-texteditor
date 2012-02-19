package me.sheimi.controller.actions;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * set a font bold
 * @author sheimi
 *
 */
public class BoldAction extends EditorAction{

	Action a;
	
	public BoldAction(TextEdit textEdit) {
		super(EditorActionManager.BOLD_ACTION, EditorActionManager.BOLD_ICON, textEdit);
		a = new StyledEditorKit.BoldAction();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		a.actionPerformed(e);
	}

}

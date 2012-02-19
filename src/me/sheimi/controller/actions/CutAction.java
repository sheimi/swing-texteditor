package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.DefaultEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * the to cut the text
 * @author sheimi
 *
 */
public class CutAction extends EditorAction {

	Action action = new DefaultEditorKit.CutAction();
	
	public CutAction(TextEdit textEdit) {
		super(EditorActionManager.CUT_ACTION,
				EditorActionManager.CUT_ICON, textEdit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		action.actionPerformed(e);

	}

}

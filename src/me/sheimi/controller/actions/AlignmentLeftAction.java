package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * a method of Alignment Left
 * @author sheimi
 *
 */
public class AlignmentLeftAction extends EditorAction {

	Action a;

	public AlignmentLeftAction(TextEdit textEdit) {
		super(EditorActionManager.ALIGNMENT_LEFT_ACTION, EditorActionManager.ALIGNMENT_LEFT_ICON,
				textEdit);
		a = new StyledEditorKit.AlignmentAction(null, EditorActionManager.ALIGN_LEFT);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		a.actionPerformed(arg0);
	}

}

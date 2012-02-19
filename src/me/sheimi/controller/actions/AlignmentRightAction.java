package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * a method of alignment right
 * @author sheimi
 *
 */
public class AlignmentRightAction extends EditorAction {

	Action a;

	public AlignmentRightAction(TextEdit textEdit) {
		super(EditorActionManager.ALIGNMENT_RIGHT_ACTION, EditorActionManager.ALIGNMENT_RIGHT_ICON,
				textEdit);
		a = new StyledEditorKit.AlignmentAction(null, EditorActionManager.ALIGN_RIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		a.actionPerformed(arg0);
	}
}

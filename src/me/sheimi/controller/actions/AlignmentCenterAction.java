package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * the Action of Alignment Center
 * @author sheimi
 *
 */
public class AlignmentCenterAction extends EditorAction {

	Action a;

	public AlignmentCenterAction(TextEdit textEdit) {
		super(EditorActionManager.ALIGNMENT_CENTER_ACTION, EditorActionManager.ALIGNMENT_CENTER_ICON,
				textEdit);
		a = new StyledEditorKit.AlignmentAction(null, EditorActionManager.ALIGN_CENTER);
	}

	//user adapter
	@Override
	public void actionPerformed(ActionEvent arg0) {
		a.actionPerformed(arg0);
	}
}

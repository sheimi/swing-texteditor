package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.DefaultEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * paste 
 * @author sheimi
 *
 */
public class PasteAction extends EditorAction {


	Action action = new DefaultEditorKit.PasteAction();
	
	public PasteAction(TextEdit textEdit) {
		super(EditorActionManager.PASTE_ACTION,
				EditorActionManager.PASTE_ICON, textEdit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		action.actionPerformed(e);
	}

}

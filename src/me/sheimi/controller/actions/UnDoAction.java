package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * undo the action
 * @author sheimi
 *
 */
public class UnDoAction extends EditorAction {

	UndoManager undo;
	public UnDoAction(TextEdit textEdit, UndoManager undo) {
		super(EditorActionManager.UNDO_ACTION,
				EditorActionManager.UNDO_ICON, textEdit);
		this.undo = undo;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			undo.undo();
		} catch (CannotUndoException ex) {
			System.out.println("Unable to undo: " + ex);
			ex.printStackTrace();
		}
	}

}

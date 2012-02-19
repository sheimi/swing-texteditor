package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * redo the action
 * @author sheimi
 *
 */
public class ReDoAction extends EditorAction {

	UndoManager undo;
	public ReDoAction(TextEdit textEdit, UndoManager undo) {
		super(EditorActionManager.REDO_ACTION,
				EditorActionManager.REDO_ICON, textEdit);
		this.undo = undo;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			undo.redo();
		} catch (CannotRedoException ex) {
			System.out.println("Unable to redo: " + ex);
			ex.printStackTrace();
		}
	}

}

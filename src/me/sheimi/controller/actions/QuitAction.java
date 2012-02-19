package me.sheimi.controller.actions;

import java.awt.event.ActionEvent;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * quit from app
 * @author sheimi
 *
 */
public class QuitAction extends EditorAction {
	public QuitAction(TextEdit textEdit) {
		super(EditorActionManager.QUIT_ACTION,
				EditorActionManager.QUIT_ICON, textEdit);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}

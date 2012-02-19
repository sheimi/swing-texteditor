package me.sheimi.controller.actions;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.text.StyledEditorKit.FontFamilyAction;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * to get help for hlep menu
 * @author sheimi
 *
 */
public class GetHelp extends EditorAction {

	
	public GetHelp(TextEdit textEdit) {
		super(EditorActionManager.GET_HELP, textEdit);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		m_textEdit.getHelp();
	}

}

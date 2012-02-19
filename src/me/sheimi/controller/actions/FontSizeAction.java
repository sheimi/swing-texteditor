package me.sheimi.controller.actions;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.text.StyledEditorKit;

import me.sheimi.controller.EditorActionManager;
import me.sheimi.view.TextEdit;

/**
 * to choose a font size
 * @author sheimi
 *
 */
public class FontSizeAction extends EditorAction {

	Action action;
	int m_size;
	
	public FontSizeAction(int size, TextEdit textEdit) {
		this.m_textEdit = textEdit;
		this.m_size = size;
		this.m_actionName = EditorActionManager.FONT_SIZE_ACTION;
		action = new StyledEditorKit.FontSizeAction(null, size);
		putValue(Action.NAME, Integer.toString(size));
		m_ls.register(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		action.actionPerformed(e);
	}
	
	public void changeLang() {
		putValue(Action.NAME, Integer.toString(m_size));
	}

}
